package com.delimiter.rsbot.autowoader.tasks;

import com.delimiter.rsbot.autowoader.Task;
import org.powerbot.script.rt4.ClientContext;

public class WalkToBank extends Task
{
    public WalkToBank(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        //If player has no gp and is not in the bank area
        return false;
    }

    @Override
    public void execute() {
        //turn on run
        //use walker class to go to bank area
        System.out.println("Walking to bank...");
    }
}
