package com.delimiter.rsbot.autowoader.tasks;

import com.delimiter.rsbot.autowoader.AutoWoader;
import com.delimiter.rsbot.autowoader.Task;
import com.delimiter.rsbot.autowoader.util.Constants;
import com.delimiter.rsbot.autowoader.util.Util;
import com.delimiter.rsbot.autowoader.util.Walker;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

public class WalkInDraynor extends Task {
    Util util = new Util(ctx);
    Walker walker = new Walker(ctx);

    public WalkInDraynor(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        /* Must be in the Draynor area AND:
            Have a full inventory and not be in the bank (walk to bank)
            OR
            Not have gp/woad and not be in bank (walk to bank)
            OR
            Not have a full inventory and not be in Aggie's shop and have woad/gp (walk to Aggie)
         */
        return util.isInDraynor() && !ctx.players.local().tile().equals(Constants.WISE_OLD_MAN_TOP_OF_STAIRS) &&
                ((ctx.inventory.isFull() && !util.isInDraynorBank()) ||
                ((!util.hasEnoughGPToBuyOneBlueDye() || !util.hasEnoughWoadLeavesToBuyOneBlueDye()) && !util.isInDraynorBank() && !AutoWoader.outOfWoad) ||
                (!ctx.inventory.isFull() && !util.isInAggiesShop() && util.hasEnoughGPToBuyOneBlueDye() && util.hasEnoughWoadLeavesToBuyOneBlueDye()));
    }

    @Override
    public void execute() {
        if(!ctx.movement.running() && ctx.movement.energyLevel()> Random.nextInt(35,55)){
            ctx.movement.running(true);
        }

        if (!ctx.players.local().inMotion() || ctx.movement.destination().equals(Tile.NIL) ||
                ctx.movement.destination().distanceTo(ctx.players.local()) < 5) {
            if(ctx.inventory.isFull() || (!util.hasEnoughGPToBuyOneBlueDye() || !util.hasEnoughWoadLeavesToBuyOneBlueDye())){
                AutoWoader.status = Constants.Status.WALKING_TO_BANK.getValue();
                walker.walkPath(Constants.AGGIE_TO_BANK);

                // Failsafe if door is in viewport but not clickable
                GameObject door = ctx.objects.select().id(1535).nearest().poll();
                if(door.valid() && util.isInAggiesShop()){
                    AutoWoader.status = Constants.Status.IMPROVING_ANGLE.getValue();
                    ctx.camera.turnTo(door);
                    AutoWoader.status = Constants.Status.WALKING_TO_BANK.getValue();
                }
            } else {
                AutoWoader.status = Constants.Status.WALKING_TO_AGGIE.getValue();
                walker.walkPathReverse(Constants.AGGIE_TO_BANK);
            }
        }
    }
}
