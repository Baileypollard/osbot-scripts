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

        if (api.camera.getPitchAngle() <= 67) {
            int targetPitch = Util.random(0, 67);
            api.log("MOVING CAMERA TO PITCH " + targetPitch);
            api.camera.movePitch(targetPitch);
        }
        api.log("IDLING FOR " + sleepTime + " MS ");

    }
}
