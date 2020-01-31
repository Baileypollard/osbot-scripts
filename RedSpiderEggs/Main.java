package RedSpiderEggs;

import RedSpiderEggs.tasks.*;
import org.osbot.rs07.api.ui.Message;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;
import util.Util;

import java.awt.*;

import java.util.ArrayList;


@ScriptManifest(author = "Snacking Rat", info = "Red Spider Egg Collector", logo = "", name = "RatsSpiderEggs", version = 1)
public class Main extends Script {

    private ArrayList<Task> tasks = new ArrayList<Task>();
    private String status = "";

    @Override
    public void onStart(){
        status = "STARTING UP...";
        tasks.add(new BankingTask(this, "BANKING..."));
        tasks.add(new OpenLootingBagTask(this, "OPENING LOOTING BAG..."));
        tasks.add(new WalkToEdgevilleLadderTask(this, "WALKING TO LADDER..."));
        tasks.add(new WalkToSpiderEggsTask(this, "WALKING TO EGGS..."));
        tasks.add(new CollectingEggsTask(this, "COLLECTING EGGS..."));
    }


    @Override
    public int onLoop() {
        if (tasks.isEmpty())
            return Util.random(200, 1200);

        tasks.forEach(task ->  {
            if (task.canProcess()) {
                try {
                    status = task.getStatus();
                    this.log(status);
                    task.process();
                } catch (InterruptedException e) {
                    log(e);
                }
            }
        } );
        return Util.random(200, 1200);
    }

    @Override
    public void onMessage(Message message){

    }

    @Override
    public void onPaint(final Graphics2D g) {
//        int currentTickets = 0;
//        final long runTime = System.currentTimeMillis() - startTime;
//        if (inventory.getItem("Castle wars ticket") != null) {
//            currentTickets =  inventory.getItem("Castle wars ticket").getAmount();
//        }
//
//        ticketsGained = currentTickets - startTickets;
//        ticketsPerHour = (int)(ticketsGained / (runTime / 3600000D));
//
//        g.setColor(Color.WHITE);
//        g.setFont(Font.getFont(Font.SANS_SERIF));
//        g.drawString("RatsCastleWars", 10, 230);
//        g.drawString("Runtime: " + Util.formatTime(runTime), 10, 244);
//        g.drawString("Tickets Gained: " + ticketsGained + " (" + ticketsPerHour + "/hr) ", 10, 258);
//        g.drawString("Status: " + status,10, 272);
    }

    @Override
    public void onExit() throws InterruptedException {

    }

}
