package com.delimiter.rsbot.autowoader.tasks;

import com.delimiter.rsbot.autowoader.AutoWoader;
import com.delimiter.rsbot.autowoader.Task;
import com.delimiter.rsbot.autowoader.util.Constants;
import com.delimiter.rsbot.autowoader.util.Util;
import com.delimiter.rsbot.autowoader.util.Walker;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class WalkInFalador extends Task
{
    Walker walker = new Walker(ctx);
    Util util = new Util(ctx);

    public WalkInFalador(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return !util.isInDraynor() && !util.hasReachedWoadLeafBuyLimit() &&
                (!ctx.chat.chatting() && (!util.hasEnoughGPToBuyOneWoadLeaf() && !util.isAtFaladorEastBank()) ||
                        (util.hasEnoughGPToBuyOneWoadLeaf() && !util.isAtWoadLeafShop()));
    }

    @Override
    public void execute() {
        if(!ctx.movement.running() && ctx.movement.energyLevel()> Random.nextInt(35,55)){
            ctx.movement.running(true);
        }

        if (!ctx.players.local().inMotion() || ctx.movement.destination().equals(Tile.NIL) || ctx.movement.destination().distanceTo(ctx.players.local()) < 5) {
            if(!util.hasEnoughGPToBuyOneWoadLeaf()){
                AutoWoader.status = Constants.Status.WALKING_TO_BANK.getValue();
                walker.walkPath(Constants.WYSON_TO_BANK);
            } else {
                AutoWoader.status = Constants.Status.WALKING_TO_WYSON.getValue();
                walker.walkPathReverse(Constants.WYSON_TO_BANK);
            }
        }
    }
}
