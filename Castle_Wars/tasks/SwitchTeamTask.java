package Castle_Wars.tasks;

import Castle_Wars.constants.Location;
import Castle_Wars.constants.Team;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.utility.ConditionalSleep;

public class SwitchTeamTask extends Task {

    private Team pickedTeam;
    private Team newTeam;

    public SwitchTeamTask(MethodProvider api, String name, Team pickedTeam) {
        super(api, name);
        this.pickedTeam = pickedTeam;
        this.newTeam = pickedTeam;
    }

    @Override
    public boolean canProcess() {
        return pickedTeam != newTeam && (Location.SARADOMIN_WAITING_LOBBY.getArea().contains(api.myPosition())
                || Location.ZAMORAK_WAITING_LOBBY.getArea().contains(api.myPosition()));
    }

    @Override
    public void process() throws InterruptedException {
        RS2Object exitPortal = api.getObjects().closest("Portal");

        if (exitPortal != null) {
            if (exitPortal.interact("Exit") && !api.myPlayer().isMoving()) {
                new ConditionalSleep(2000) {
                    public boolean condition() {
                        return Location.CASTLE_WARS_LOBBY.getArea().contains(api.myPosition());
                    }
                }.sleep();
            }
        }
        this.pickedTeam = this.newTeam;
    }

    public void setNewTeam(Team newTeam) {
        this.newTeam = newTeam;
    }
}
