package Optional.Controller;

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
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import Optional.Main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;

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
    private RadioButton eraserSelect;

    @FXML
    private RadioButton lineSelect;

    @FXML
    private RadioButton deleteSelect;

    @FXML
    private RadioButton shapeRecognition;

    private int canvasObjects[][];
    private int indexOfItem;

    public void mouseReleased(MouseEvent mouseEvent) {
        //System.out.println("Drag over.");
        if (shapeRecognition.isSelected()){
            //System.out.println("Replacing canvas.");

        }
    }


    class Item{
        public String type;
        public double x;
        public double y;
        public double size;

        public Item(String type, double x, double y, double size) {
            this.type = type;
            this.x = x;
            this.y = y;
            this.size = size;
        }
    }

    private List<Item> listOfItems;
    private Canvas copyOfCanvas;

    public void fillCircle(int x, int y, int r, int index) {
        canvasObjects[x][y]=index;
        for(int j=0;j<r;j++){
            if (y+j < 266)
            canvasObjects[x][y+j]=index;
            if (y-j >= 0)
            canvasObjects[x][y-j]=index;
            for(int i=0;i<r-j;i++){
                if (y+j < 266 && x+i < 423)
                canvasObjects[x+i][y+j]=index;
                if (y+j < 266 && x-i >= 0)
                canvasObjects[x-i][y+j]=index;
                if (x+i < 423 && y-j >= 0)
                canvasObjects[x+i][y-j]=index;
                if (x-i >= 0 && y-j >= 0)
                canvasObjects[x-i][y-j]=index;
            }
        }
    }
    public void fillSquare(int x, int y, int r, int index) {
        canvasObjects[x][y]=index;
        for(int j=0;j<r;j++){
            if (y+j < 266)
                canvasObjects[x][y+j]=index;
            if (y-j >= 0)
                canvasObjects[x][y-j]=index;
            for(int i=0;i<r;i++){
                if (y+j < 266 && x+i < 423)
                    canvasObjects[x+i][y+j]=index;
                if (y+j < 266 && x-i >= 0)
                    canvasObjects[x-i][y+j]=index;
                if (x+i < 423 && y-j >= 0)
                    canvasObjects[x+i][y-j]=index;
                if (x-i >= 0 && y-j >= 0)
                    canvasObjects[x-i][y-j]=index;
            }
        }
    }

    public void deleteForm(int x, int y){
    }

    public void initialize() {
        indexOfItem = 1;
        listOfItems = new ArrayList<>();
        listOfItems.add(new Item("0",0,0,0));


        GraphicsContext g = canvas.getGraphicsContext2D();

        canvasObjects = new int[423][266];
        g.setFill(color.getValue());
        g.fillRect(0,0,1000,1000);

        /*
        * Cand mouse ul este pe drag, desenaza sau sterge in functie de butonul selectat
         */
        canvas.setOnMouseDragged(e -> {
            double size = Double.parseDouble(this.size.getText());
            double x = e.getX() - size / 2;
            double y = e.getY() - size / 2;
            if (lineSelect.isSelected()) {
                g.setFill(color.getValue());
                g.fillRect(x, y, size, size);
            }
            if (eraserSelect.isSelected()){
                g.setFill(Color.rgb(255,255,255));
                g.fillRect(x, y, size, size);
            }
        });

        /*
        * Cand mouse ul este apasat, este desenata forma in functie de butonul selectat. Forma este salvata intr un vec-
        * tor ce reprezinta canvasul si intr o lista cu datele exacte ale acestuia. Cand este selectat deleteul, se
        * verifica ce forma se afla la pozitia apasarii si daca exista o forma, se sterge din lista in care sunt
        *  memorate toate figurile in ordinea desenarii.
         */
        canvas.setOnMouseClicked(e -> {
            double size = Double.parseDouble(this.size.getText());
            double x = e.getX() - size / 2;
            double y = e.getY() - size / 2;
            if (circleSelect.isSelected()) {
                g.setFill(color.getValue());
                g.fillRoundRect(x, y, size, size, size, size);

                fillCircle((int)x,(int)y, (int) size, indexOfItem);
                listOfItems.add(new Item("circle",x,y,size));
                indexOfItem++;
            }
            if (squareSelect.isSelected()) {
                g.setFill(color.getValue());
                g.fillRect(x, y, size, size);

                fillSquare((int)x,(int)y, (int) size, indexOfItem);
                listOfItems.add(new Item("square",x,y,size));
                indexOfItem++;
            }
            if(deleteSelect.isSelected()) {
                if (canvasObjects[(int) x][(int) y] != 0) {
                    Item itemToDelete = listOfItems.get(canvasObjects[(int) x][(int) y]);
                    if (itemToDelete.type.compareTo("circle") == 0) {
                        g.setFill(Color.rgb(255, 255, 255));
                        g.fillRoundRect(itemToDelete.x - 1, itemToDelete.y - 1, itemToDelete.size + 2, itemToDelete.size + 2, itemToDelete.size, itemToDelete.size);
                        fillCircle((int) x, (int) y, (int) size, 0);
                    } else if (itemToDelete.type.compareTo("square") == 0) {
                        g.setFill(Color.rgb(255, 255, 255));
                        g.fillRect(itemToDelete.x, itemToDelete.y, itemToDelete.size, itemToDelete.size);
                        fillSquare((int) x, (int) y, (int) size, 0);
                    }
                }
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

            BufferedImage tempCard = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(tempCard, null);

            canvas.getGraphicsContext2D().drawImage(image,0,0,image.getWidth(),image.getHeight());

        } catch (Exception e) {
            System.out.println("Failed to load image: " + e);
        }
    }

    public void resetOn(ActionEvent actionEvent) {
        canvasObjects = new int[423][266];
        canvas.getGraphicsContext2D().clearRect(0,0,canvas.getWidth(),canvas.getHeight());
        GraphicsContext g = canvas.getGraphicsContext2D();
        g.setFill(Color.rgb(255,255,255));
        g.fillRect(0,0,1000,1000);
    }

    public void exitOn(ActionEvent actionEvent) {
        Platform.exit();
    }
}