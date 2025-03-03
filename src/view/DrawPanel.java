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
    private final HashMap<Class<? extends Drawable>, BufferedImage> carImages = new HashMap<>();
    private final CarModel carModel;

    public DrawPanel(CarModel carModel) {
        this.carModel = carModel;
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(CarView.width, CarView.height - 240));
        this.setBackground(Color.green);
        loadImages();
    }

    private void loadImages() {
        for (Drawable drawable : carModel.getDrawables()) {
            String imagePath = "pics/" + drawable.getClass().getSimpleName() + ".jpg";
            try {
                BufferedImage image = ImageIO.read(Objects.requireNonNull(DrawPanel.class.getResourceAsStream(imagePath)));
                carImages.put(drawable.getClass(), image);
            } catch (IOException e) {
                System.err.println("Error loading image for " + drawable.getClass().getSimpleName() + ": " + e.getMessage());
            } catch (NullPointerException e) {
                System.err.println("Image file not found for " + drawable.getClass().getSimpleName() + " at path: " + imagePath);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Drawable drawable : carModel.getDrawables()) {
            int x = (int) Math.round(drawable.getX());
            int y = (int) Math.round(drawable.getY());
            BufferedImage image = carImages.get(drawable.getClass());

            if (image != null) {
                g.drawImage(image, x, y, null);
            } else {
                System.err.println("No image found for " + drawable.getClass().getSimpleName());
            }
        }
    }
}
