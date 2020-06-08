package sample.Neighbourhood;

import sample.Next.Grid;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Embryo {
    private final static List<String> templates = Arrays.asList("Własna", "Jednorodne", "Losowe");

    public static List<String> getTemplates() {
        return templates;
    }

    public static Grid getGrid(String template, int width, int height, int scale, int embryos) throws Exception{
        switch (template){
            case "Własna":
                return null;
            case "Jednorodne":
                return Jednorodne(width, height, scale, embryos);
            case "Losowe":
                return Losowe(width, height, scale, embryos);
            default:
                throw new Exception("Zle");
        }
    }

    private static Grid Losowe(int width, int height, int scale, int embryos) {
        Grid grid = new Grid(width,height,scale);
        Random random = new Random();
        int number = embryos*2;
        double[]randomDoubles = random.doubles(number).toArray();
        for(int i = 0; i < randomDoubles.length-1;i=i+2){
            int x =(int)(randomDoubles[i]*height);
            int y = (int)(randomDoubles[i+1]*width);
            grid.incrementCell(x,y);
        }
        return grid;
    }

    private static Grid Jednorodne(int width, int height, int scale, int embryos) {
        if(width < embryos)
            width = embryos;
        if(height<embryos)
            height=embryos;
        Grid grid = new Grid(width, height, scale);
        double d1 = (double) width/(double)(embryos+1);
        double d2 = (double) height/ (double)(embryos+1);
        for(int i =1; i<=embryos;i++){
            for(int j=0;j<= embryos; j++){
                grid.incrementCell((int)(d1*i), (int)(d2*j));
            }
        }
        return grid;
    }
}
