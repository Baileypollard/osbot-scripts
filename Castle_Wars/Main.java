package Castle_Wars;

import Castle_Wars.constants.Team;
import Castle_Wars.gui.CastleWarsGUI;
import Castle_Wars.tasks.*;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.ui.Message;

import util.Util;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;


@ScriptManifest(author = "Snacking Rat", info = "Castle Wars RatPumper", logo = "", name = "RatsCastleWars", version = 1)
public class Main extends Script {
    private CastleWarsGUI gui;

    private ArrayList<Task> tasks = new ArrayList<Task>();

    private long startTime = 0;
    private int ticketsGained = 0;
    private int startTickets = 0;
    private int ticketsPerHour = 0;
    private String status;

    private Team defaultTeam;

    private List<String> ccCallerNames;

    private EnterPortalTask enterPortalTask;
    private SwitchTeamTask switchTeamTask;

    @Override
    public void onStart(){
        status = "STARTING UP...";
        startTime = System.currentTimeMillis();

        Item tickets = inventory.getItem("Castle wars ticket");
        if (tickets != null)
            startTickets = inventory.getItem("Castle wars ticket").getAmount();

         SwingUtilities.invokeLater(() -> {
                gui = new CastleWarsGUI();
                gui.setLocationRelativeTo(bot.getCanvas());
                gui.getStartButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        onClickStart();
                    }
                });
                gui.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                       stop(false);
                    }
                });
            });
    }

    public void onClickStart() {
        defaultTeam = gui.getSelectedTeam();

        getCallerNames();

        enterPortalTask = new EnterPortalTask(this, "ENTERING PORTAL...", defaultTeam);
        switchTeamTask = new SwitchTeamTask(this, "SWITCHING TEAMS", defaultTeam);

        tasks.add(enterPortalTask);
        tasks.add(switchTeamTask);
        tasks.add(new IdleTask(this, "IDLING..."));
        tasks.add(new ClimbLadderTask(this, "CLIMBING LADDER..."));
        tasks.add(new JoinEarlyTask(this, "JOINING EARLY"));

        gui.getGUI().setVisible(false);
        gui.getGUI().dispose();
    }

    public void getCallerNames() {
        ccCallerNames = new ArrayList<>();
        ccCallerNames.add("MurdaNotNice");
        ccCallerNames.add("Brain Pains");
        ccCallerNames.add("ggay");
        ccCallerNames.add("Avasdaddy");
    }

    @Override
    public int onLoop() {
        if (tasks.isEmpty())
            return Util.random(200, 1200);

        tasks.forEach(task ->  {
            if (task.canProcess()) {
                try {
                    task.process();
                    status = task.getStatus();
                } catch (InterruptedException e) {
                    log(e);
                }
            }
        } );
        return Util.random(200, 1200);
    }

    @Override
    public void onMessage(Message message){
        if(message.getTypeId() == 9){
            if (ccCallerNames.contains(message.getUsername())) {
                log(message.getMessage());
                if (message.getMessage().toLowerCase().contains("zam")) {
                    enterPortalTask.setPickedTeam(Team.ZAMORAK);
                    switchTeamTask.setNewTeam(Team.ZAMORAK);
                } else if (message.getMessage().toLowerCase().contains("sar")){
                    enterPortalTask.setPickedTeam(Team.SARADOMIN);
                    switchTeamTask.setNewTeam(Team.SARADOMIN);
                }
            }
        }
    }

    @Override
    public void onPaint(final Graphics2D g) {
        int currentTickets = 0;
        final long runTime = System.currentTimeMillis() - startTime;
        if (inventory.getItem("Castle wars ticket") != null) {
            currentTickets =  inventory.getItem("Castle wars ticket").getAmount();
        }

        ticketsGained = currentTickets - startTickets;
        ticketsPerHour = (int)(ticketsGained / (runTime / 3600000D));

        g.setColor(Color.WHITE);
        g.setFont(Font.getFont(Font.SANS_SERIF));
        g.drawString("RatsCastleWars", 10, 230);
        g.drawString("Runtime: " + Util.formatTime(runTime), 10, 244);
        g.drawString("Tickets Gained: " + ticketsGained + " (" + ticketsPerHour + "/hr) ", 10, 258);
        g.drawString("Status: " + status,10, 272);
    }

    @Override
    public void onExit() throws InterruptedException {
        if (gui != null) {
            SwingUtilities.invokeLater(gui::dispose);
        }
    }

}
