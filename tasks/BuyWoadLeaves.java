package com.delimiter.rsbot.autowoader.tasks;

import com.delimiter.rsbot.autowoader.Task;
import org.powerbot.script.rt4.ClientContext;

public class BuyWoadLeaves extends Task {

    public BuyWoadLeaves(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        // if player has gp and is in the shop area
        return false;
    }

    @Override
    public void execute() {
        // interact with Wyson
        System.out.println("Buying woad leaves...");
    }
}
