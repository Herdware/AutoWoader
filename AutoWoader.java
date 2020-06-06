package com.delimiter.rsbot.autowoader;

import com.delimiter.rsbot.autowoader.tasks.BuyWoadLeaves;
import com.delimiter.rsbot.autowoader.tasks.Bank;
import com.delimiter.rsbot.autowoader.tasks.WalkToBank;
import com.delimiter.rsbot.autowoader.tasks.WalkToWyson;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Script.Manifest(name= "AutoWoader", description = "Woad you like some woad?", properties = "author=Delimiter; topic=999; client=4;")

public class AutoWoader extends PollingScript<ClientContext> {

    private List<com.delimiter.rsbot.autowoader.Task> taskList = new ArrayList<>();

    @Override
    public void start() {
        System.out.println("Ready to rock & woad!");
        taskList.addAll(
                Arrays.asList(
                        new Bank(ctx),
                        new BuyWoadLeaves(ctx),
                        new WalkToBank(ctx),
                        new WalkToWyson(ctx)
                )
        );
    }

    @Override
    public void poll() {
        for(Task task : taskList){
            if(task.activate()){
                task.execute();
            }
        }
    }
}
