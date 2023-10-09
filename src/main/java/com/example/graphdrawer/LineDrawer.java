package com.example.graphdrawer;

import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class LineDrawer {
    public static void WuLine(int x1, int y1, int x2, int y2, PixelWriter pW) {
        if(x1 < 0 || x2 < 0 || y1 < 0 || y2 < 0) return;
        boolean steep = Math.abs(y2 - y1) > Math.abs(x2 - x1);
        if (steep) { // swap
            x1 = x1 + y1;
            y1 = x1 - y1;
            x1 = x1 - y1;
            x2 = x2 + y2;
            y2 = x2 - y2;
            x2 = x2 - y2;
        }
        if (x1 > x2) {
            x2 = x1 + x2;
            x1 = x2 - x1;
            x2 = x2 - x1;
            y2 = y2 + y1;
            y1 = y2 - y1;
            y2 = y2 - y1;
        }

        drawPixel(steep, x1, y1, Color.BLACK, pW);
        drawPixel(steep, x2, y2, Color.BLACK, pW);

        float dx = x2 - x1;
        float dy = y2 - y1;
        float gradient = dy / dx;
        float y = y1 + gradient;
        for(int x = x1 + 1; x <= x2; x++) {
            drawPixel(steep, x, (int)y, Color.rgb((int)(1 - (y - (int)y)), (int)(1 - (y - (int)y)), (int)(1 - (y - (int)y))), pW);
            drawPixel(steep, x, (int)y, Color.rgb((int)(y - (int)y), (int)(y - (int)y), (int)(y - (int)y)), pW);
            y += gradient;
        }
    }
    private static void drawPixel(boolean steep, int x, int y, Color c, PixelWriter pW) {
        if(!steep) {
            pW.setColor(x, y, c);
            return;
        }
        pW.setColor(y, x, c);
    }
}
