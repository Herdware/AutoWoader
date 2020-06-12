package com.delimiter.rsbot.autowoader.util;

import org.powerbot.script.Area;
import org.powerbot.script.Tile;

import java.awt.*;

public class Constants {
    // Status
    public enum Status
    {
        STARTING("Starting up"),
        BANKING_DRAYNOR("Banking at Draynor"),
        BANKING_FALADOR("Banking at Falador"),
        BUYING_BLUE_DYE("Buying blue dye"),
        BUYING_WOAD_LEAVES("Buying woad leaves"),
        FINDING_AGGIE("Finding Aggie"),
        FINDING_WYSON("Finding Wyson"),
        OPEN_SHOP_DOOR("Opening shop door"),
        WALKING_TO_FALADOR("Walking to Falador"),
        WALKING_TO_DRAYNOR("Walking to Draynor"),
        WALKING_TO_BANK("Walking to bank"),
        WALKING_TO_AGGIE("Walking to Aggie"),
        WALKING_TO_WYSON("Walking to Wyson"),
        HOPPING_WORLDS("Hopping worlds"),
        NO_GP("!! Out of GP !!!"),
        ESCAPING("Escaping!"),
        IMPROVING_ANGLE("Improving angle");

        private final String value;

        Status(String envValue) {
            this.value = envValue;
        }

        public String getValue() {
            return value;
        }
    }

    // Widgets
    public static final int WIDGET_OPPOSITION = 231;
    public static final int WIDGET_OPTIONS = 219;
    public static final int WIDGET_SELF = 217;
    public static final int[] WIDGETS_CONTINUABLE = {WIDGET_OPPOSITION, WIDGET_OPTIONS};

    // Items
    public static final int COINS = 995;
    public static final int WOAD_LEAF = 1793;
    public static final int BLUE_DYE = 1767;

    // NPCs
    public static final int WYSON_THE_GARDENER = 5422;
    public static final int AGGIE_THE_WITCH = 4284;

    // Wyson
    public static final String CHAT_OPTION_NEED_WOAD = "Yes please, I need woad leaves.";
    public static final String CHAT_OPTION_OFFER = "How about 20 coins?";
    public static final String CHAT_OPTION_CONTINUE = "Click here to continue";
    public static final String CHAT_OPTION_GENEROUS = "Here, have two, you're a generous person.";

    // Aggie
    public static final String CHAT_OPTION_MAKE_DYES = "Can you make dyes for me please?";
    public static final String CHAT_OPTION_BLUE_DYE_MATERIALS = "What do you need to make blue dye?";
    public static final String CHAT_OPTION_MAKE_BLUE_DYE = "Okay, make me some blue dye please.";
    public static final String CHAT_OPTION_BLUE_BOTTLE = "You hand the woad leaves and payment to Aggie. Aggie produces a blue bottle and hands it to you.";

    // Draynor Area
    public static final Tile WISE_OLD_MAN_TOP_OF_STAIRS = new Tile(3093,2351,1);

    public static final Area WISE_OLD_MAN_UPSTAIRS = new Area(
            new Tile(3087, 3251, 1),
            new Tile(3095, 3251, 1),
            new Tile(3095, 3256, 1),
            new Tile(3087, 3256, 1));

    public static final Area DRAYNOR = new Area(
            new Tile(3074, 3243, 0),
            new Tile(3084, 3235, 0),
            new Tile(3107, 3235, 0),
            new Tile(3107, 3269, 0),
            new Tile(3103, 3269, 0),
            new Tile(3103, 3266, 0),
            new Tile(3096, 3266, 0),
            new Tile(3096, 3265, 0),
            new Tile(3089, 3265, 0),
            new Tile(3081, 3265, 0),
            new Tile(3081, 3256, 0),
            new Tile(3074, 3256, 0));

    public static final Area AGGIES_SHOP = new Area(
            new Tile(3089, 3256, 0),
            new Tile(3089, 3262, 0),
            new Tile(3083, 3262, 0),
            new Tile(3083, 3256, 0));

    public static final Area DRAYNOR_BANK = new Area(
            new Tile(3098, 3240, 0),
            new Tile(3098, 3248, 0),
            new Tile(3088, 3248, 0),
            new Tile(3088, 3240, 0));

    // Falador area
    public static final Area FALADOR = new Area(
            new Tile(3019, 3355, 0),
            new Tile(3019, 3367, 0),
            new Tile(3020, 3368, 0),
            new Tile(3022, 3368, 0),
            new Tile(3025, 3371, 0),
            new Tile(3025, 3373, 0),
            new Tile(3027, 3375, 0),
            new Tile(3032, 3375, 0),
            new Tile(3032, 3378, 0),
            new Tile(3030, 3378, 0),
            new Tile(3030, 3381, 0),
            new Tile(3032, 3381, 0),
            new Tile(3032, 3384, 0),
            new Tile(3027, 3384, 0),
            new Tile(3022, 3389, 0),
            new Tile(3017, 3389, 0),
            new Tile(3015, 3391, 0),
            new Tile(2989, 3391, 0),
            new Tile(2989, 3377, 0),
            new Tile(2989, 3368, 0),
            new Tile(2992, 3368, 0),
            new Tile(2992, 3366, 0),
            new Tile(2995, 3366, 0),
            new Tile(2995, 3363, 0),
            new Tile(2995, 3355, 0));

