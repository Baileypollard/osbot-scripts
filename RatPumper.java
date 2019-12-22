import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

import java.awt.*;
import java.util.ArrayList;


@ScriptManifest(author = "Snacking Rat", info = "Pumper", logo = "", name = "RatsPumper", version = 1)
public class RatPumper extends Script {

    private ArrayList<Task> tasks = new ArrayList<Task>();
    private long startTime;

    @Override
    public void onStart(){
        startTime = System.currentTimeMillis();

        tasks.add(new ClickPumpTask(this, "CLICKING PUMP..."));
        tasks.add(new IdleTask(this, "IDLING..."));
    }

    @Override
    public int onLoop() {
        tasks.forEach(task ->  {
            if (task.canProcess()) {
                try {
                    task.process();
                } catch (InterruptedException e) {
                    log(e);
                }
            }
        } );
        return Util.random(200, 1200);
    }

    @Override
    public void onPaint(final Graphics2D g) {
        final long runTime = System.currentTimeMillis() - startTime;

        g.setColor(Color.WHITE);
        g.setFont(Font.getFont(Font.SANS_SERIF));
        g.drawString("RatsPumper", 10, 230);
        g.drawString("Pumping for: " + Util.formatTime(runTime), 10, 244);
    }
}
