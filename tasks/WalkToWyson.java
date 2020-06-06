package com.delimiter.rsbot.autowoader.tasks;

import com.delimiter.rsbot.autowoader.Task;
import org.powerbot.script.rt4.ClientContext;

public class WalkToWyson extends Task {
    public WalkToWyson(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        // if player has gp and is not in shop
        return false;
    }

    @Override
    public void execute() {
        // walk to Wyson's shop
    }
}
