package Castle_Wars.tasks;

import Castle_Wars.constants.Location;
import Castle_Wars.constants.Team;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.utility.ConditionalSleep;

public class EnterPortalTask extends Task {

    private Team pickedTeam;

    public EnterPortalTask(MethodProvider api, String name, Team pickedTeam) {
        super(api, name);
        this.pickedTeam = pickedTeam;
    }

    private RS2Widget gameCompleteWidget = api.getWidgets().get(193, 2);

    @Override
    public boolean canProcess() {
        return Location.CASTLE_WARS_LOBBY.getArea().contains(api.myPosition());
    }

    @Override
    public void process() {
        api.log("ENTERING PORTAL...");

        RS2Object portal = api.getObjects().closest(pickedTeam.getPortalName());
        if (portal != null) {
            if (portal.interact("Enter") && !api.myPlayer().isMoving()) {
                new ConditionalSleep(2000) {
                    public boolean condition() throws InterruptedException {
                        return !Location.CASTLE_WARS_LOBBY.getArea().contains(api.myPosition());
                    }
                }.sleep();
            }
        }
    }

    public void setPickedTeam(Team newTeam) {
        this.pickedTeam = newTeam;
    }
}
