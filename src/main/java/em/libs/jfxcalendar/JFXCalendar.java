package em.libs.jfxcalendar;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class JFXCalendar<T> extends JFXCalendarDesigner<T> {

    private int month = LocalDate.now().getMonthValue();
    private int year = LocalDate.now().getYear();
    private DayOfWeek startDayOfWeek = DayOfWeek.MONDAY;
    private final Set<DayOfWeek> weekends = new HashSet<>(Arrays.asList(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY));
    private final SimpleObjectProperty<Optional<LocalDate>> selectedDate = new SimpleObjectProperty<>(Optional.ofNullable(null));
    private final SimpleIntegerProperty selectedYear = new SimpleIntegerProperty(year);
    private final SimpleObjectProperty<LocalDate> selectedMonth = new SimpleObjectProperty<>(LocalDate.of(year, month, 1));
    private final Map<String, JFXCalendarData<T>> data = new TreeMap<>();
    private boolean multipleSelection;
    private final Set<StackPane> selectedDayPanels = new HashSet<>();
    private StackPane previousSelectedPanel;

    public JFXCalendar(Month month, int year) {
        if (month == null) {
            throw new RuntimeException("Month is null");
        }

        if (year < 0) {
            throw new RuntimeException("Year is negative");
        }

        this.month = month.getValue();
        this.year = year;

        selectedYear.set(year);
        selectedMonth.set(LocalDate.of(year, month, 1));

        initNewCalendar(this.month, year, startDayOfWeek, weekends, data);
    }

    public JFXCalendar() {
        initNewCalendar(month, year, startDayOfWeek, weekends, data);
    }

    @Override
    protected void lblNextMonth_onClicked() {
        if (month < 12) {
            month++;
        } else {
            month = 1;
            year++;
        }

        selectedMonth.set(LocalDate.of(year, month, 1));
        initNewCalendar(month, year, startDayOfWeek, weekends, data);
    }

    @Override
    protected void lblPreviousMonth_onClicked() {
        if (month > 1) {
            month--;
        } else {
            month = 12;
            year--;
        }

        selectedMonth.set(LocalDate.of(year, month, 1));
        initNewCalendar(month, year, startDayOfWeek, weekends, data);
    }

    public void setStartDayOfWeek(DayOfWeek startDayOfWeek) {
        this.startDayOfWeek = startDayOfWeek;
        initNewCalendar(month, year, startDayOfWeek, weekends, data);
    }

    public void setWeekends(DayOfWeek... weekends) {
        this.weekends.clear();

        if (weekends != null) {
            this.weekends.addAll(Arrays.asList(weekends));
        }
        initNewCalendar(month, year, startDayOfWeek, this.weekends, data);
    }

    @Override
    protected void day_onClicked(StackPane dayBackgroundPanel, LocalDate selectedDate, Label lblDay) {
        dayBackgroundPanel.setUserData(selectedDate);

        if (!isMultipleSelection()) {
            if (previousSelectedPanel == dayBackgroundPanel) {
                // если предыдущий выбранный день совпадает с текущим выбранным днем
                // значит снять выделение
                dayBackgroundPanel.getStyleClass().remove("calendar-selected-day-panel");
                previousSelectedPanel = null;
                selectedDayPanels.clear();
                this.selectedDate.set(Optional.empty());
                return;
            }

            dayBackgroundPanel.getStyleClass().add("calendar-selected-day-panel");
            if (previousSelectedPanel != null) {
                previousSelectedPanel.getStyleClass().remove("calendar-selected-day-panel");
            }
            previousSelectedPanel = dayBackgroundPanel;
            selectedDayPanels.clear();
            selectedDayPanels.add(dayBackgroundPanel);
            this.selectedDate.set(Optional.of(selectedDate));
        } else {
            if (selectedDayPanels.contains(dayBackgroundPanel)) {
                dayBackgroundPanel.getStyleClass().remove("calendar-selected-day-panel");
                selectedDayPanels.remove(dayBackgroundPanel);
                this.selectedDate.set(Optional.empty());
                return;
            }

            dayBackgroundPanel.getStyleClass().add("calendar-selected-day-panel");
            selectedDayPanels.add(dayBackgroundPanel);
            this.selectedDate.set(Optional.of(selectedDate));
        }
    }

    public SimpleObjectProperty<Optional<LocalDate>> selectedDateProperty() {
        return selectedDate;
    }

    public SimpleIntegerProperty selectedYearProperty() {
        return selectedYear;
    }

    public SimpleObjectProperty<LocalDate> selectedMonthProperty() {
        return selectedMonth;
    }

    public void setData(List<JFXCalendarData<T>> data) {
        clearData();

        if (data != null) {
            this.data.putAll(data.stream().collect(Collectors.toMap(calendarData -> calendarData.getDate().toString(), calendarData -> calendarData)));
        }

        initNewCalendar(month, year, startDayOfWeek, weekends, this.data);
    }

    private void clearData() {
        this.data.clear();
    }

    public void setMultipleSelection(boolean value) {
        multipleSelection = value;
    }

    public boolean isMultipleSelection() {
        return multipleSelection;
    }

    public Optional<JFXCalendarData<T>> getData(LocalDate date) {
        if (date == null) {
            return Optional.empty();
        }

        return Optional.ofNullable(data.get(date.toString()));
    }

    private void initNewCalendar(int month, int year, DayOfWeek startDayOfWeek, Set<DayOfWeek> weekends,
                                 Map<String, JFXCalendarData<T>> data) {
        clearSelected();
        initCalendar(month, year, startDayOfWeek, weekends, data);
    }

    private void clearSelected() {
        previousSelectedPanel = null;
        selectedDayPanels.clear();
    }

    public Set<LocalDate> getSelectedDates() {
        return selectedDayPanels.stream().map(stackPane -> (LocalDate) stackPane.getUserData()).collect(Collectors.toSet());
    }

    @Override
    protected void year_onClicked(Label lblYear, int currentYear, double x, double y) {
        setVisibleTxtYearEditor(true);
        txtYearEditor.setText(String.valueOf(currentYear));
        setVisibleLblYear(false);
    }

    @Override
    protected void selectedYear(Integer newValue) {
        year = newValue;

        selectedYear.set(year);
        initNewCalendar(month, year, startDayOfWeek, weekends, data);
    }
}
