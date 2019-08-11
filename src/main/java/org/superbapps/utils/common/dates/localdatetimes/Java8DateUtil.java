package org.superbapps.utils.common.dates.localdatetimes;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.Date;

public class Java8DateUtil {

    public static final String DATE_PATTERN_SERBIA = "dd.MM.yyyy";
    //</editor-fold>
    public static final String DATE_TIME_PATTERN_SERBIA = "dd.MM.yyyy hh:MM:ss";
    //<editor-fold desc="enum WEEK">
    public enum WEEK {
        DAYS(LocalDate.MIN, LocalDate.MAX);

        public LocalDate start;
        public LocalDate end;

        WEEK(LocalDate start, LocalDate end) {
            this.start = start;
            this.end = end;
        }

        public void setStart(LocalDate start) {
            this.start = start;
        }

        public void setEnd(LocalDate end) {
            this.end = end;
        }

        @Override
        public String toString() {
            return "Starting date : " + start + ", ending date : " + end;
        }
    }

    public static Date FromLocalDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date FromLocalDateTime(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate ToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime ToLocalDateTime(Date dateToConvert) {
        ZonedDateTime zonedDateTime = dateToConvert.toInstant().atZone(ZoneId.systemDefault());
        LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();
        localDateTime.withHour(0).withMinute(0).withSecond(0).withNano(0);

        return localDateTime;
    }

    public static WEEK WorkWeek(LocalDate localDate) {
        return WorkWeek(localDate, DayOfWeek.MONDAY, 5);
    }

    public static WEEK WorkWeek(LocalDate ld, DayOfWeek startingDayOfWeek, int minimalDaysInFirstWeek) {
        LocalDate first = ld.with(WeekFields.of(startingDayOfWeek, minimalDaysInFirstWeek).getFirstDayOfWeek());

        LocalDate last = first.plusDays(4);

        WEEK w = WEEK.DAYS;
        w.setStart(first);
        w.setEnd(last);
        return w;
    }

    public static void main(String[] args) {
        LocalDate now = LocalDate.of(2019, 8, 2);
        WEEK week = WorkWeek(now);
        System.err.println(week);

        System.err.println(week.start.format(DateTimeFormatter.ofPattern(DATE_PATTERN_SERBIA)));
        System.err.println(week.end.format(DateTimeFormatter.ofPattern(DATE_PATTERN_SERBIA)));
    }

}
