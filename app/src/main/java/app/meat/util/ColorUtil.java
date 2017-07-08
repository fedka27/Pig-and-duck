package app.meat.util;

import android.graphics.Color;

public class ColorUtil {
    public static int lighter(int color, float factor) {
        int red = (int) ((Color.red(color) * (1 - factor) / 255 + factor) * 255);
        int green = (int) ((Color.green(color) * (1 - factor) / 255 + factor) * 255);
        int blue = (int) ((Color.blue(color) * (1 - factor) / 255 + factor) * 255);
        return Color.argb(Color.alpha(color), red, green, blue);
    }

    public static int drakness(int color, float factor) {
        int red = (int) ((Color.red(color) * (1 - factor) * 255 + factor) / 255);
        int green = (int) ((Color.green(color) * (1 - factor) * 255 + factor) / 255);
        int blue = (int) ((Color.blue(color) * (1 - factor) * 255 + factor) / 255);
        return Color.argb(Color.alpha(color), red, green, blue);
    }
}
