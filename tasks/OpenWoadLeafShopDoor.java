package com.delimiter.rsbot.autowoader.tasks;

import com.delimiter.rsbot.autowoader.AutoWoader;
import com.delimiter.rsbot.autowoader.Task;
import com.delimiter.rsbot.autowoader.util.Constants;
import com.delimiter.rsbot.autowoader.util.Util;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

public class OpenWoadLeafShopDoor extends Task {
    Util util = new Util(ctx);

    public OpenWoadLeafShopDoor(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        GameObject door = ctx.objects.select().id(24050).nearest().poll();
        return door.valid() && !util.chatting() && util.isAtWoadLeafShop() && !util.isWysonReachable();
    }

    @Override
    public void execute() {
        System.out.println("Opening door...");
        AutoWoader.status = Constants.Status.OPEN_SHOP_DOOR.getValue();
        GameObject door = ctx.objects.select().id(24050).nearest().poll();
        door.bounds(111, 131, -226, 0, -4, 134);

        if (!door.inViewport() || door.tile().distanceTo(ctx.players.local()) > 5) {
            ctx.camera.turnTo(door);
        }

        door.interact("Open");

        Condition.wait(() -> !door.valid(), 200, 10);
    }
}
