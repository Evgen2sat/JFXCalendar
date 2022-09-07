package em.libs.jfxcalendar;

import java.time.LocalDate;

public class JFXCalendarData<T> {
    private final LocalDate date;
    private final T data;

    public JFXCalendarData(LocalDate date, T data) {
        this.date = date;
        this.data = data;
    }

    public LocalDate getDate() {
        return date;
    }

    public T getData() {
        return data;
    }
}
