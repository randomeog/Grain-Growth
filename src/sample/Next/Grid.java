package sample.Next;

import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

public class Grid {

    private int width;
    private int height;
    private int scale;
    private int embryos;
    private Map<Integer, Color> colorMap;
    private int [][]grid;


    public Grid(int width, int height, int scale, int embryos) {
        this.width = width;
        this.height = height;
        this.scale = scale;
        this.embryos = 0;
        this.colorMap = new HashMap<>();
        colorMap.put(0,Color.WHITE);
        this.grid = initiateGrid(this.height,this.width);
    }

    public Grid(int width, int height, int scale) {
        this.width = width;
        this.height = height;
        this.scale = scale;
        this.embryos = 0;
        this.colorMap = new HashMap<>();
        colorMap.put(0,Color.WHITE);
        this.grid = initiateGrid(this.height,this.width);
    }

    private int [][] initiateGrid(int height, int width){
        int [][] grid = new int [height][width];
        for(int i =0;i<height;i++){
            for(int j=0;j<width;j++){
                grid[i][j] = 0;
            }
        }
        return grid;
    }

    public void resizeGrid(int w, int h, int scale){
        int [][] newGrid = initiateGrid(w,h);

        int widthB = Math.min(w, width);
        int heightB = Math.min(h, height);

        for(int i =0; i<heightB;i++){
            for(int j=0;j < widthB;j++){
                newGrid[i][j] = grid[i][j];
            }
        }
        this.height=h;
        this.width=w;
        this.scale=scale;
        this.grid=newGrid;
    }


    public void incrementCell(int i, int j){
        int newValue;
        if(grid[i][j] == 0){
            newValue =++embryos;
            Color newColor = generateColor();
            colorMap.put(newValue,newColor);
        }
        else
            newValue=0;
        grid[i][j]=newValue;
    }

    private Color generateColor() {
        Color color = Color.color(Math.random(), Math.random(),Math.random());
        if(colorMap.containsValue(color))
            return generateColor();
        else
            return color;
    }

    public Color getColor(int value){
        return colorMap.get(value);
    }

    public int getCell(int height, int width){

        return grid[(height+grid.length)%grid.length][(width+grid[0].length)%grid[0].length];

    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public int getEmbryos() {
        return embryos;
    }

    public void setEmbryos(int embryos) {
        this.embryos = embryos;
    }

    public Map<Integer, Color> getColorMap() {
        return colorMap;
    }

    public void setColorMap(Map<Integer, Color> colorMap) {
        this.colorMap = colorMap;
    }

    public int[][] getGrid() {
        return grid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }
}
