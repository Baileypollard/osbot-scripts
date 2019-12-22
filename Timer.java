public class Timer {

    private boolean running = false;

    private long startTime = 0;
    private long stopTime = 0;

    public void start() {
        this.startTime = System.currentTimeMillis();
        this.running = true;
    }

    public void stop() {
        this.stopTime = System.currentTimeMillis();
        this.running = false;
    }
    public boolean isRunning() {
        return isRunning();
    }
    public long elapsed() {
        long elapsed;
        if(running)
            elapsed = (System.currentTimeMillis() - startTime);
        else
            elapsed = (stopTime - startTime);
        return elapsed;
    }

    public int getHourly(int number) {
        return (int) (number * 3600000.0D / elapsed());
    }

    public String getFormattedString() {
        long time = elapsed() / 1000;
        return String.format("%02d:%02d:%02d", time / 3600, (time % 3600) / 60, (time % 60));
    }
}
