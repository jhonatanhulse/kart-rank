package kartrank.domain;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * Simple POJO representing one pilot
 */
public class Pilot {

    private Integer code;

    private String name;

    private Map<Integer, Lap> laps;

    private Lap bestLap;

    private Float averageSpeed;

    private Duration totalTime;

    public Pilot() {
        laps = new HashMap<>();
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Map<Integer, Lap> getLaps() {
        return laps;
    }

    /**
     * If the best lap was already calculated return it, otherwise
     * search between all added laps to find the best lap
     *
     * @return the best lap
     */
    public Lap getBestLap() {
        if (bestLap != null) {
            return bestLap;
        }

        for (Map.Entry<Integer, Lap> entry : laps.entrySet()) {
            if (bestLap == null) {
                bestLap = entry.getValue();
            } else {
                Lap lap = entry.getValue();
                if (lap.getLapTime().toMillis() < bestLap.getLapTime().toMillis()) {
                    bestLap = lap;
                }
            }
        }

        return bestLap;
    }


    /**
     * If the average speed was already calculated return it, otherwise
     * calculate the average speed using all added laps
     *
     * @return the average speed
     */
    public Float getAverageSpeed() {
        if (averageSpeed != null) {
            return averageSpeed;
        }

        averageSpeed = 0f;
        for (Map.Entry<Integer, Lap> entry : laps.entrySet()) {
            Lap lap = entry.getValue();
            averageSpeed += lap.getAverageSpeed();
        }

        averageSpeed = averageSpeed / laps.size();

        return averageSpeed;
    }

    public Duration getTotalTime() {
        if (totalTime != null) {
            return totalTime;
        }

        totalTime = Duration.ZERO;
        for (Map.Entry<Integer, Lap> entry : laps.entrySet()) {
            Lap lap = entry.getValue();
            totalTime = totalTime.plus(lap.getLapTime());
        }

        return totalTime;
    }
}
