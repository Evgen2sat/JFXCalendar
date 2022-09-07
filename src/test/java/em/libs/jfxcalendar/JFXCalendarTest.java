package em.libs.jfxcalendar;

import javafx.application.Platform;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

public class JFXCalendarTest {
    @BeforeAll
    static void initJfxRuntime() {
        Platform.startup(() -> {
        });
    }

    @Test
    void getExistCalendarData() {
        JFXCalendar<Integer> calendar = new JFXCalendar<>();
        calendar.setData(Arrays.asList(new JFXCalendarData<>(LocalDate.now(), 123)));

        Assertions.assertTrue(calendar.getData(LocalDate.now()).isPresent());
    }

    @Test
    void getNotExistCalendarData() {
        JFXCalendar<Integer> calendar = new JFXCalendar<>();
        calendar.setData(Arrays.asList(new JFXCalendarData<>(LocalDate.now(), 123)));

        Assertions.assertFalse(calendar.getData(LocalDate.now().minusDays(1)).isPresent());
    }
}
