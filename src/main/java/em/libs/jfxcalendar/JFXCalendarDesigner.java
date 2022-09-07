package em.libs.jfxcalendar;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

abstract class JFXCalendarDesigner<T> extends StackPane {
    private GridPane grdMain;
    private GridPane grdHeader;
    private Label lblPreviousMonth;
    private Label lblNextMonth;
    private Label lblYear;
    private Label lblMonthName;
    private GridPane grdCalendar;
    protected TextField txtYearEditor;

    public JFXCalendarDesigner() {
        this.getStylesheets().add(JFXCalendarDesigner.class.getResource("/css/jfx-calendar.css").toExternalForm());
        this.getStyleClass().add("calendar-background");
        this.setAlignment(Pos.CENTER);
        this.setMinSize(250, 300);
    }

    private void initTxtYearEditor() {
        txtYearEditor = new TextField();
        txtYearEditor.setMaxWidth(75);
        txtYearEditor.setAlignment(Pos.CENTER);
        txtYearEditor.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(
                    ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtYearEditor.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        txtYearEditor.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                setVisibleTxtYearEditor(false);
                setVisibleLblYear(true);

                if (txtYearEditor.getText() != null && !txtYearEditor.getText().isBlank()) {
                    lblYear.setText(txtYearEditor.getText());
                    selectedYear(Integer.valueOf(txtYearEditor.getText()));
                }
            } else if (event.getCode() == KeyCode.ESCAPE) {
                setVisibleTxtYearEditor(false);
                setVisibleLblYear(true);
            }
        });
        setVisibleTxtYearEditor(false);

        grdHeader.add(txtYearEditor, 1, 0);
    }

    protected void setVisibleTxtYearEditor(boolean value) {
        txtYearEditor.setManaged(value);
        txtYearEditor.setVisible(value);
    }

    protected void setVisibleLblYear(boolean value) {
        lblYear.setManaged(value);
        lblYear.setVisible(value);
    }

    protected void initCalendar(int month, int year, DayOfWeek startDayOfWeek, Set<DayOfWeek> weekends,
                                Map<String, JFXCalendarData<T>> data) {
        initGrdMain(month, year, startDayOfWeek, weekends, data);
    }

    private void initGrdMain(int month, int year, DayOfWeek startDayOfWeek, Set<DayOfWeek> weekends, Map<String, JFXCalendarData<T>> data) {
        this.getChildren().clear();

        grdMain = new GridPane();
        grdMain.getRowConstraints().add(new RowConstraints() {
            {
                setVgrow(Priority.NEVER);
                setValignment(VPos.CENTER);
            }
        });
        grdMain.getRowConstraints().add(new RowConstraints() {
            {
                setVgrow(Priority.ALWAYS);
                setValignment(VPos.CENTER);
            }
        });
        grdMain.getColumnConstraints().add(new ColumnConstraints() {
            {
                setHgrow(Priority.ALWAYS);
                setHalignment(HPos.CENTER);
            }
        });

        initGrdHeader(month, year);
        initGrdCalendar(month, year, startDayOfWeek, weekends, data);

        this.getChildren().add(grdMain);
    }

    private void initGrdCalendar(int month, int year, DayOfWeek startDayOfWeek, Set<DayOfWeek> weekends,
                                 Map<String, JFXCalendarData<T>> data) {
        grdCalendar = new GridPane();
        GridPane.setMargin(grdCalendar, new Insets(10));
        grdCalendar.getRowConstraints().add(new RowConstraints() {
            {
                setVgrow(Priority.NEVER);
                setValignment(VPos.CENTER);
            }
        });
        grdCalendar.getRowConstraints().add(new RowConstraints() {
            {
                setVgrow(Priority.SOMETIMES);
                setValignment(VPos.CENTER);
            }
        });
        grdCalendar.getRowConstraints().add(new RowConstraints() {
            {
                setVgrow(Priority.SOMETIMES);
                setValignment(VPos.CENTER);
            }
        });
        grdCalendar.getRowConstraints().add(new RowConstraints() {
            {
                setVgrow(Priority.SOMETIMES);
                setValignment(VPos.CENTER);
            }
        });
        grdCalendar.getRowConstraints().add(new RowConstraints() {
            {
                setVgrow(Priority.SOMETIMES);
                setValignment(VPos.CENTER);
            }
        });
        grdCalendar.getRowConstraints().add(new RowConstraints() {
            {
                setVgrow(Priority.SOMETIMES);
                setValignment(VPos.CENTER);
            }
        });
        grdCalendar.getColumnConstraints().add(new ColumnConstraints() {
            {
                setHgrow(Priority.SOMETIMES);
                setHalignment(HPos.CENTER);
            }
        });
        grdCalendar.getColumnConstraints().add(new ColumnConstraints() {
            {
                setHgrow(Priority.SOMETIMES);
                setHalignment(HPos.CENTER);
            }
        });
        grdCalendar.getColumnConstraints().add(new ColumnConstraints() {
            {
                setHgrow(Priority.SOMETIMES);
                setHalignment(HPos.CENTER);
            }
        });
        grdCalendar.getColumnConstraints().add(new ColumnConstraints() {
            {
                setHgrow(Priority.SOMETIMES);
                setHalignment(HPos.CENTER);
            }
        });
        grdCalendar.getColumnConstraints().add(new ColumnConstraints() {
            {
                setHgrow(Priority.SOMETIMES);
                setHalignment(HPos.CENTER);
            }
        });
        grdCalendar.getColumnConstraints().add(new ColumnConstraints() {
            {
                setHgrow(Priority.SOMETIMES);
                setHalignment(HPos.CENTER);
            }
        });
        grdCalendar.getColumnConstraints().add(new ColumnConstraints() {
            {
                setHgrow(Priority.SOMETIMES);
                setHalignment(HPos.CENTER);
            }
        });

        initDaysNameOfWeek(startDayOfWeek, weekends);
        initDays(month, year, startDayOfWeek, weekends, data);

        grdMain.add(grdCalendar, 0, 1);
    }

    private void initDays(int month, int year, DayOfWeek startDayOfWeek, Set<DayOfWeek> weekends, Map<String, JFXCalendarData<T>> data) {
        LocalDate now = LocalDate.now();
        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        LocalDate lastDayOfMonth = firstDayOfMonth.with(TemporalAdjusters.lastDayOfMonth());

        int offset = 0;

        if (startDayOfWeek.getValue() > 1) {
            offset = startDayOfWeek.getValue() + 2;
        }

        int columnIndex = firstDayOfMonth.getDayOfWeek().getValue() - 1 + offset;
        int rowIndex = 1;
        for (int i = 1; i <= lastDayOfMonth.getDayOfMonth(); i++) {
            final int day = i;
            LocalDate date = LocalDate.of(year, month, day);

            if (columnIndex > 7) {
                columnIndex = columnIndex - 7;
            }

            if (columnIndex > 0 && columnIndex % 7 == 0) {
                columnIndex = 0;
                rowIndex++;
            }

            Label lblDay = new Label(String.valueOf(day));
            StackPane.setAlignment(lblDay, Pos.CENTER);

            StackPane spDayBackground = new StackPane(lblDay);
            spDayBackground.setCursor(Cursor.HAND);
            spDayBackground.setOnMouseClicked(event -> day_onClicked(spDayBackground, date, lblDay));

            if (now.equals(date)) {
                // если текущий день
                lblDay.getStyleClass().add("calendar-current-day");

                if (data.containsKey(date.toString())) {
                    // если есть данные
                    Pane paneData = new Pane();
                    StackPane.setAlignment(paneData, Pos.BOTTOM_CENTER);
                    StackPane.setMargin(paneData, new Insets(0, 0, 3, 0));
                    paneData.setMaxSize(6, 6);
                    paneData.getStyleClass().add("calendar-data-current-day-pane");

                    spDayBackground.getChildren().add(paneData);
                }

                spDayBackground.getStyleClass().add("calendar-current-day-panel");
            } else {
                if (weekends.contains(date.getDayOfWeek())) {
                    spDayBackground.getStyleClass().add("calendar-weekend-panel");
                }

                if (data.containsKey(date.toString())) {
                    // если есть данные
                    Pane paneData = new Pane();
                    StackPane.setAlignment(paneData, Pos.BOTTOM_CENTER);
                    StackPane.setMargin(paneData, new Insets(0, 0, 3, 0));
                    paneData.setMaxSize(6, 6);
                    paneData.getStyleClass().add("calendar-data-day-pane");

                    spDayBackground.getChildren().add(paneData);
                }

                lblDay.getStyleClass().add("calendar-day");
            }

            if (rowIndex > 5) {
                grdCalendar.getRowConstraints().add(new RowConstraints() {
                    {
                        setVgrow(Priority.SOMETIMES);
                        setValignment(VPos.CENTER);
                    }
                });
            }

            grdCalendar.add(spDayBackground, columnIndex, rowIndex);
            columnIndex++;
        }
    }

    private void initDaysNameOfWeek(DayOfWeek startDayOfWeek, Set<DayOfWeek> weekends) {
        List<DayOfWeek> values = Arrays.asList(DayOfWeek.values());
        if (values.get(0) != startDayOfWeek) {
            values = new ArrayList<>();
            values.add(startDayOfWeek);
            for (int i = 1; i < 7; i++) {
                if (values.get(i - 1).getValue() == 7) {
                    values.add(DayOfWeek.of(1));
                } else {
                    values.add(DayOfWeek.of(values.get(i - 1).getValue() + 1));
                }
            }
        }

        for (int i = 0; i < values.size(); i++) {
            Label lblDayOfWeek = new Label(values.get(i).getDisplayName(TextStyle.SHORT, Locale.getDefault()).toUpperCase());
            lblDayOfWeek.getStyleClass().add("calendar-day-of-week");

            StackPane spDayOfWeek = new StackPane(lblDayOfWeek);

            if (weekends.contains(values.get(i))) {
                spDayOfWeek.getStyleClass().add("calendar-weekend-panel");
            }
            grdCalendar.add(spDayOfWeek, i, 0);
        }
    }

    private void initGrdHeader(int month, int year) {
        grdHeader = new GridPane();
        grdHeader.getRowConstraints().add(new RowConstraints() {
            {
                setVgrow(Priority.NEVER);
                setValignment(VPos.CENTER);
            }
        });
        grdHeader.getRowConstraints().add(new RowConstraints() {
            {
                setVgrow(Priority.NEVER);
                setValignment(VPos.CENTER);
            }
        });
        grdHeader.getColumnConstraints().add(new ColumnConstraints() {
            {
                setHgrow(Priority.NEVER);
                setHalignment(HPos.CENTER);
            }
        });
        grdHeader.getColumnConstraints().add(new ColumnConstraints() {
            {
                setHgrow(Priority.ALWAYS);
                setHalignment(HPos.CENTER);
            }
        });
        grdHeader.getColumnConstraints().add(new ColumnConstraints() {
            {
                setHgrow(Priority.NEVER);
                setHalignment(HPos.CENTER);
            }
        });

        initLblPreviousMonth();
        initLblNextMonth();
        initLblYear(year);
        initLblMonthName(month);

        grdMain.add(grdHeader, 0, 0);
    }

    private void initLblMonthName(int month) {
        String monthName = Month.of(month).getDisplayName(TextStyle.FULL_STANDALONE, Locale.getDefault());
        lblMonthName = new Label(monthName.substring(0, 1).toUpperCase() + monthName.substring(1));
        lblMonthName.getStyleClass().add("calendar-month");

        grdHeader.add(lblMonthName, 1, 1);
    }

    private void initLblYear(int year) {
        lblYear = new Label(String.valueOf(year));
        lblYear.getStyleClass().add("calendar-year");
        lblYear.setCursor(Cursor.HAND);
        lblYear.setOnMouseClicked(event -> year_onClicked(lblYear, year, event.getScreenX(), event.getScreenY()));

        initTxtYearEditor();

        grdHeader.add(lblYear, 1, 0);
    }

    private void initLblNextMonth() {
        lblNextMonth = new Label(">");
        lblNextMonth.getStyleClass().add("calendar-month-arrow");
        GridPane.setMargin(lblNextMonth, new Insets(0, 30, 0, 0));
        lblNextMonth.setOnMouseClicked(event -> lblNextMonth_onClicked());
        lblNextMonth.setCursor(Cursor.HAND);

        grdHeader.add(lblNextMonth, 2, 1);
    }

    private void initLblPreviousMonth() {
        lblPreviousMonth = new Label("<");
        lblPreviousMonth.getStyleClass().add("calendar-month-arrow");
        GridPane.setMargin(lblPreviousMonth, new Insets(0, 0, 0, 30));
        lblPreviousMonth.setCursor(Cursor.HAND);
        lblPreviousMonth.setOnMouseClicked(event -> lblPreviousMonth_onClicked());

        grdHeader.add(lblPreviousMonth, 0, 1);
    }

    protected abstract void lblNextMonth_onClicked();

    protected abstract void lblPreviousMonth_onClicked();

    protected abstract void day_onClicked(StackPane dayBackgroundPanel, LocalDate selectedDate, Label lblDay);

    protected abstract void year_onClicked(Label lblYear, int currentYear, double x, double y);

    protected abstract void selectedYear(Integer newValue);
}