    public static final Area FALADOR_WOADLEAF_SHOP_AREA = new Area(
            new Tile(3025, 3373, 0),
            new Tile(3027, 3375, 0),
            new Tile(3032, 3375, 0),
            new Tile(3032, 3378, 0),
            new Tile(3030, 3378, 0),
            new Tile(3030, 3381, 0),
            new Tile(3032, 3381, 0),
            new Tile(3032, 3384, 0),
            new Tile(3027, 3384, 0),
            new Tile(3024, 3387, 0),
            new Tile(3021, 3387, 0),
            new Tile(3021, 3373, 0)
    );

    public static final Area FALADOR_EAST_BANK = new Area(
            new Tile(3009, 3355, 0),
            new Tile(3019, 3355, 0),
            new Tile(3019, 3359, 0),
            new Tile(3009, 3359, 0)
    );

    // Paths
    public static final Tile[] WYSON_TO_BANK = {
            new Tile(3028, 3379, 0),
            new Tile(3026, 3379, 0),
            new Tile(3022, 3377, 0),
            new Tile(3018, 3375, 0),
            new Tile(3014, 3375, 0),
            new Tile(3010, 3375, 0),
            new Tile(3006, 3375, 0),
            new Tile(3002, 3375, 0),
            new Tile(2998, 3375, 0),
            new Tile(2995, 3373, 0),
            new Tile(2995, 3369, 0),
            new Tile(2998, 3365, 0),
            new Tile(3002, 3363, 0),
            new Tile(3006, 3361, 0),
            new Tile(3010, 3359, 0),
            new Tile(3012, 3357, 0),
            new Tile(3012, 3356, 0)
    };

    public static final Tile[] AGGIE_TO_BANK = {
            new Tile(3088, 3258, 0),
            new Tile(3090, 3258, 0),
            new Tile(3094, 3261, 0),
            new Tile(3098, 3262, 0),
            new Tile(3102, 3262, 0),
            new Tile(3103, 3259, 0),
            new Tile(3102, 3255, 0),
            new Tile(3098, 3253, 0),
            new Tile(3094, 3249, 0),
            new Tile(3093, 3245, 0),
            new Tile(3092, 3243, 0)
    };

    public static final Tile[] FALADOR_BANK_TO_DRAYNOR_BANK = {
            new Tile(3012, 3356, 0),
            new Tile(3012, 3356, 0),
            new Tile(3011, 3359, 0),
            new Tile(3008, 3358, 0),
            new Tile(3008, 3354, 0),
            new Tile(3008, 3350, 0),
            new Tile(3008, 3346, 0),
            new Tile(3008, 3342, 0),
            new Tile(3008, 3338, 0),
            new Tile(3008, 3334, 0),
            new Tile(3008, 3330, 0),
            new Tile(3008, 3326, 0),
            new Tile(3008, 3322, 0),
            new Tile(3008, 3318, 0),
            new Tile(3007, 3314, 0),
            new Tile(3007, 3310, 0),
            new Tile(3007, 3306, 0),
            new Tile(3007, 3302, 0),
            new Tile(3007, 3298, 0),
            new Tile(3007, 3294, 0),
            new Tile(3007, 3290, 0),
            new Tile(3008, 3286, 0),
            new Tile(3008, 3282, 0),
            new Tile(3011, 3278, 0),
            new Tile(3013, 3276, 0),
            new Tile(3013, 3276, 0),
            new Tile(3017, 3275, 0),
            new Tile(3021, 3276, 0),
            new Tile(3025, 3276, 0),
            new Tile(3029, 3277, 0),
            new Tile(3033, 3277, 0),
            new Tile(3037, 3276, 0),
            new Tile(3041, 3276, 0),
            new Tile(3045, 3276, 0),
            new Tile(3049, 3276, 0),
            new Tile(3053, 3276, 0),
            new Tile(3057, 3276, 0),
            new Tile(3061, 3276, 0),
            new Tile(3065, 3276, 0),
            new Tile(3069, 3276, 0),
            new Tile(3073, 3274, 0),
            new Tile(3077, 3272, 0),
            new Tile(3078, 3268, 0),
            new Tile(3080, 3264, 0),
            new Tile(3081, 3260, 0),
            new Tile(3081, 3256, 0),
            new Tile(3083, 3253, 0),
            new Tile(3086, 3251, 0),
            new Tile(3089, 3249, 0),
            new Tile(3092, 3246, 0)
    };

    public static final Tile[] ESCAPE_PATH = {
            new Tile(3093, 3243, 0),
            new Tile(3093, 3243, 0),
            new Tile(3092, 3247, 0),
            new Tile(3088, 3250, 0),
            new Tile(3093, 3252, 1),
            new Tile(3093, 3253, 1)
    };

    // GUI
    public static final Color BLACK = new Color(0, 0, 0, 200);
    public static final Color WHITE = new Color(255, 255, 255);
    public static final Font FONT_HEADER = new Font("SansSerif", Font.BOLD, 16);
    public static final Font FONT_SUBHEADER = new Font("SansSerif", Font.ITALIC, 12);
    public static final Font FONT_STATS = new Font("SansSerif", Font.PLAIN, 13);
    public static final Font FONT_ITEMS = new Font("SansSerif", Font.PLAIN, 12);

}
