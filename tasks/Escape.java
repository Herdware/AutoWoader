package com.delimiter.rsbot.autowoader.tasks;

import com.delimiter.rsbot.autowoader.AutoWoader;
import com.delimiter.rsbot.autowoader.Task;
import com.delimiter.rsbot.autowoader.util.Constants;
import com.delimiter.rsbot.autowoader.util.Util;
import com.delimiter.rsbot.autowoader.util.Walker;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

public class Escape extends Task {
    Util util = new Util(ctx);
    Walker walker = new Walker(ctx);

    public Escape(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.players.local().healthBarVisible() && util.isInDraynor();
    }

    @Override
    public void execute() {
        if(!ctx.movement.running() && ctx.players.local().healthBarVisible()){
            ctx.movement.running(true);
        }

        if(!util.isUpstairsInWiseOldMansHouse()){
            AutoWoader.status = Constants.Status.ESCAPING.getValue();
            walker.walkPath(Constants.ESCAPE_PATH);

            GameObject door = ctx.objects.select().id(1535).nearest().poll();

            door.bounds(new int[]{4, 124, -236, 0, -4, 20});

            if(door.valid() && door.inViewport() && ctx.movement.distance(door) < 4){
                door.interact("Open");
                Condition.wait(()->!door.valid(), 250, 12);
            }else{
                ctx.camera.turnTo(door);
            }
        }
    }
}
