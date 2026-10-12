import java.io.IOException;
import java.time.Clock;
import java.time.Instant;

public class Main {
    public static void main(String[] args) throws IOException {
        ParkingLL parking = new ParkingLL();

        Clock timeClockObject = Clock.systemUTC();

        System.out.println("-------- Start reading the file ------");
        Instant start = timeClockObject.instant();
        System.out.println("Start time: " + start);
        parking.readDataFromFile();
        System.out.println("End reading the file ------");
        Instant end = timeClockObject.instant();
        System.out.println("End time: " + end);
        System.out.println("Total Time: " + (end.toEpochMilli() - start.toEpochMilli()) + " milliseconds\n");

        System.out.println("-------- Start processing the data ------");
        start = timeClockObject.instant();
        System.out.println("Start time: " + start);
        parking.processTheData();
        System.out.println("End processing the data ------");
        end = timeClockObject.instant();
        System.out.println("End time: " + end);
        System.out.println("Total Time: " + (end.toEpochMilli() - start.toEpochMilli()) + " milliseconds\n");

        System.out.println("-------- Start writing the file ------");
        start = timeClockObject.instant();
        System.out.println("Start time: " + start);
        parking.printDataToFile();
        System.out.println("End writing the file ------");
        end = timeClockObject.instant();
        System.out.println("End time: " + end);
        System.out.println("Total Time: " + (end.toEpochMilli() - start.toEpochMilli()) + " milliseconds");
    }
}