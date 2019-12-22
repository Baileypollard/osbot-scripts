package Castle_Wars.tasks;

import Castle_Wars.constants.Location;
import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.utility.ConditionalSleep;

public class JoinEarlyTask extends Task {
    private RS2Widget joinEarlyWidget = api.getWidgets().get(219, 1, 1);

    public JoinEarlyTask(MethodProvider api, String name) {
        super(api, name);
    }

    @Override
    public boolean canProcess() {

        return joinEarlyWidget != null && joinEarlyWidget.isVisible();
    }

    @Override
    public void process() throws InterruptedException {

        api.log("JOINING GAME EARLY...");
        if (joinEarlyWidget.interact("Continue")) {
            new ConditionalSleep(2000) {
                public boolean condition() throws InterruptedException {
                    return !Location.SARADOMIN_WAITING_LOBBY.getArea().contains(api.myPosition())
                            && !Location.ZAMORAK_WAITING_LOBBY.getArea().contains(api.myPosition());
                }
            }.sleep();
        }
    }
}
