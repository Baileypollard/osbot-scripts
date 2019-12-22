import org.osbot.rs07.script.MethodProvider;

public abstract class Task {
    protected MethodProvider api;

    private String status;

    public Task(MethodProvider api, String name) {
        this.api = api;
        this.status = name;
    }

    public abstract boolean canProcess();

    public abstract void process() throws InterruptedException;

    public void run() {
        if (canProcess()) {
            try {
                process();
            } catch (InterruptedException e) {
                api.log(e);
            }
        }
    }

    public String getStatus() {
        return status;
    }
} 