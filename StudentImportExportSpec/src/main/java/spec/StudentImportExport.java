package spec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class StudentImportExport {

    private List<Appointment> appointmentList;

    public abstract  boolean loadData(String path, String configPath) throws IOException;

    public abstract  boolean exportData(String path) throws IOException;

    public List<Appointment> getAppointmentList() {
        if(appointmentList == null) appointmentList = new ArrayList<>();
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }
}
