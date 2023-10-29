package impl.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVParser;
import spec.Appointment;
import spec.StudentImportExport;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class StudentImportExportApacheCommonsCSV extends StudentImportExport {

    @Override
    public boolean loadData(String filePath, String configPath) throws IOException {
        loadApache(filePath, configPath);
        return true;
    }

    @Override
    public boolean exportData(String path) throws IOException{
        writeData(path);
        return true;
    }

    public void loadApache(String filePath, String configPath) throws IOException {
        List<ConfigMapping> columnMappings = readConfig(configPath);
        Map<Integer, String> mappings = new HashMap<>();
        for(ConfigMapping configMapping : columnMappings) {
            mappings.put(configMapping.getIndex(), configMapping.getOriginal());
        }

        FileReader fileReader = new FileReader(filePath);
        CSVParser parser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(fileReader);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(mappings.get(-1));

        for (CSVRecord record : parser) {
            Appointment appointment = new Appointment();

            for (ConfigMapping entry : columnMappings) {
                int columnIndex = entry.getIndex();

                if(columnIndex == -1) continue;

                String columnName = entry.getCustom();

                switch (mappings.get(columnIndex)) {
                        case "place":
                            appointment.setPlace(record.get(columnIndex));
                            break;
                        case "start":
                            LocalDateTime startDateTime = LocalDateTime.parse(record.get(columnIndex), formatter);
                            appointment.setStart(startDateTime);
                            break;
                        case "end":
                            LocalDateTime endDateTime = LocalDateTime.parse(record.get(columnIndex), formatter);
                            appointment.setEnd(endDateTime);
                            break;
                        case "additional":
                            appointment.getAdditional().put(columnName, record.get(columnIndex));
                            break;
                    }
                }

                getAppointmentList().add(appointment);
            }
    }

    private static List<ConfigMapping>  readConfig(String filePath) throws FileNotFoundException{
        List<ConfigMapping> mappings = new ArrayList<>();

        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] splitLine = line.split(" ", 3);

            mappings.add(new ConfigMapping(Integer.valueOf(splitLine[0]), splitLine[1], splitLine[2]));
        }

        scanner.close();


        return mappings;
    }

    private void writeData(String path) throws IOException {
        // Create a FileWriter and CSVPrinter
        FileWriter fileWriter = new FileWriter(path);
        CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT);

        for (Appointment appointment : super.getAppointmentList()) {
            csvPrinter.printRecord(
                    appointment.getStart(),
                    appointment.getEnd(),
                    appointment.getPlace()
            );
        }

        csvPrinter.close();
        fileWriter.close();
    }


}
