package sample.Next;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Draw {

    private GraphicsContext graphicsContext;
    private int scale;

    public Draw(GraphicsContext graphicsContext, int scale) {
        this.graphicsContext = graphicsContext;
        this.scale = scale;
    }

    private void paintCell(int i, int j, int value, Grid grid){
        Color color = grid.getColor(value);
        graphicsContext.setFill(color);
        graphicsContext.fillRect(i,j,grid.getScale(), grid.getScale());
    }
    public void paintAllCells(Grid grid){
        for(int i =0;i<grid.getHeight();i++){
            for(int j =0;j<grid.getWidth();j++){
                int cellValue = grid.getCell(i,j);
                int x = i * grid.getScale();
                int y = j * grid.getScale();
                paintCell(x,y, cellValue, grid);
            }
        }
    }



    public void paintClicked(Grid grid, int i, int j){
        int xR = i % scale;
        int yR = j % scale;
        int xP = i - xR;
        int yP = j - yR;
        int x = xP / grid.getScale();
        int y = yP / grid.getScale();
        if(y >= grid.getGrid().length || x >= grid.getGrid()[0].length)
            return;
        grid.incrementCell(y,x);
        int value = grid.getCell(y,x);
        paintCell(xP,yP,value, grid);
    }
    public GraphicsContext getGraphicsContext() {
        return graphicsContext;
    }

    public void setGraphicsContext(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }
}
