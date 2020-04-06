import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Loops {

    public void displayNumbers(){

        System.out.println("\nwypisz liczby od -20 do 20");
        for(int i=-20; i<=20; i++){
            System.out.print(i + " ");
        }
        System.out.println("\n6 pierwszych liczb");
        for(int i=-20; i<=20; i++){
            if(i > -15){
                continue;
            }
            System.out.print(i + " ");
        }
        System.out.println("\n6 ostatnich liczb");
        for(int i=-20; i<=20; i++){
            if(i < 15){
                continue;
            }
            System.out.print(i + " ");
        }
        System.out.println("\nwszystkie parzyste liczby");
        for(int i=-20; i<=20; i++){
            if(i % 2 == 0 && i != 0){
                System.out.print(i + " ");
            }
        }
        System.out.println("\nwszystkie liczby oprócz cyfry 5");
        for(int i=-20; i<=20; i++){
            if(i == 5){
                continue;
            }
            System.out.print(i + " ");
        }
        System.out.println("\nwszystkie liczby do cyfry 7 włącznie");
        for(int i=-20; i<=20; i++){
            if(i > 7){
                continue;
            }
            System.out.print(i + " ");
        }
        System.out.println("\nwszystkie liczby podzielne przez 3");
        for(int i=-20; i<=20; i++){
            if(i % 3 == 0 && i != 0){
                System.out.print(i + " ");
            }
        }
        System.out.println("\nsumę wszystkich liczb");
        int x = 0;
        for(int i=-20; i<=20; i++){
            x += i;
        }
        System.out.println("Suma: " + x);

        System.out.println("\nsumę liczb większych lub równych 4");
        int y = 0;
        for(int i=-20; i<=20; i++) {
            if(i >= 4) {
                y += i;
            }
        }
        System.out.println("Suma: " + y);

        System.out.println("\nwszystkie liczby oraz ich kwadratowe potęgi");
        for(int i=-20; i<=20; i++) {
            System.out.print(i + "^2 = " + Math.pow(i, 2) + " ");
        }
        System.out.println("\nwszystkie liczby oraz ich wartość modulo 10");
        for(int i=-20; i<=20; i++) {
            System.out.print(i + "%10 = " + i%10 + " ");
        }
    }

    public void displayDateTimeLoop(LocalTime currentTime){
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss");
        System.out.println("\n\nActual time: " + currentTime.format(formatterTime));
        System.out.println("Hour:");
        for(int i = 1; i <= currentTime.getHour(); i++){
            System.out.print("*");
            if(i % 10 == 0){
                System.out.println();
            }
        }
        System.out.println("\nMinutes:");
        for(int i = 1; i <= currentTime.getMinute(); i++){
            System.out.print("*");
            if(i % 10 == 0){
                System.out.println();
            }
        }

        System.out.println("\nSeconds:");
        for(int i = 1; i <= currentTime.getSecond(); i++){
            System.out.print("*");
            if(i % 10 == 0){
                System.out.println();
            }
        }
    }

}
