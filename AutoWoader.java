package com.delimiter.rsbot.autowoader;

import com.delimiter.rsbot.autowoader.tasks.*;
import com.delimiter.rsbot.autowoader.util.Constants;
import org.powerbot.script.*;
import org.powerbot.script.rt4.ClientContext;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Script.Manifest(name= "AutoWoader", description = "Woad you like some woad?", properties = "author=Delimiter; topic=999; client=4;")

public class AutoWoader extends PollingScript<ClientContext> implements PaintListener, MessageListener {

    public static int woadLeavesBought = 0, woadPerHour = 0, blueDyeBought = 0, blueDyePerHour = 0, woadBuyLimit=1000;
    public static String status = Constants.Status.STARTING.getValue();
    public static boolean outOfWoad = false;
    private final List<Task> taskList = new ArrayList<>();
    private final JFrame frame = new JFrame("Woader options");
    BufferedImage woadLeaves, blueDye;

    @Override
    public void start() {
        System.out.println("AutoWoader started. Ready to rock & woad!");

        try{
            woadLeaves = ImageIO.read(new URL("https://vignette.wikia.nocookie.net/runescape2/images/e/e2/Woad_leaf_5.png"));
            blueDye = ImageIO.read(new URL("https://vignette.wikia.nocookie.net/runescape2/images/6/66/Blue_dye.png"));
        }catch(IOException err){
            System.out.println("Failed to fetch images");
            System.out.println(err);
        }

        frame.setAlwaysOnTop(true);
        frame.setLocationRelativeTo(null);
        Integer option = (Integer) JOptionPane.showInputDialog(frame,
                "How many woad would you like to buy\nbefore switching to making dyes?",
                "Woader options",
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Integer[]{250, 500, 1000, 5000},
                1000);

        if((option!=null)){
            woadBuyLimit = option;
        }

        taskList.addAll(
                Arrays.asList(
                        new Escape(ctx),
                        new HopWorlds(ctx),

                        new WalkBetweenFaladorAndDraynor(ctx),
                        new OpenWoadLeafShopDoor(ctx),

                        new FindWyson(ctx),
                        new FindAggie(ctx),
                        new BuyWoadLeaves(ctx),
                        new BuyBlueDye(ctx),
                        new WalkInFalador(ctx),
                        new BankInFalador(ctx),

                        new WalkInDraynor(ctx),
                        new BankInDraynor(ctx)
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

    @Override
    public void repaint(Graphics graphics) {
        woadPerHour = (int) (3600000d / getRuntime() * (double) (woadLeavesBought));
        blueDyePerHour = (int) (3600000d / getRuntime() * (double) (blueDyeBought));
        String woadPerHourString = woadPerHour + "";
        String blueDyePerHourString = blueDyePerHour + "";

        graphics.setColor(new Color(0, 0, 0, 200));
        graphics.fillRect(0, 0, 320, 90);

        graphics.setColor(new Color(255, 255, 255));
        graphics.drawRect(0, 0, 320, 90);

        graphics.setFont(new Font("SansSerif", Font.BOLD, 16));
        graphics.drawString("AutoWoader", 10, 20);
        graphics.setFont(new Font("SansSerif", Font.ITALIC, 12));
        graphics.drawString("By Delimiter", 10, 35);
        graphics.setFont(new Font("SansSerif", Font.PLAIN, 13));

        graphics.drawString("Runtime: " + formatTime(super.getRuntime()),10,60);

        graphics.drawString("Status: "+status, 10, 80);

        graphics.setFont(new Font("SansSerif", Font.PLAIN, 12));
        graphics.setColor(Color.yellow);
        graphics.drawString("Woads: " + woadLeavesBought,220,20);

        graphics.setColor(Color.green);
        graphics.drawString("Woad/hr: " + woadPerHourString,220,40);

        graphics.setColor(Color.yellow);
        graphics.drawString("Dyes: " + blueDyeBought,220,60);

        graphics.setColor(Color.green);
        graphics.drawString("Dye/hr: " + blueDyePerHourString,220,80);

        graphics.drawImage(woadLeaves , 180, 10, null);
        graphics.drawImage(blueDye , 185, 50, null);
    }

    @Override
    public void messaged(MessageEvent messageEvent) {
        final String msg = messageEvent.text().toLowerCase();
        if (msg.contains("wyson gives you a pair of woad leaves.")) {
            woadLeavesBought += 2;
            if(outOfWoad){
                outOfWoad = false;
            }
        }
    }

    @Override
    public long getRuntime() {
        return super.getRuntime();
    }

    public String formatTime(final long time) {
        final int sec = (int) (time / 1000), h = sec / 3600, m = sec / 60 % 60, s = sec % 60;
        return (h < 10 ? "0" + h : h) + ":" + (m < 10 ? "0" + m : m) + ":"
                + (s < 10 ? "0" + s : s);
    }
}
