package RedSpiderEggs.tasks.muling;

import RedSpiderEggs.tasks.Task;
import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.utility.ConditionalSleep;

public class TradeMuleTask extends Task {

    public TradeMuleTask(MethodProvider api, String name) {
        super(api, name);
    }

    @Override
    public boolean canProcess() {
        return api.inventory.contains(224) && !api.bank.isOpen();
    }

    @Override
    public void process() throws InterruptedException {
        Player mule = api.players.closest("Naturre");

        if (mule != null) {
            if (mule.interact("Trade with")) {
                new ConditionalSleep(2000) {
                    public boolean condition() throws InterruptedException {
                        return api.trade.isCurrentlyTrading();
                    }
                }.sleep();
            }
            if (api.trade.isCurrentlyTrading()) {
                if (api.trade.offerAll(224)) {
                    if (api.trade.acceptTrade()) {
                        new ConditionalSleep(2000) {
                            public boolean condition() throws InterruptedException {
                                return api.trade.isSecondInterfaceOpen();
                            }
                        }.sleep();
                    }
                }
                if (api.trade.isSecondInterfaceOpen()) {
                    if (api.trade.acceptTrade()) {
                        new ConditionalSleep(2000) {
                            public boolean condition() throws InterruptedException {
                                return !api.trade.isCurrentlyTrading();
                            }
                        }.sleep();
                    }
                }
            }
        }
    }
}
