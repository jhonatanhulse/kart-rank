package kartrank;

import kartrank.domain.Lap;
import kartrank.domain.Pilot;
import kartrank.util.LapTimeUtils;

import java.io.InputStream;
import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Class responsible for initializing the kart rank application
 */
public class AppInitializer {

    private static Scanner scanner;

    private static Map<Integer, Pilot> pilots = new HashMap<>();

    private static Map<Integer, Integer> places = new HashMap<>();

    /**
     * Print expected output based on resource file with the input
     * named input.txt
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println();
        InputStream inputStream = ClassLoader.getSystemResourceAsStream("input.txt");
        scanner = new Scanner(inputStream);

        populateRank();

        for (Map.Entry<Integer, Integer> entry : places.entrySet()) {
            Integer place = entry.getKey();
            Pilot pilot = pilots.get(entry.getValue());

            System.out.println(
                place + " "
                + pilot.getCode() + " "
                + pilot.getName() + " "
                + pilot.getLaps().size() + " "
                + LapTimeUtils.format(pilot.getTotalTime().toMillis())
            );
        }

        System.out.println();

        for (Map.Entry<Integer, Pilot> entry : pilots.entrySet()) {
            Pilot pilot = entry.getValue();
            Lap bestLap = pilot.getBestLap();

            System.out.println(
                pilot.getCode() + " "
                + pilot.getName() + " "
                + pilot.getAverageSpeed() + " "
                + bestLap.getNumber() + " "
                + LapTimeUtils.format(bestLap.getLapTime().toMillis())
            );
        }

        System.out.println();
    }

    /**
     * Populates the Map of pilots using the input file
     */
    private static void populateRank() {
        int lastLap = 4;
        int currentPlace = 1;

        while (scanner.hasNext()) {
            Lap lap = new Lap();
            Pilot pilot = new Pilot();

            lap.setTime(LocalTime.parse(scanner.next()));

            pilot.setCode(scanner.nextInt());
            scanner.next(); // skip the '-'
            pilot.setName(scanner.next());

            // If the pilot is already mapped use it
            // instead of the created instance
            if (pilots.containsKey(pilot.getCode())) {
                pilot = pilots.get(pilot.getCode());
            } else {
                pilots.put(pilot.getCode(), pilot);
            }

            lap.setNumber(scanner.nextInt());

            // If it is the last lap fill one map
            // with the winners places
            if (lap.getNumber() == lastLap) {
                places.put(currentPlace, pilot.getCode());
                currentPlace++;
            }

            String lapTime = scanner.next();
            // Split the lap time to create
            // Duration object
            String[] splittedLapTime = lapTime.split(":");
            String[] splittedLapTimeSeconds = splittedLapTime[1].split("\\.");

            lap.setLapTime(
                Duration.ofMinutes(Long.parseLong(splittedLapTime[0]))
                    .plusSeconds(Long.parseLong(splittedLapTimeSeconds[0]))
                    .plusMillis(Long.parseLong(splittedLapTimeSeconds[1]))
            );

            lap.setAverageSpeed(Float.parseFloat(scanner.next().replace(",", ".")));
            // Make the bidirectional relationship
            // between the pilot and its laps
            lap.setPilot(pilot);
            pilot.getLaps().put(lap.getNumber(), lap);
        }
    }
}
