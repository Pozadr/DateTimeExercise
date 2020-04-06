/*
• Korzystając z klasy oferującej operacje na dacie i czasie pobierz:
◦ aktualną godzinę
◦ nr dnia tygodnia
◦ nr dnia w miesiącu
◦ nr miesiąca
◦ na podstawie tych danych, dobierając optymalną instrukcje warunkową, wypisz:
▪ informację o porze dnia (ustalając arbitralnie): rano, do południa,
  po południu, wieczór, noc
    ▪   informację o dniu: pracujący, wolny, święto
    ▪ informację, czy jest już po wypłacie (przyjmując, że wypłata przychodzi
    do 10 każdego miesiąca)
    ▪ informację o porze roku: wiosna, lato, jesień, zima
 */

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class MyCalendar {
    DateTimeFormatter formatter
            = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    /* TEST
    private LocalDateTime currentDateTime =
            LocalDateTime.parse("2020-02-19T19:34:50.63", formatter);
     */
    private LocalDateTime currentDateTime = LocalDateTime.now();

    private LocalTime currentTime = currentDateTime.toLocalTime();
    private Calendar calendar = Calendar.getInstance();

    // getters
    public LocalDateTime getCurrentDateTime() {
        return currentDateTime;
    }

    public LocalTime getCurrentTime() {
        return currentTime;
    }
    public int getPointerFirstDayOfMonth() {
        /*
        Algorithm based on:
        http://www.algorytm.org/przetwarzanie-dat/wyznaczanie-dnia-tygodnia/dzien-tyg-j.html
         */
        int month = currentDateTime.getMonthValue();
        int year = currentDateTime.getYear();
        int daysFromBeginningOfYear[] =
                {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};
        int yy, c, g;
        int result;

        yy = (year - 1) % 100;
        c = (year - 1) - yy;
        g = yy + (yy / 4);
        result = (((((c / 100) % 4) * 5) + g) % 7);
        result += daysFromBeginningOfYear[month-1];
        // February 28 or 29 days
        if((month > 2) && ((year % 4 == 0  &&  year % 100 != 0) || year % 400 == 0)){
            result++;
        }
        result %= 7;
        result+=1; // because of displayCalendar for loop counts from 1
        return result;
    }
    // display
    public void displayCalendar(){
        String[] daysOfWeek = {" Pn ", "Wt ", "Sr ", "Cz ", "Pt ", "Sb ", "Nd "};
        System.out.println(currentDateTime.getMonth());
        // 1st row of calendar
        for(String day : daysOfWeek){
            System.out.print(day);
        }
        // 2nd row of calendar
        System.out.println();
        System.out.print(" ");
        for(int i=0; i<20; i++){
            System.out.print("-");
        }

        // Days rows
        // dayOfMonth for display in loop, 'j' is a pointer for display in console
        // bug in library for February
        int daysInMonth;
        if(currentDateTime.getMonth() == Month.FEBRUARY){
            daysInMonth = 28;
            int year = currentDateTime.getYear();
            if((year % 4 == 0  &&  year % 100 != 0 || year % 400 == 0)){
                daysInMonth++;
            }
        }
        else{
            daysInMonth = getHowManyDaysInMonth();
        }

        int dayOfMonth = 1;
        int pointerFirstDayOfMonth = getPointerFirstDayOfMonth();
        int numberOfLoops = pointerFirstDayOfMonth + daysInMonth - 1;
        System.out.println(); // new line
        for(int j=1; j<=numberOfLoops;j++){
            if(j < pointerFirstDayOfMonth){
                System.out.print("   ");
                continue;
            }
            // display in console formatting for 1-10
            if( dayOfMonth < 10){
                if(dayOfMonth == currentDateTime.getDayOfMonth()){
                    System.out.print(" [" + dayOfMonth + "]");
                }
                else if(dayOfMonth == currentDateTime.getDayOfMonth()+1
                        && ((j-1)%7 != 0)){
                    System.out.print(" " + dayOfMonth);
                }
                else{
                    System.out.print("  " + dayOfMonth);
                }
            }
            // display in console formatting for 10-31
            else{
                if(dayOfMonth == currentDateTime.getDayOfMonth()){
                    System.out.print("[" + dayOfMonth + "]");
                }
                else if(dayOfMonth == currentDateTime.getDayOfMonth()+1
                    && ((j-1)%7 != 0)){
                    System.out.print(dayOfMonth);
                }
                else{
                    System.out.print(" " + dayOfMonth);
                }            }
            // end of line after 7 days in a row
            if(j %7 == 0){
                System.out.println();
            }
            dayOfMonth++;
        }
    }

    public int getHowManyDaysInMonth(){
        if(currentDateTime.getMonthValue() == 1
                || currentDateTime.getMonthValue() == 3
                || currentDateTime.getMonthValue() == 5
                || currentDateTime.getMonthValue() == 7
                || currentDateTime.getMonthValue() == 8
                || currentDateTime.getMonthValue() == 10
                ||currentDateTime.getMonthValue() == 12){
            return 31;
        }
        else{
            return 30;
        }
    }

    public void displayDetails(){
        DateTimeFormatter formatterDateTime = DateTimeFormatter.ofPattern("\n\ndd/MM/yyyy HH:mm:ss");

        System.out.println(currentDateTime.format(formatterDateTime));

        System.out.println("Month: " + currentDateTime.getMonth());
        System.out.println("Week of year: " + Calendar.WEEK_OF_YEAR);
        System.out.println("Day of month: " + currentDateTime.getDayOfMonth());
        System.out.println("Part of year: " + whatPartOfYear(calendar));
        whatPartOfDay(currentTime);

        String text = isBusinessDay(this.calendar) ? "It's business day."
                : "It's not business day.";
        System.out.println(text);
        isAfterSalary(this.calendar);
    }

    // others
    public static void whatPartOfDay(LocalTime currentTime){
        LocalTime midnight = LocalTime.parse("00:00:00");
        LocalTime morningStart = LocalTime.parse("06:00:00");
        LocalTime morningEnd = LocalTime.parse("10:00:00");
        LocalTime noon = LocalTime.parse("12:00:00");
        LocalTime afternoonEnd = LocalTime.parse("18:00:00");

        if (currentTime.compareTo(midnight) == 0){
            System.out.println("It's midnight.");
        }
        else if (currentTime.compareTo(morningStart) < 0){
            System.out.println("It's night.");
        }
        else if (currentTime.compareTo(afternoonEnd) > 0){
            System.out.println("It's evening.");
        }
        else if (currentTime.compareTo(noon) > 0 &&
                currentTime.compareTo(afternoonEnd) <= 0){
            System.out.println("It's afternoon.");
        }
        else if (currentTime.compareTo(morningEnd) > 0 &&
                currentTime.compareTo(noon) < 0){
            System.out.println("It's close to noon.");
        }
        else if (currentTime.compareTo(noon) == 0){
            System.out.println("It's noon.");
        }
        /*else if (currentTime.compareTo(morningStart) >= 0 &&
                currentTime.compareTo(morningEnd) < 0)*/
        else{
            System.out.println("It's morning.");
        }
    }

    public static boolean isBusinessDay(Calendar cal){
        // check if weekend
        if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            return false;
        }

        // check if New Year's Day
        if (cal.get(Calendar.MONTH) == Calendar.JANUARY
                && cal.get(Calendar.DAY_OF_MONTH) == 1) {
            return false;
        }

        // check MLK Day (3rd Monday of January)
        if (cal.get(Calendar.MONTH) == Calendar.JANUARY
                && cal.get(Calendar.DAY_OF_WEEK_IN_MONTH) == 3
                && cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
            return true;
        }

        // check President's Day (3rd Monday of February)
        if (cal.get(Calendar.MONTH) == Calendar.FEBRUARY
                && cal.get(Calendar.DAY_OF_WEEK_IN_MONTH) == 3
                && cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
            return true;
        }

        // check Memorial Day (last Monday of May)
        if (cal.get(Calendar.MONTH) == Calendar.MAY
                && cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY
                && cal.get(Calendar.DAY_OF_MONTH) > (31 - 7) ) {
            return false;
        }

        // check if 4th of July
        if (cal.get(Calendar.MONTH) == Calendar.JULY
                && cal.get(Calendar.DAY_OF_MONTH) == 4) {
            return false;
        }

        // check Labor Day (1st Monday of September)
        if (cal.get(Calendar.MONTH) == Calendar.SEPTEMBER
                && cal.get(Calendar.DAY_OF_WEEK_IN_MONTH) == 1
                && cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
            return false;
        }

        // check Veterans Day (November 11)
        if (cal.get(Calendar.MONTH) == Calendar.NOVEMBER
                && cal.get(Calendar.DAY_OF_MONTH) == 11) {
            return true;
        }

        // check Thanksgiving (4th Thursday of November)
        if (cal.get(Calendar.MONTH) == Calendar.NOVEMBER
                && cal.get(Calendar.DAY_OF_WEEK_IN_MONTH) == 4
                && cal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
            return false;
        }

        // check if Christmas
        if(cal.get(Calendar.MONTH) == Calendar.DECEMBER
                && cal.get(Calendar.DAY_OF_MONTH) > 24
                && cal.get(Calendar.DAY_OF_MONTH) < 26) {
            return false;
        }

        // IF NOTHING ELSE, IT'S A BUSINESS DAY
        return true;
    }

    public static void isAfterSalary(Calendar cal){
        if(cal.get(Calendar.DAY_OF_MONTH) > 10){
            System.out.println("After salary.");
        }
        else{
            System.out.println("Before salary.");
        }
    }

    public static String whatPartOfYear(Calendar cal){

        if(cal.get(Calendar.MONTH) == Calendar.DECEMBER && cal.get(Calendar.DAY_OF_MONTH) >= 21
                ||cal.get(Calendar.MONTH) == Calendar.MARCH && cal.get(Calendar.DAY_OF_MONTH) < 20
                || cal.get(Calendar.MONTH) < Calendar.MARCH){
            return "WINTER.";
        }
        else if(cal.get(Calendar.MONTH) == Calendar.MARCH && cal.get(Calendar.DAY_OF_MONTH) >= 20
                || cal.get(Calendar.MONTH) == Calendar.JUNE && cal.get(Calendar.DAY_OF_MONTH) < 21
                || cal.get(Calendar.MONTH) > Calendar.MARCH && cal.get(Calendar.MONTH) < Calendar.JUNE) {
            return "SPRING.";
        }
        else if(cal.get(Calendar.MONTH) == Calendar.JUNE && cal.get(Calendar.DAY_OF_MONTH) >= 21
                || cal.get(Calendar.MONTH) == Calendar.SEPTEMBER && cal.get(Calendar.DAY_OF_MONTH) < 23
                || cal.get(Calendar.MONTH) > Calendar.JUNE && cal.get(Calendar.MONTH) < Calendar.SEPTEMBER) {
            return "SUMMER.";
        }
        else if(cal.get(Calendar.MONTH) == Calendar.SEPTEMBER && cal.get(Calendar.DAY_OF_MONTH) >= 23
                || cal.get(Calendar.MONTH) == Calendar.DECEMBER && cal.get(Calendar.DAY_OF_MONTH) < 21
                || cal.get(Calendar.MONTH) > Calendar.SEPTEMBER && cal.get(Calendar.MONTH) < Calendar.DECEMBER) {
            return "AUTUMN.";
        }
        return "Fault";
    }


}
