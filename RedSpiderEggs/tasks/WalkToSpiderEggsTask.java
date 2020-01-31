package RedSpiderEggs.tasks;

import RedSpiderEggs.constants.Location;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.script.MethodProvider;

public class WalkToSpiderEggsTask extends Task {
    Position spiderPos = new Position(3121, 9953, 0);

    public WalkToSpiderEggsTask(MethodProvider api, String name) {
        super(api, name);
    }

    @Override
    public boolean canProcess() {
        return !api.inventory.isFull() &&
                !Location.SPIDER_EGG_LOCATION.getArea().contains(api.myPosition());
    }

    @Override
    public void process() throws InterruptedException {
        if (api.walking.webWalk(spiderPos)) {

        }
    }
}
