package view;

import model.CarModel;
import model.interfaces.Drawable;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class DrawPanel extends JPanel {
    private final HashMap<Class, BufferedImage> carImages = new HashMap<>();
    private final CarModel carModel;

    public DrawPanel(CarModel carModel) {
        this.carModel = carModel;
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(CarView.width, CarView.height - 240));
        this.setBackground(Color.green);
        try {
            loadImages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadImages() throws IOException {
        for (Drawable drawable : carModel.getDrawables()) {
            carImages.put(drawable.getClass(), ImageIO.read(Objects.requireNonNull(DrawPanel.class.getResourceAsStream(("pics/" + drawable.getClass().getSimpleName() + ".jpg")))));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Drawable drawable : carModel.getDrawables() ) {
            int x = (int) Math.round(drawable.getX());
            int y = (int) Math.round(drawable.getY());
            BufferedImage image = carImages.get(drawable.getClass());
            if (image != null) {
                g.drawImage(image, x, y, null);
            }
        }
    }
}
