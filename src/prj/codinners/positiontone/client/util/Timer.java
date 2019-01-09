package prj.codinners.positiontone.client.util;

public class Timer {
    private double millisecond;

    private double endTime;

    public Timer(double millisecond) {
        this.millisecond = millisecond;
    }

    public void start() {
        endTime = System.currentTimeMillis() + millisecond;
    }

    public boolean check() {
        return System.currentTimeMillis() >= endTime;
    }
}
