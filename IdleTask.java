import org.osbot.rs07.script.MethodProvider;


public class IdleTask extends Task {
    Timer timer = new Timer();
    long lastIdleTime = 0;
    long sleepTime = 0;

    public IdleTask(MethodProvider api, String name) {
        super(api, name);
        timer.start();
    }

    @Override
    public boolean canProcess() {
        return ((timer.elapsed() - lastIdleTime) > sleepTime) && api.myPlayer().isAnimating();
    }

    @Override
    public void process() throws InterruptedException {
        sleepTime = Util.random(1, 150000);
        lastIdleTime = timer.elapsed();

        if (api.camera.getYawAngle() <= 360) {
            int targetYaw = Util.random(0, 360);
            api.log("MOVING CAMERA TO YAW " + targetYaw);
            api.camera.moveYaw(targetYaw);
        }
        api.log("IDLING FOR " + sleepTime + " MS ");

    }
}
