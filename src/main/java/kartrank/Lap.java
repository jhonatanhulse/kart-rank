package kartrank;

import java.time.Duration;
import java.time.LocalTime;

/**
 * Simple POJO representing one lap entry of kart rank
 */
public class Lap {

    private Integer number;

    private LocalTime time;

    private Duration lapTime;

    private Float averageSpeed;

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setLapTime(Duration lapTime) {
        this.lapTime = lapTime;
    }

    public Duration getLapTime() {
        return lapTime;
    }

    public void setAverageSpeed(Float averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    public Float getAverageSpeed() {
        return averageSpeed;
    }
}
