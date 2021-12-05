package utils;

import java.util.concurrent.TimeUnit;

public class Timer {
    private long currentTimeMs;
    private String measuredTime = "";

    public void start() {
        currentTimeMs = System.currentTimeMillis();
    }

    public String stop() {
        long endTimeMs = System.currentTimeMillis() - currentTimeMs;
        
        long hr  = TimeUnit.MILLISECONDS.toHours(endTimeMs);
        
        endTimeMs -= TimeUnit.HOURS.toMillis(hr);
        long min = TimeUnit.MILLISECONDS.toMinutes(endTimeMs);
        
        endTimeMs -= TimeUnit.MINUTES.toMillis(min);
        long sec = TimeUnit.MILLISECONDS.toSeconds(endTimeMs);

        measuredTime = String.format("%02d hr %02d min %02d sec", hr, min, sec);
        return measuredTime;
    }

    public String getMeasuredTime() {
        return measuredTime;
    }
}
