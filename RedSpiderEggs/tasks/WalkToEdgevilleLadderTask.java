package RedSpiderEggs.tasks;

import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.api.ui.EquipmentSlot;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.utility.ConditionalSleep;

public class WalkToEdgevilleLadderTask extends Task {
    Position ladderPos = new Position(3094, 3470, 0);

    public WalkToEdgevilleLadderTask(MethodProvider api, String name) {
        super(api, name);
}

    @Override
    public boolean canProcess() {
        return readyToGo() && api.myPosition().getY() < 9869 ;
    }

    @Override
    public void process() throws InterruptedException {
        if (api.walking.webWalk(ladderPos)) {
            RS2Object closedTrapDoor = api.getObjects().closest(1579);

            if (closedTrapDoor != null) {
                if (closedTrapDoor.interact("Open")) {
                    new ConditionalSleep(2000) {
                        public boolean condition() throws InterruptedException {
                            return api.getObjects().closest(1581) != null;
                        }
                    }.sleep();
                }
            }
            RS2Object openedTrapDoor = api.getObjects().closest(1581);
            if (openedTrapDoor != null) {
                if (openedTrapDoor.interact("Climb-down")) {
                    new ConditionalSleep(2000) {
                        public boolean condition() throws InterruptedException {
                            return api.myPosition().getY() > 9869;
                        }
                    }.sleep();
                }
            }
        }
    }


    private boolean readyToGo() {
        return !api.inventory.isFull() && api.equipment.isWearingItemThatContains(EquipmentSlot.AMULET, "Amulet of glory(");
    }
}
