package RedSpiderEggs.tasks;

import RedSpiderEggs.constants.Location;
import org.osbot.rs07.api.model.GroundItem;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.utility.ConditionalSleep;

public class CollectingEggsTask extends Task {

    public CollectingEggsTask(MethodProvider api, String name) {
        super(api, name);
    }

    @Override
    public boolean canProcess() {
        return api.myPlayer().getHealthPercent() > 35 && !api.inventory.isFull()
                && Location.SPIDER_EGG_LOCATION.getArea().contains(api.myPosition())
                && !api.myPlayer().isMoving();
    }

    @Override
    public void process() throws InterruptedException {
        GroundItem redSpiderEgg = api.getGroundItems().closest("Red spiders' eggs");
        if (redSpiderEgg != null) {
            if (redSpiderEgg.interact("Take")) {
                new ConditionalSleep(3000) {
                    public boolean condition() throws InterruptedException {
                        return !api.myPlayer().isMoving();
                    }
                }.sleep();
            }
        }
    }
}
