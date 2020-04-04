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
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class MyCalendar {

    private LocalDateTime currentDateTime = LocalDateTime.now();
    private LocalTime currentTime = currentDateTime.toLocalTime();
    private Calendar calendar = Calendar.getInstance();


    public void displayDetails(){
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter formatterDateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

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
