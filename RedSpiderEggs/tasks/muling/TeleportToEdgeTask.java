package RedSpiderEggs.tasks.muling;

import RedSpiderEggs.constants.Location;
import RedSpiderEggs.tasks.Task;
import org.osbot.rs07.api.ui.EquipmentSlot;
import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.utility.ConditionalSleep;

public class TeleportToEdgeTask extends Task {

    boolean timeToMule = false;

    public TeleportToEdgeTask(MethodProvider api, String name) {
        super(api, name);
    }

    @Override
    public boolean canProcess() {
        return !Location.EDGEVILLE_LOCATION.getArea().contains(api.myPosition());
    }

    @Override
    public void process() throws InterruptedException {
        if (!Location.EDGEVILLE_LOCATION.getArea().contains(api.myPosition())) {
            if (!api.equipment.isWearingItem(EquipmentSlot.AMULET, "Amulet of glory") &&
                    api.equipment.interact(EquipmentSlot.AMULET, "Edgeville")) {
                new ConditionalSleep(2000) {
                    public boolean condition() throws InterruptedException {
                        return Location.EDGEVILLE_LOCATION.getArea().contains(api.myPosition());
                    }
                }.sleep();
            }
            if (api.tabs.getOpen() != Tab.INVENTORY) {
                api.tabs.open(Tab.INVENTORY);
            }
        }
    }
}
