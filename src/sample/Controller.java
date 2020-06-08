package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.util.Duration;
import sample.Neighbourhood.Embryo;
import sample.Next.Draw;
import sample.Next.Generator;
import sample.Neighbourhood.NeighbourhoodType;
import sample.Next.Grid;

import java.net.URL;

import java.util.ResourceBundle;

import static java.lang.Math.max;

public class Controller implements Initializable {

    private static int HEIGHT;
    private static int WIDTH;
    private static int SCALE;

    @FXML
    private Canvas canvas;
    @FXML
    private ChoiceBox embryoChoiceBox;
    @FXML
    private Button embryoButton;
    @FXML
    private TextField embryoNumberTextField;
    @FXML
    private TextField heightTextField;
    @FXML
    private TextField widthTextField;
    @FXML
    private Button resizeButton;
    @FXML
    private ChoiceBox<NeighbourhoodType> neighbourhoodChoiceBox;
    @FXML
    private Button stopButton;
    @FXML
    private Button startButton;
    @FXML
    private Button clearButton;


    private Draw draw;
    private GraphicsContext graphicsContext;
    private Grid grid;
    private Timeline timeline;
    private Generator generator;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HEIGHT = (int)canvas.getHeight();
        WIDTH = (int)canvas.getWidth();
        SCALE = 10;
        graphicsContext = canvas.getGraphicsContext2D();
        grid = new Grid(WIDTH, HEIGHT, SCALE, 0);
        draw = new Draw(graphicsContext, SCALE);

        graphicsContext.setFill(Color.GRAY);
        graphicsContext.fillRect(0,0,canvas.getHeight(),canvas.getWidth());



        embryoButton.setOnAction(event -> {
            String template = (String) embryoChoiceBox.getValue();
            int embryosNumber = Integer.parseInt(embryoNumberTextField.getText());
            try{
                grid= Embryo.getGrid(template, grid.getWidth(), grid.getHeight(), SCALE, embryosNumber);
                draw.paintAllCells( grid);
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        startButton.setOnAction(event -> {
            timeline.stop();
            NeighbourhoodType neighbourhood = neighbourhoodChoiceBox.getValue();
            generator = new Generator(neighbourhood);
            Duration delayTime = Duration.millis(1000);
            Duration frame = delayTime;
            timeline.setCycleCount(Timeline.INDEFINITE);
            int iterations = 1;
           // for(int i =0;i<iterations; i++){
                timeline.getKeyFrames().add(new KeyFrame(frame, e -> generator.generateNextStep(grid)));
                timeline.getKeyFrames().add(new KeyFrame(frame, e->draw.paintAllCells(grid)));
                frame = frame.add(delayTime);
           // }
            timeline.play();


        });
        resizeButton.setOnAction(event -> {
            resizeGrid();
        });
        stopButton.setOnAction(event -> {
            timeline.stop();
        });


        clearButton.setOnAction(event -> {
            clearCanvas();
        });
        //embryo choice
        ObservableList embryoTemp = FXCollections.observableArrayList(Embryo.getTemplates());
        embryoChoiceBox.setItems(embryoTemp);
        embryoChoiceBox.setValue(embryoTemp.get(2));
        activateCanvas();
        //neighbourhood choice
        neighbourhoodChoiceBox.getItems().setAll(NeighbourhoodType.values());
        neighbourhoodChoiceBox.setValue(NeighbourhoodType.HEXAGONAL);
        timeline = new Timeline();
        timeline.setOnFinished(event -> {
            System.err.println("Zakończono!");
            timeline.stop();
            timeline.getKeyFrames();
        });


    }

    public void resizeGrid(){
        int width = getWidth();
        int height = getHeight();
        int widthScale = WIDTH/width;
        int heightScale = HEIGHT/height;
        SCALE = max(widthScale, heightScale);
        if(SCALE>20)
            SCALE = 20;
        grid.resizeGrid(width, height, SCALE);
        clearCanvas();
        draw.paintAllCells(grid);
        draw.setScale(SCALE);
    }

    private void clearCanvas() {
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillRect(0,0, graphicsContext.getCanvas().getWidth(), graphicsContext.getCanvas().getHeight());
    }

    private void activateCanvas(){
        canvas.setOnMouseClicked(event -> {
            int x = (int) event.getX();
            int y = (int) event.getY();
            MouseButton mouseButton = event.getButton();
            if(MouseButton.PRIMARY.equals(mouseButton))
                draw.paintClicked(grid,x,y);
        });
    }

    private int getHeight() {
        int height = Integer.parseInt(heightTextField.getText());
        if (height <=0)
            return 1;
        else if(height > HEIGHT)
            return HEIGHT;
        return height;
    }

    private int getWidth() {
        int width = Integer.parseInt(widthTextField.getText());
        if (width <=0)
            return 1;
        else if(width > WIDTH)
            return WIDTH;
        return width;
    }
}
