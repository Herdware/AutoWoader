package com.delimiter.rsbot.autowoader.tasks;

import com.delimiter.rsbot.autowoader.AutoWoader;
import com.delimiter.rsbot.autowoader.Task;
import com.delimiter.rsbot.autowoader.util.Constants;
import com.delimiter.rsbot.autowoader.util.Util;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.Bank;
import org.powerbot.script.rt4.ClientContext;

public class BankInDraynor extends Task {
    Util util = new Util(ctx);

    public BankInDraynor(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return !ctx.players.local().healthBarVisible() &&
                    (util.isInDraynorBank() && !AutoWoader.outOfWoad &&
                            (ctx.inventory.isFull() || (!util.hasEnoughGPToBuyOneBlueDye() || !util.hasEnoughWoadLeavesToBuyOneBlueDye())));
    }

    @Override
    public void execute() {
        AutoWoader.status = Constants.Status.BANKING_DRAYNOR.getValue();

        if (ctx.bank.opened()) {
            ctx.bank.deposit(Constants.BLUE_DYE, Bank.Amount.ALL);
            ctx.bank.depositAllExcept(Constants.COINS, Constants.WOAD_LEAF);

            if(!util.hasEnoughGPToBuyInventoryOfBlueDye()){
                Condition.wait(()->ctx.bank.withdraw(Constants.COINS, 500),500,4);
            }

            if(!util.hasEnoughWoadLeavesToBuyInventoryOfBlueDye()){
                Condition.wait(()->ctx.bank.withdraw(Constants.WOAD_LEAF, 500),500,4);
            }

            // No gp in inventory or bank, stop script
            if (!util.hasEnoughGPToBuyOneBlueDye()) {
                AutoWoader.status = "!! Out of GP !!!";
                ctx.controller.suspend();
            }else if (!util.hasWoadLeaves()){
                AutoWoader.outOfWoad = true;
            }

            ctx.bank.close();
        } else {
            if (ctx.bank.inViewport()) {
                if (ctx.bank.open()) {
                    Condition.wait(ctx.bank::opened, 250, 8);
                }
            } else {
                ctx.camera.turnTo(ctx.bank.nearest());
            }
        }
    }
}
