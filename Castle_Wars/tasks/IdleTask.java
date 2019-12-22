package Castle_Wars.tasks;

import Castle_Wars.constants.Location;
import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.utility.ConditionalSleep;
import util.Timer;
import util.Util;

public class IdleTask extends Task {
    Timer timer = new Timer();
    long lastIdleTime = 0;
    long sleepTime = 0;
    private RS2Widget joinEarlyWidget = api.getWidgets().get(219, 1, 1);

    public IdleTask(MethodProvider api, String name) {
        super(api, name);
        timer.start();
    }

    @Override
    public boolean canProcess() {
        return ((timer.elapsed() - lastIdleTime) > sleepTime) && joinEarlyWidget == null
                && (Location.SARADOMIN_WAITING_LOBBY.getArea().contains(api.myPosition())
                || Location.ZAMORAK_WAITING_LOBBY.getArea().contains(api.myPosition())
                || Location.SARADOMIN_IDLE_AREA.getArea().contains(api.myPosition())
                || Location.ZAMORAK_IDLE_AREA.getArea().contains(api.myPosition()));
    }

    @Override
    public void process() throws InterruptedException {
        sleepTime = Util.random(1, 200000);
        lastIdleTime = timer.elapsed();

        if (api.camera.getPitchAngle() <= 67) {
            int targetPitch = Util.random(0, 67);
            api.log("MOVING CAMERA TO PITCH " + targetPitch);
            api.camera.movePitch(targetPitch);
        }
        api.log("IDLING FOR " + sleepTime + " MS ");

    }
}
