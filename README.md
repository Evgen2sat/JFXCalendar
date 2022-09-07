# JFXCalendar
JavaFX Calendar with additional functions

# Description
**JFXCalendar** is a simple JavaFX calendar that allows you to set/get data in the calendar, 
set the start day of the week (Monday by default) and set weekend days (Saturday and Sunday by default).

#Get started
Create a **JFXCalendar** object and you're done.

In this example, we will create a **JFXCalendar** in which the data will be of the String type.

```
JFXCalendar<String> calendar = new JFXCalendar<>();
```

Если использовать конструктор по умолчанию, то месяц, отображаемый в календаре будет взят из текущей даты.

![Default constructor](https://i.imgur.com/qSwO1Ta.png)

Конечно, можно использовать другой конструктор и передать необходимый месяц и год.

```
JFXCalendar<String> calendar = new JFXCalendar<>(Month.OCTOBER, 2023);
```

![Constructor with month and year](https://i.imgur.com/qAd2LA4.png)

# Set data
Вы можете установить данные для определенных дней, при этом дни будут отмечены.

```
JFXCalendar<String> calendar = new JFXCalendar<>();
List<JFXCalendarData<String>> data = new ArrayList<>();
data.add(new JFXCalendarData<>(LocalDate.of(2022, 9, 1), "data1"));
data.add(new JFXCalendarData<>(LocalDate.of(2022, 9, 2), "data2"));
data.add(new JFXCalendarData<>(LocalDate.of(2022, 9, 3), "data3"));
calendar.setData(data);
```

![Set data](https://i.imgur.com/NGgrB44.png)

# Get data
Можно получить данные установленные в определенный день.

```
JFXCalendar<String> calendar = new JFXCalendar<>();
List<JFXCalendarData<String>> data = new ArrayList<>();
data.add(new JFXCalendarData<>(LocalDate.of(2022, 9, 1), "data1"));
data.add(new JFXCalendarData<>(LocalDate.of(2022, 9, 2), "data2"));
data.add(new JFXCalendarData<>(LocalDate.of(2022, 9, 3), "data3"));
calendar.setData(data);
Optional<JFXCalendarData<String>> data1 = calendarView.getData(LocalDate.of(2022, 9, 1));
```

# Set start day of week
По умолчанию началом недели считается понедельник, но это можно настроить.

```
JFXCalendar<String> calendar = new JFXCalendar<>();
calendar.setStartDayOfWeek(DayOfWeek.WEDNESDAY);
```

![Set start day of week](https://i.imgur.com/VkpMXtV.png)

# Set weekends
По умолчанию в качестве выходных установлены суббота и воскресенье, но это можно настроить.

```
JFXCalendar<String> calendar = new JFXCalendar<>();
calendar.setWeekends(DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY);
```

![Set weekends](https://i.imgur.com/n77wvKM.png)

# Set multiple selection
По умолчанию можно выбрать только один день, кликнув на него, но можно включить множественный выбор.

```
JFXCalendar<String> calendar = new JFXCalendar<>();
calendar.setMultipleSelection(true);
```

![Set multiple selection](https://i.imgur.com/svlhg4t.png)

# Edit year
Чтобы изменить год необходимо нажать на него, ввести новые данные и нажать **Enter**, чтобы применить изменения
или нажать **Esc**, чтобы отменить изменения.

![Edit year](https://i.imgur.com/miKPIDT.png)

# Get selected dates
Чтобы получить выбранные дни, необходимо вызвать метод **getSelectedDates**.

```
JFXCalendar<String> calendar = new JFXCalendar<>();
Set<LocalDate> selectedDates = calendar.getSelectedDates();
```

# Listen selected day
Чтобы подписаться на событие изменения выбранного дня необходимо:

```
JFXCalendar<String> calendar = new JFXCalendar<>();
calendar.selectedDateProperty().addListener((observable, oldValue, newValue) -> {
    // code
});
```

# Listen selected year
Чтобы подписаться на событие изменения выбранного года необходимо:

```
JFXCalendar<String> calendar = new JFXCalendar<>();
calendar.selectedYearProperty().addListener((observable, oldValue, newValue) -> {
    // code
});
```

# Listen selected month
Чтобы подписаться на событие изменения выбранного месяца необходимо:

```
JFXCalendar<String> calendar = new JFXCalendar<>();
calendar.selectedMonthProperty().addListener((observable, oldValue, newValue) -> {
    // code
});
```

**Обратите внимание**, что в качестве значения используется `LocalDate`, 
так как необходимо знать не только месяц, но и год, а день всегда будет 1.