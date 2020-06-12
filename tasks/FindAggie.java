package com.delimiter.rsbot.autowoader.tasks;

import com.delimiter.rsbot.autowoader.AutoWoader;
import com.delimiter.rsbot.autowoader.Task;
import com.delimiter.rsbot.autowoader.util.Constants;
import com.delimiter.rsbot.autowoader.util.Util;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Npc;

public class FindAggie extends Task {
    Util util = new Util(ctx);

    public FindAggie(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return util.isInAggiesShop() && util.hasEnoughGPToBuyOneBlueDye() &&
                util.hasEnoughWoadLeavesToBuyOneBlueDye() && !util.chatting() &&
                !ctx.chat.pendingInput() && !ctx.inventory.isFull() && !AutoWoader.outOfWoad;
    }

    @Override
    public void execute() {
        AutoWoader.status = Constants.Status.FINDING_AGGIE.getValue();

        final Npc aggie = ctx.npcs.select().id(Constants.AGGIE_THE_WITCH).nearest().poll();

        if (!aggie.valid()) return;

        if (aggie.inViewport()) {
            aggie.interact("Talk-to");
            Condition.wait(()->util.chatting(), 100, 10);
        } else {
            ctx.camera.turnTo(aggie);
        }
    }
}
