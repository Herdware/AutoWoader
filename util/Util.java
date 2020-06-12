package com.delimiter.rsbot.autowoader.util;

import com.delimiter.rsbot.autowoader.AutoWoader;
import org.powerbot.script.rt4.ClientContext;

public class Util {

    private final ClientContext ctx;

    public Util(ClientContext ctx) {
        this.ctx = ctx;
    }

    public boolean isAtWoadLeafShop(){
        return Constants.FALADOR_WOADLEAF_SHOP_AREA.contains(ctx.players.local());
    }

    public boolean isInAggiesShop(){
        return Constants.AGGIES_SHOP.contains(ctx.players.local());
    }

    public boolean isInDraynor(){
        return Constants.DRAYNOR.contains(ctx.players.local());
    }

    public boolean isInFalador(){
        return Constants.FALADOR.contains(ctx.players.local());
    }

    public boolean isInDraynorBank(){
        return Constants.DRAYNOR_BANK.contains(ctx.players.local());
    }

    public boolean isAtFaladorEastBank(){
        return Constants.FALADOR_EAST_BANK.contains(ctx.players.local());
    }

    public boolean isUpstairsInWiseOldMansHouse(){
        return Constants.WISE_OLD_MAN_UPSTAIRS.contains(ctx.players.local());
    }

    public boolean isWysonReachable(){
        return ctx.npcs.select().id(Constants.WYSON_THE_GARDENER).nearest().poll().tile().matrix(ctx).reachable();
    }

    public boolean hasWoadLeaves(){
        return ctx.inventory.select().id(Constants.WOAD_LEAF).count(true) >= 2;
    }

    public boolean hasReachedWoadLeafBuyLimit(){
        return ctx.inventory.select().id(Constants.WOAD_LEAF).count(true) >= AutoWoader.woadBuyLimit;
    }

    public boolean hasEnoughGPToBuyOneWoadLeaf(){
        return ctx.inventory.select().id(Constants.COINS).count(true) >= 20;
    }

    public boolean hasEnoughGPToBuyOneBlueDye(){
        return ctx.inventory.select().id(Constants.COINS).count(true) >= 5;
    }

    public boolean hasEnoughGPToBuyInventoryOfBlueDye(){
        return ctx.inventory.select().id(Constants.COINS).count(true) >= 130;
    }

    public boolean hasEnoughWoadLeavesToBuyOneBlueDye(){
        return ctx.inventory.select().id(Constants.WOAD_LEAF).count(true) >= 2;
    }

    public boolean hasEnoughWoadLeavesToBuyInventoryOfBlueDye(){
        return ctx.inventory.select().id(Constants.WOAD_LEAF).count(true) >= 52;
    }

    public boolean chatting() {
        return ctx.widgets.widget(Constants.WIDGET_OPPOSITION).componentCount() > 0 ||
                ctx.widgets.widget(Constants.WIDGET_OPTIONS).componentCount() > 0 ||
                ctx.widgets.widget(Constants.WIDGET_SELF).componentCount() > 0;
    }
}
