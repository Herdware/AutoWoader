package com.delimiter.rsbot.autowoader.tasks;

import com.delimiter.rsbot.autowoader.AutoWoader;
import com.delimiter.rsbot.autowoader.Task;
import com.delimiter.rsbot.autowoader.util.Constants;
import com.delimiter.rsbot.autowoader.util.Util;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;

public class BuyWoadLeaves extends Task {
    Util utils = new Util(ctx);

    public BuyWoadLeaves(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.chat.chatting() && utils.isAtWoadLeafShop();
    }

    @Override
    public void execute() {
        AutoWoader.status = Constants.Status.BUYING_WOAD_LEAVES.getValue();

        Component chatContinue = ctx.components.select(Constants.WIDGETS_CONTINUABLE).text(Constants.CHAT_OPTION_CONTINUE).poll();
        Component chatEnd = ctx.components.select(Constants.WIDGET_OPPOSITION).text(Constants.CHAT_OPTION_GENEROUS).poll();
        Component chatOptions = ctx.components.select(Constants.WIDGET_OPTIONS,1).text(
                Constants.CHAT_OPTION_NEED_WOAD,
                Constants.CHAT_OPTION_OFFER).poll();

        if(ctx.chat.canContinue() || chatContinue.visible()){
            ctx.input.send("{VK_SPACE}");
            Condition.wait(()->!chatContinue.visible() || !chatEnd.visible(), 50, 20);
        }

        if (chatOptions.text().equals(Constants.CHAT_OPTION_NEED_WOAD)){
            ctx.chat.continueChat(true, Constants.CHAT_OPTION_NEED_WOAD);
            Condition.wait(()->!chatOptions.visible(), 50, 20);
        }

        if (chatOptions.text().equals(Constants.CHAT_OPTION_OFFER)){
            ctx.chat.continueChat(true, Constants.CHAT_OPTION_OFFER);
            Condition.wait(()->!chatOptions.visible(), 50, 20);
        }
    }
}
