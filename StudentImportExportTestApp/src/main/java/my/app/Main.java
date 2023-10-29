package my.app;

import impl.csv.StudentImportExportApacheCommonsCSV;
import impl.csv.StudentImportExportOpenCSV;

import java.io.IOException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        StudentImportExportApacheCommonsCSV studentImportExportApacheCommonsCSV = new StudentImportExportApacheCommonsCSV();
        StudentImportExportOpenCSV studentImportExportOpenCSV = new StudentImportExportOpenCSV();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Unesite putanju do fajla i konfiguracionog fajla u obliku: putanjaDoFajla,putanjaDoKonfiguracije");
        String line = scanner.nextLine();
        try {
            studentImportExportApacheCommonsCSV.loadData(line.split(",")[0], line.split(",")[1]);
            /// studentImportExportOpenCSV.loadData(line.split(",")[0], line.split(",")[1]);
            System.out.println(studentImportExportApacheCommonsCSV.getAppointmentList());
        } catch (IOException e) {
            System.out.println("Greska pri citanju fajlova");
            return;
        }
        System.out.println("Unesite naziv izlaznog fajla");
        line = scanner.nextLine();
        try {
            studentImportExportApacheCommonsCSV.exportData(line);
            /// studentImportExportOpenCSV.exportData(line);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return;
    }
}
