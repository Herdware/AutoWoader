package com.delimiter.rsbot.autowoader.tasks;

import com.delimiter.rsbot.autowoader.AutoWoader;
import com.delimiter.rsbot.autowoader.Task;
import com.delimiter.rsbot.autowoader.util.Constants;
import com.delimiter.rsbot.autowoader.util.Util;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Npc;

public class FindWyson extends Task {
    Util util = new Util(ctx);

    public FindWyson(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return !util.hasReachedWoadLeafBuyLimit() &&
                util.isAtWoadLeafShop() && util.hasEnoughGPToBuyOneWoadLeaf() &&
                !util.chatting() && !ctx.chat.pendingInput();
    }

    @Override
    public void execute() {
        AutoWoader.status = Constants.Status.FINDING_WYSON.getValue();

        final Npc wyson = ctx.npcs.select().id(Constants.WYSON_THE_GARDENER).nearest().poll();

        if (!wyson.valid()) return;

        if (wyson.inViewport()) {
            wyson.interact("Talk-to");
            Condition.wait(()->util.chatting(), 250, 8);
        } else {
            ctx.camera.turnTo(wyson);
        }
    }
}
