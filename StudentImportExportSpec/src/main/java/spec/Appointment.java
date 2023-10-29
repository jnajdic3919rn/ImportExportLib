package spec;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Appointment {

    private LocalDateTime start;
    private LocalDateTime end;
    private String place;

    private Map<String, String> additional;

    public Appointment(){
        this.additional = new HashMap<>();
    }

    public Appointment(LocalDateTime start, LocalDateTime end, String place) {
        this.start = start;
        this.end = end;
        this.place = place;
        this.additional = new HashMap<>();
    }

    public Appointment(LocalDateTime start, LocalDateTime end, String place, Map<String, String> additional) {
        this.start = start;
        this.end = end;
        this.place = place;
        this.additional = additional;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Map<String, String> getAdditional() {
        return additional;
    }

    public void setAdditional(Map<String, String> additional) {
        this.additional = additional;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return Objects.equals(start, that.start) && Objects.equals(end, that.end) && Objects.equals(place, that.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end, place);
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "start=" + start +
                ", end=" + end +
                ", place='" + place + '\'' +
                ", additional=" + additional +
                '}';
    }
}
