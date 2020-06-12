package com.delimiter.rsbot.autowoader.tasks;

import com.delimiter.rsbot.autowoader.AutoWoader;
import com.delimiter.rsbot.autowoader.Task;
import com.delimiter.rsbot.autowoader.util.Constants;
import com.delimiter.rsbot.autowoader.util.Util;
import com.delimiter.rsbot.autowoader.util.Walker;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class WalkBetweenFaladorAndDraynor extends Task {
    Util util = new Util(ctx);
    Walker walker = new Walker(ctx);

    public WalkBetweenFaladorAndDraynor(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return (AutoWoader.outOfWoad && !util.isInFalador()) ||
                (util.hasReachedWoadLeafBuyLimit() && !util.isInDraynor());
    }

    @Override
    public void execute() {
        if(!ctx.movement.running() && ctx.movement.energyLevel()> Random.nextInt(35,55)){
            ctx.movement.running(true);
        }

        if (!ctx.players.local().inMotion() || ctx.movement.destination().equals(Tile.NIL) ||
                ctx.movement.destination().distanceTo(ctx.players.local()) < 5) {
            if(AutoWoader.outOfWoad){
                AutoWoader.status = Constants.Status.WALKING_TO_FALADOR.getValue();
                walker.walkPathReverse(Constants.FALADOR_BANK_TO_DRAYNOR_BANK);
            } else {
                AutoWoader.status = Constants.Status.WALKING_TO_DRAYNOR.getValue();
                walker.walkPath(Constants.FALADOR_BANK_TO_DRAYNOR_BANK);
            }
        }
    }
}
