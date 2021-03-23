package Compulsory.Controller;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import Compulsory.Main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Controller extends Main {

    @FXML
    private TextField size;

    @FXML
    private Canvas canvas;

    @FXML
    private ColorPicker color;

    @FXML
    private ToggleGroup form;

    @FXML
    private Button saveButton;

    @FXML
    private Button loadButton;

    @FXML
    private Button resetButton;

    @FXML
    private Button exitButton;

    @FXML
    private RadioButton squareSelect;

    @FXML
    private RadioButton circleSelect;

    @FXML
    private RadioButton triangleSelect;

    @FXML
    private RadioButton lineSelect;

    public void initialize() {
        GraphicsContext g = canvas.getGraphicsContext2D();

        canvas.setOnMouseDragged(e -> {
            if (lineSelect.isSelected()) {
                double size = Double.parseDouble(this.size.getText());
                double x = e.getX() - size / 2;
                double y = e.getY() - size / 2;
                g.setFill(color.getValue());
                g.fillRect(x, y, size, size);
            }
        });
        canvas.setOnMouseClicked(e -> {
            double size = Double.parseDouble(this.size.getText());
            double x = e.getX() - size / 2;
            double y = e.getY() - size / 2;
            if (circleSelect.isSelected()) {
                g.setFill(color.getValue());
                g.fillRoundRect(x, y, size, size, size, size);
            }
            if (squareSelect.isSelected()) {
                g.setFill(color.getValue());
                g.fillRect(x, y, size, size);
            }
        });
    }

    public void onExit() {
        Platform.exit();
    }

    public void saveOn(javafx.event.ActionEvent actionEvent) {
        try {
            Image snapshot = canvas.snapshot(null, null);

            ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", new File("paint.png"));
        } catch (Exception e) {
            System.out.println("Failed to save image: " + e);
        }
    }

    public void loadOn(ActionEvent actionEvent) {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            fileChooser.setInitialDirectory(new File("C:\\Users\\Zetq\\IdeaProjects\\Advanced-Programming\\PaLab6"));
            File file = fileChooser.showOpenDialog(stage);
            System.out.println(file.getAbsolutePath());

            BufferedImage tempCard = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(tempCard, null);

            canvas.getGraphicsContext2D().drawImage(image,0,0,image.getWidth(),image.getHeight());

        } catch (Exception e) {
            System.out.println("Failed to load image: " + e);
        }
    }

    public void resetOn(ActionEvent actionEvent) {
        canvas.getGraphicsContext2D().clearRect(0,0,canvas.getWidth(),canvas.getHeight());
    }

    public void exitOn(ActionEvent actionEvent) {
        Platform.exit();
    }
}