import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.utility.ConditionalSleep;

public class ClickPumpTask extends Task {
    public ClickPumpTask(MethodProvider api, String name) {
        super(api, name);
    }

    @Override
    public boolean canProcess() {
        RS2Object pump = api.getObjects().closest("Pump");
        return pump != null && pump.isVisible() && !api.myPlayer().isAnimating();
    }

    @Override
    public void process() throws InterruptedException {
        RS2Object pump = api.getObjects().closest("Pump");
        api.log("CLICKING PUMP...");
        if (pump.interact("Operate") && !api.myPlayer().isAnimating()) {
            new ConditionalSleep(2000) {
                public boolean condition() {
                    return api.myPlayer().isAnimating();
                }
            }.sleep();
        }
    }
}
