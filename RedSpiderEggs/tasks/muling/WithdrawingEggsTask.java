package RedSpiderEggs.tasks.muling;

import RedSpiderEggs.constants.Location;
import RedSpiderEggs.tasks.Task;
import org.osbot.rs07.api.Bank;
import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.utility.ConditionalSleep;

public class WithdrawingEggsTask extends Task {

    public WithdrawingEggsTask(MethodProvider api, String name) {
        super(api, name);
    }

    @Override
    public boolean canProcess() {
        return Location.EDGEVILLE_LOCATION.getArea().contains(api.myPosition()) &&
                !api.inventory.contains(224);
    }

    @Override
    public void process() throws InterruptedException {
        if (!Banks.EDGEVILLE.contains(api.myPosition())) {
            api.walking.webWalk(Banks.EDGEVILLE.getRandomPosition());
        }

        if (!api.bank.isOpen()) {
            if (api.bank.open()) {
                if (api.bank.enableMode(Bank.BankMode.WITHDRAW_NOTE)) {
                    if (api.bank.withdrawAll("Red spiders' eggs")) {
                        new ConditionalSleep(2000) {
                            public boolean condition() throws InterruptedException {
                                return api.inventory.contains(224);
                            }
                        }.sleep();
                    }

                    if (api.bank.isOpen()) {
                        if (api.bank.close()) {
                            new ConditionalSleep(2000) {
                                public boolean condition() throws InterruptedException {
                                    return !api.bank.isOpen();
                                }
                            }.sleep();
                        }
                    }
                }
            }
        }
    }
}
