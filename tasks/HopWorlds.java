package com.delimiter.rsbot.autowoader.tasks;

import com.delimiter.rsbot.autowoader.AutoWoader;
import com.delimiter.rsbot.autowoader.Task;
import com.delimiter.rsbot.autowoader.util.Constants;
import com.delimiter.rsbot.autowoader.util.Util;
import com.delimiter.rsbot.autowoader.util.Walker;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Game;
import org.powerbot.script.rt4.World;

public class HopWorlds extends Task {

    Util util = new Util(ctx);
    Walker walker = new Walker(ctx);

    public HopWorlds(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return util.isUpstairsInWiseOldMansHouse();
    }

    @Override
    public void execute() {
        String world = "";
        int worldNumber = 0;

        AutoWoader.status = Constants.Status.HOPPING_WORLDS.getValue();

        if (ctx.game.tab() == Game.Tab.LOGOUT) {
            ctx.worlds.open();
        } else {
            ctx.game.tab(Game.Tab.LOGOUT);
        }

        if (ctx.widgets.widget(69).component(2).valid()) {
            world = ctx.widgets.widget(69).component(2).text();
            worldNumber = Integer.parseInt(world.replaceAll("[\\D]", ""));
        }

        ctx.worlds.open(); // Opens world switcher widget
        int finalWorldNumber = worldNumber;
        ctx.worlds.select(
                world1 -> world1.id() != finalWorldNumber
        )
                .joinable()
                .types(World.Type.FREE)
                .population(1000);

        World worldToHopTo = ctx.worlds.shuffle().peek();

        Condition.wait(()->worldToHopTo.hop(),1000,5);

        walker.walkPathReverse(Constants.ESCAPE_PATH);
    }
}
