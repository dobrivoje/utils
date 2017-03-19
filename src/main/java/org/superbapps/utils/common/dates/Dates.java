/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.superbapps.utils.common.dates;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.superbapps.utils.common.dates.formats.DateFormat;

public class Dates {

    private Date from;
    private Date to;
    private DateFormat dateFormat;

    /**
     * Pozivanje ovog konstruktora postavlja interval datuma : <br>
     * od 1. dana prethodnog meseca do današnjeg datuma.
     */
    public Dates() {
        this(-1);
    }

    /**
     * Pozivanje ovog konstruktora postavlja interval datuma : <br>
     * od 1. dana prethodnog meseca do današnjeg datuma (justThatMonth=false).
     * od 1. dana prethodnog meseca do posledenjeg dana tog meseca
     * (justThatMonth=true).
     *
     * @param justThatMonth Do poslednjeg dana u mesecu.
     */
    public Dates(boolean justThatMonth) {
        this(-1, justThatMonth);
    }

    public Dates(Dates d) {
        this.from = d.getFrom();
        this.to = d.getTo();
    }

    public Dates(Date from, Date to) {
        this.from = from;
        this.to = to;

        if (this.from.after(this.to)) {
            Date z = this.from;
            this.from = this.to;
            this.to = z;
        }
    }

    public Dates(String from, String to, String format) {
        try {
            this.from = new SimpleDateFormat(format).parse(from);
            this.to = new SimpleDateFormat(format).parse(to);
        } catch (ParseException ex) {
        }
    }

    /**
     * Default constructor with English date format.
     *
     * @param from
     * @param to
     */
    public Dates(String from, String to) {
        try {
            this.from = new SimpleDateFormat(DateFormat.DATE_FORMAT_ENG.toString()).parse(from);
            this.to = new SimpleDateFormat(DateFormat.DATE_FORMAT_ENG.toString()).parse(to);
        } catch (ParseException ex) {
        }
    }

    /**
     *
     * @param months Broj meseci - podešavanje intervala.
     * <p>
     * months > 0 : Od danas do poslednjeg dana za <u>months</u> unapred. <br>
     * months < 0 : Od 1. dana za <u>months</u> unazad, do danas. <br>
     */
    public Dates(int months) {
        this.dateFormat = DateFormat.DATE_FORMAT_ENG;
        setMonthsBF(months, false);
    }

    public Dates(int months, DateFormat dateFormat) {
        this.dateFormat = dateFormat;
        setMonthsBF(months, false);
    }

    /**
     *
     * @param months Broj meseci - podešavanje intervala.
     * <p>
     * months > 0 : Od danas do poslednjeg dana za <u>months</u> unapred. <br>
     * months < 0 : Od 1. dana za <u>months</u> unazad, do dana određenog
     * parametrom justThatMonth. <br>
     *
     * @param justThatMonth=true: do poslednjeg dana tog meseca <br>
     * justThatMonth=false : do danas.<br>
     */
    public Dates(int months, boolean justThatMonth) {
        this.dateFormat = DateFormat.DATE_FORMAT_ENG;
        setMonthsBF(months, justThatMonth);
    }

    public Dates(int months, boolean justThatMonth, DateFormat dateFormat) {
        this.dateFormat = dateFormat;
        setMonthsBF(months, justThatMonth);
    }

    /**
     * @param months Broj meseci - podešavanje intervala.<br>
     * months &lt 0 : Interval je : Od 1. dana za <u>months</u> unazad, do
     * danas, za justThatMonth=false.<br>
     *
     * months &lt 0 : Interval je : Od 1. dana za <u>months</u> unazad, do
     * poslednjeg dana u tom mesecu, za justThatMonth=true.<br>
     *
     * months > 0 : Interval je : Od danas do poslednjeg dana za <u>months</u>
     * unapred. <br>
     *
     * @param justThatMonth
     */
    public void setMonthsBackForth(int months, boolean justThatMonth) {
        setMonthsBF(months, justThatMonth);
    }

    public void setMonthsBackForth(int months) {
        setMonthsBF(months, false);
    }

