package com.delimiter.rsbot.autowoader.tasks;

import com.delimiter.rsbot.autowoader.AutoWoader;
import com.delimiter.rsbot.autowoader.Task;
import com.delimiter.rsbot.autowoader.util.Constants;
import com.delimiter.rsbot.autowoader.util.Util;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;

public class BuyBlueDye extends Task {
    Util util = new Util(ctx);
    private int lastDyeCount = ctx.inventory.select().id(Constants.BLUE_DYE).count();

    public BuyBlueDye(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.chat.chatting() && util.isInAggiesShop();
    }

    @Override
    public void execute() {
        AutoWoader.status = Constants.Status.BUYING_BLUE_DYE.getValue();

        int currDyeCount = ctx.inventory.select().id(Constants.BLUE_DYE).count();

        Component chatContinue = ctx.components.select(Constants.WIDGETS_CONTINUABLE).text(Constants.CHAT_OPTION_CONTINUE).poll();
        Component chatEnd = ctx.components.select(193).text(Constants.CHAT_OPTION_BLUE_BOTTLE).poll();
        Component chatOptions = ctx.components.select(Constants.WIDGET_OPTIONS,1).text(
                Constants.CHAT_OPTION_MAKE_DYES,
                Constants.CHAT_OPTION_BLUE_DYE_MATERIALS,
                Constants.CHAT_OPTION_MAKE_BLUE_DYE).poll();

        if(chatEnd.visible()){
            ctx.input.send("{VK_SPACE}");
            Condition.wait(()->!chatEnd.visible(),50,20);
        }

        if(ctx.chat.canContinue() || chatContinue.visible()){
            ctx.input.send("{VK_SPACE}");
            Condition.wait(()->!chatContinue.visible(), 50, 20);
        }

        if (chatOptions.text().equals(Constants.CHAT_OPTION_MAKE_DYES)){
            ctx.chat.continueChat(true, Constants.CHAT_OPTION_MAKE_DYES);
            Condition.wait(()->!chatOptions.visible(), 50, 20);
        }

        if (chatOptions.text().equals(Constants.CHAT_OPTION_BLUE_DYE_MATERIALS)){
            ctx.chat.continueChat(true, Constants.CHAT_OPTION_BLUE_DYE_MATERIALS);
            Condition.wait(()->!chatOptions.visible(), 50, 20);
        }

        if (chatOptions.text().equals(Constants.CHAT_OPTION_MAKE_BLUE_DYE)){
            ctx.chat.continueChat(true, Constants.CHAT_OPTION_MAKE_BLUE_DYE);
            Condition.wait(()->!chatOptions.visible(), 50, 20);
        }

        if(lastDyeCount != currDyeCount) AutoWoader.blueDyeBought++;

        lastDyeCount = currDyeCount;
    }
}
