package RedSpiderEggs.tasks;

import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.utility.ConditionalSleep;

public class OpenLootingBagTask extends Task {

    private int closedLootingBag = 11941;
    private int openLootingBag = 22586;

    public OpenLootingBagTask(MethodProvider api, String name) {
        super(api, name);
    }

    @Override
    public boolean canProcess() {
        return api.getInventory().contains(closedLootingBag);
    }

    @Override
    public void process() throws InterruptedException {

        if (api.getInventory().interact("Open", closedLootingBag)) {
            new ConditionalSleep(2000) {
                public boolean condition() throws InterruptedException {
                    return api.getInventory().contains(openLootingBag);
                }
            }.sleep();
        }
    }
}
