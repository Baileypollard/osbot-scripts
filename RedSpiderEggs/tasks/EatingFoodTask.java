package RedSpiderEggs.tasks;

import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.utility.ConditionalSleep;

public class EatingFoodTask extends Task
{
    int eatPercentage = 50;

    public EatingFoodTask(MethodProvider api, String name)
    {
        super(api, name);
    }

    @Override
    public boolean canProcess()
    {
        return api.myPlayer().getHealthPercent() < eatPercentage
                && api.inventory.contains("Lobster");
    }

    @Override
    public void process() throws InterruptedException
    {
        if (api.inventory.contains("Lobster")) {
            if (api.inventory.getItem("Lobster").interact("Eat")) {
                new ConditionalSleep(2000) {
                    public boolean condition() {
                        return !api.myPlayer().isAnimating();
                    }
                }.sleep();
            }
            eatPercentage = util.Util.random(25, 60);
            api.log("Eating when HP goes below " + eatPercentage + "%");
        }
    }
}
