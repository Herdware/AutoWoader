package com.delimiter.rsbot.autowoader.tasks;

import com.delimiter.rsbot.autowoader.Task;
import org.powerbot.script.rt4.ClientContext;

public class Bank extends Task {
    public Bank(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        // if player has no gp and is in the bank area
        return false;
    }

    @Override
    public void execute() {
        //open bank and withdraw gp
        System.out.println("Banking...");
    }
}
