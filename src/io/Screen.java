package io;

import game.abstraction.Entity;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.Collection;

public class Screen {

    private final GraphicsContext foreground;
    private final GraphicsContext midground;
    private final GraphicsContext background;
    private final double screenWidth;
    private final double screenHeight;

    public Screen(Canvas fgCanvas, Canvas mgCanvas, Canvas bgCanvas) {
        Rectangle2D screen = javafx.stage.Screen.getPrimary().getBounds();
        this.screenHeight = screen.getHeight();
        this.screenWidth = screen.getWidth();

        fgCanvas.setHeight(screenHeight);
        fgCanvas.setWidth(screenWidth);
        mgCanvas.setHeight(screenHeight);
        mgCanvas.setWidth(screenWidth);
        bgCanvas.setHeight(screenHeight);
        bgCanvas.setWidth(screenWidth);

        fgCanvas.toFront();
        bgCanvas.toBack();

        foreground = fgCanvas.getGraphicsContext2D();
        midground = mgCanvas.getGraphicsContext2D();
        background = bgCanvas.getGraphicsContext2D();
    }

    public void drawOnBackground(Collection<? extends Entity> objects) {
        objects.forEach(o -> o.draw(background));
    }

    public void drawOnForeground(Collection<? extends Entity> objects) {
        foreground.clearRect(0, 0, screenWidth, screenHeight);
        objects.forEach(o -> o.draw(foreground));
    }

    public void drawOnMidground(Collection<? extends Entity> objects) {
        midground.clearRect(0, 0, screenWidth, screenHeight);
        objects.forEach(o -> o.draw(midground));
    }

    public void reset() {
        foreground.clearRect(0, 0, screenWidth, screenHeight);
        background.clearRect(0, 0, screenWidth, screenHeight);
    }
}
