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

If you use the default constructor, then the month displayed in the calendar will be taken from the current date.

![Default constructor](https://i.imgur.com/qSwO1Ta.png)

Of course, you can use another constructor and pass in the desired month and year.

```
JFXCalendar<String> calendar = new JFXCalendar<>(Month.OCTOBER, 2023);
```

![Constructor with month and year](https://i.imgur.com/qAd2LA4.png)

# Set data
You can set data for specific days, and the days will be marked.

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
You can get the data set on a specific day.

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
The default start of the week is Monday, but this can be configured.

```
JFXCalendar<String> calendar = new JFXCalendar<>();
calendar.setStartDayOfWeek(DayOfWeek.WEDNESDAY);
```

![Set start day of week](https://i.imgur.com/VkpMXtV.png)

# Set weekends
By default, Saturday and Sunday are set as holidays, but this can be configured.

```
JFXCalendar<String> calendar = new JFXCalendar<>();
calendar.setWeekends(DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY);
```

![Set weekends](https://i.imgur.com/n77wvKM.png)

# Set multiple selection
By default, only one day can be selected by clicking on it, but multiple selection can be enabled.

```
JFXCalendar<String> calendar = new JFXCalendar<>();
calendar.setMultipleSelection(true);
```

![Set multiple selection](https://i.imgur.com/svlhg4t.png)

# Edit year
To change the year, you must click on it, enter new data and press **Enter** to apply the changes
or press **Esc** to cancel changes.

![Edit year](https://i.imgur.com/miKPIDT.png)

# Get selected dates
To get the selected days, you need to call the `getSelectedDates` method.

```
JFXCalendar<String> calendar = new JFXCalendar<>();
Set<LocalDate> selectedDates = calendar.getSelectedDates();
```

# Listen selected day
To subscribe to the change event of the selected day:

```
JFXCalendar<String> calendar = new JFXCalendar<>();
calendar.selectedDateProperty().addListener((observable, oldValue, newValue) -> {
    // code
});
```

# Listen selected year
To subscribe to the change event of the selected year:

```
JFXCalendar<String> calendar = new JFXCalendar<>();
calendar.selectedYearProperty().addListener((observable, oldValue, newValue) -> {
    // code
});
```

# Listen selected month
To subscribe to the change event of the selected month:

```
JFXCalendar<String> calendar = new JFXCalendar<>();
calendar.selectedMonthProperty().addListener((observable, oldValue, newValue) -> {
    // code
});
```

**Please note** that `LocalDate` is used as the value,
since it is necessary to know not only the month, but also the year, and the day will always be 1.