package com.delimiter.rsbot.autowoader.tasks;

import com.delimiter.rsbot.autowoader.AutoWoader;
import com.delimiter.rsbot.autowoader.Task;
import com.delimiter.rsbot.autowoader.util.Constants;
import com.delimiter.rsbot.autowoader.util.Util;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;

public class BankInFalador extends Task {
    Util util = new Util(ctx);

    public BankInFalador(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return !util.hasEnoughGPToBuyOneWoadLeaf() && util.isAtFaladorEastBank();
    }

    @Override
    public void execute() {
        AutoWoader.status = Constants.Status.BANKING_FALADOR.getValue();

        if (ctx.bank.opened()) {
            ctx.bank.depositAllExcept(Constants.WOAD_LEAF);
            ctx.bank.withdraw(Constants.COINS, 10000);

            ctx.bank.close();

            // No gp in inventory or bank, stop script
            if (!util.hasEnoughGPToBuyOneWoadLeaf()) {
                AutoWoader.status = Constants.Status.NO_GP.getValue();
                ctx.controller.suspend();
            }
        }
        else {
            if(ctx.bank.inViewport()) {
                if(ctx.bank.open()) {
                    Condition.wait(ctx.bank::opened, 250, 8);
                }
            }
            else {
                ctx.camera.turnTo(ctx.bank.nearest());
            }
        }
    }
}
