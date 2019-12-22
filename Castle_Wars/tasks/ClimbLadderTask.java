package Castle_Wars.tasks;

import Castle_Wars.constants.Location;
import Castle_Wars.constants.Team;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.utility.ConditionalSleep;

public class ClimbLadderTask extends Task {

    public ClimbLadderTask(MethodProvider api, String name) {
        super(api, name);
    }

    @Override
    public boolean canProcess() {
        return Location.SARADOMIN_GAME_LOBBY.getArea().contains(api.myPosition())
                || Location.ZAMORAK_GAME_LOBBY.getArea().contains(api.myPosition());
    }

    @Override
    public void process() throws InterruptedException {
        api.log("CLIMBING LADDER...");

        int ladderId = Location.SARADOMIN_GAME_LOBBY.getArea().contains(api.myPosition()) ? 6280 : 6281;

        RS2Object ladder = api.getObjects().closest(ladderId);
        if (ladder != null) {
            if (ladder.interact("Climb-up")) {
                new ConditionalSleep(2500) {
                    public boolean condition() throws InterruptedException {
                        return !Location.SARADOMIN_GAME_LOBBY.getArea().contains(api.myPosition())
                                && !Location.ZAMORAK_GAME_LOBBY.getArea().contains(api.myPosition());
                    }
                }.sleep();
            }
        }
    }
}