    /**
     * Metod u zavisnosti da li je radni dan ili dan vikenda,<br>
     * vraća taj radni dan, ili ako je vikend, vraća prvi sledeći ponedeljak<br>
     *
     * @param startHour
     * @return
     */
    public static Date getWorkDay(int startHour) {
        LocalDate ldToday = LocalDate.now();

        switch (ldToday.getDayOfWeek()) {
            case FRIDAY:
                ldToday = ldToday.plusDays(3);
                break;
            case SATURDAY:
                ldToday = ldToday.plusDays(2);
                break;
            case SUNDAY:
                ldToday = ldToday.plusDays(1);
                break;
            default:
                // ldToday = ldToday.plusDays(1);
                break;
        }
        LocalDateTime ldt = ldToday.atTime(startHour, 0);

        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Vrati dan iz radne nedelje, a ako je dan koji pada u vikdend, vrati prvi
     * sledeći ponedeljak.
     *
     * @param startHour
     * @return
     */
    public static Date getWorkWeekDay(int startHour) {
        LocalDate ldToday = LocalDate.now();

        switch (ldToday.getDayOfWeek()) {
            case SATURDAY:
                ldToday = ldToday.plusDays(2);
                break;
            case SUNDAY:
                ldToday = ldToday.plusDays(1);
                break;
        }

        LocalDateTime ldt = ldToday.atTime(startHour, 0);

        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Konstruisanje radne nedelje prema postavljenom datumu.<br>
     * Ako dan pripada radnoj nedelji, prvi i poslednji dani su ponedeljak i
     * petak tekuće nedelje.<br>
     * Ako dan pripada vikendu, početni i krajnji dani ponedeljak i petak
     * sledeće nedelje.<br>
     *
     * @param currentDate
     * @param startHour
     * @param endHour
     * @return
     */
    public static DateInterval getWorkWeek(Date currentDate, int startHour, int endHour) {
        int daysDiff = 4;
        LocalDate ldtEnd;

        LocalDate ldCurrent = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        switch (ldCurrent.getDayOfWeek()) {
            case SATURDAY:
                ldCurrent = ldCurrent.plusDays(2);
                break;
            case SUNDAY:
                ldCurrent = ldCurrent.plusDays(1);
                break;
            default:
                daysDiff = ldCurrent.getDayOfWeek().getValue() - DayOfWeek.MONDAY.getValue();
                ldCurrent = ldCurrent.minusDays(daysDiff);
                break;
        }

        ldtEnd = ldCurrent.plusDays(4);

        LocalDateTime ldts = ldCurrent.atTime(startHour, 0);
        LocalDateTime ldte = ldtEnd.atTime(startHour, 0);

        Date ds = Date.from(ldts.atZone(ZoneId.systemDefault()).toInstant());
        Date de = Date.from(ldte.atZone(ZoneId.systemDefault()).toInstant());

        return new DateInterval(ds, de);
    }

    /**
     * Konstruisanje radne nedelje prema današnjem datumu.<br>
     * Ako dan pripada radnoj nedelji, prvi i poslednji dani su ponedeljak i
     * petak tekuće nedelje.<br>
     * Ako dan pripada vikendu, početni i krajnji dani ponedeljak i petak
     * sledeće nedelje.<br>
     *
     * @param startHour
     * @return
     */
    public static DateInterval getWorkWeek(int startHour) {
        return getWorkWeek(new Date(), startHour, 0);
    }

    //<editor-fold defaultstate="collapsed" desc="DateInterval">
    public static class DateInterval {

        private Date from;
        private Date to;

        public DateInterval() {
            this(new Date(), new Date());
        }

        public DateInterval(Date from, Date to) {
            this.from = from;
            this.to = to;
        }

        public Date getFrom() {
            return from;
        }

        public void setFrom(Date from) {
            this.from = from;
        }

        public Date getTo() {
            return to;
        }

        public void setTo(Date to) {
            this.to = to;
        }

    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="interni metodi">
    private Date setDMY(int month, int day, int year) {
        Calendar c1 = new GregorianCalendar();

        c1.set(Calendar.MONTH, month - 1);
        c1.set(Calendar.DAY_OF_MONTH, day);
        c1.set(Calendar.YEAR, year);

        return c1.getTime();
    }

    private void setMonthsBF(int months, boolean justThatMonth) {
        Calendar c1 = new GregorianCalendar();

        c1.add(Calendar.MONTH, months);

        if (months < 0) {
            c1.set(Calendar.DAY_OF_MONTH, 1);

            this.from = setHMS(c1, 0, 0, 0);

            if (justThatMonth) {
                c1.set(Calendar.DAY_OF_MONTH, c1.getActualMaximum(Calendar.DAY_OF_MONTH));
                this.to = setHMS(c1, 23, 59, 59);
            } else {
                this.to = new Date();
            }
        } else {
            c1.set(Calendar.DAY_OF_MONTH, c1.getActualMaximum(Calendar.DAY_OF_MONTH));

            this.from = new Date();
            this.to = setHMS(c1, 23, 59, 59);
        }

        if (from.after(to)) {
            Date z = from;
            from = to;
            to = z;
        }
    }

    private Date setHMS(Calendar c, int h, int m, int s) {
        c.set(Calendar.HOUR_OF_DAY, h);
        c.set(Calendar.MINUTE, m);
        c.set(Calendar.SECOND, s);

        return c.getTime();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getter/setter">
    public Date getFrom() {
        return from;
    }

    public String getFrom(DateFormat dateFormat) {
        return new SimpleDateFormat(dateFormat.toString()).format(from);
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public void setFrom(String from, String format) {
        try {
            this.from = new SimpleDateFormat(format).parse(from);
        } catch (ParseException ex) {
        }
    }

    public void setFrom(int day, int month, int year) {
        this.from = setDMY(month, day, year);
    }

    public Date getTo() {
        return to;
    }

    public String getTo(DateFormat dateFormat) {
        return new SimpleDateFormat(dateFormat.toString()).format(to);
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public void setTo(String to, String format) {
        try {
            this.to = new SimpleDateFormat(format).parse(to);
        } catch (ParseException ex) {
        }
    }

    public void setTo(int day, int month, int year) {
        this.to = setDMY(month, day, year);
    }

    /**
     * Default english format (yyyy-MM-dd)
     *
     * @return
     */
    public String getFromStr() {
        try {
            return getFromStr(dateFormat.toString());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Default english format (yyyy-MM-dd)
     *
     * @return
     */
    public String getToStr() {
        try {
            return getToStr(dateFormat.toString());
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * Custom format
     *
     * @param format Custom date format
     * @return
     */
    public String getFromStr(String format) {
        try {
            return new SimpleDateFormat(format).format(from);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Custom format
     *
     * @param format Custom date format
     * @return
     */
    public String getToStr(String format) {
        try {
            return new SimpleDateFormat(format).format(to);
        } catch (Exception e) {
            return null;
        }
    }
    //</editor-fold>

    @Override
    public String toString() {
        return "["
                + from + ", " + to
                + "]";
    }

    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -0);
        Date d = Date.from(c.toInstant());

        System.err.println("dan : " + d);

        DateInterval di = Dates.getWorkWeek(d, 13, 14);
        System.err.println("od : " + di.from + ", do :" + di.to);
    }

}
