package RedSpiderEggs.tasks;
import RedSpiderEggs.constants.Location;
import org.osbot.rs07.api.map.constants.Banks;

import org.osbot.rs07.api.ui.EquipmentSlot;
import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.utility.ConditionalSleep;

public class BankingTask extends Task {
    public BankingTask(MethodProvider api, String name) {
        super(api, name);
    }

    @Override
    public boolean canProcess() {
        return api.inventory.isFull();
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
        } else {
            if (!Banks.EDGEVILLE.contains(api.myPosition())) {
                api.walking.webWalk(Banks.EDGEVILLE.getRandomPosition());
            }
            if (!api.bank.isOpen()) {
                if (api.bank.open()) {
                    api.bank.depositAllExcept("Looting bag");
                    depositLootingBag();
                }
            }
        }
    }

    private void depositLootingBag() {
        if (api.inventory.contains("Looting bag")) {
            if (api.inventory.interact("View", "Looting bag")) {
                new ConditionalSleep(2000) {
                    public boolean condition() {
                        return api.getWidgets().get(15, 6) != null;
                    }
                }.sleep();
            }

            RS2Widget depositWidget = api.getWidgets().get(15,6);
            int x = depositWidget.getAbsX();
            int y = depositWidget.getAbsY();

            if (depositWidget != null) {
                if (api.mouse.move(x, y)) {
                    api.mouse.click(false);
                }
            }

        }
    }
}


