package sample.Next;

import sample.Neighbourhood.NeighbourhoodType;

import java.lang.reflect.Array;
import java.util.*;

public class Generator {


    private static NeighbourhoodType neighbourhoodType;
    private static Random rand = new Random();


    public Generator(NeighbourhoodType neighbourhoodType) {
        this.neighbourhoodType = neighbourhoodType;
    }

    public void generateNextStep(Grid grid) {
        int[][] newGrid = new int[grid.getHeight()][grid.getWidth()];
        for (int x = 0; x < grid.getHeight(); x++) {
            for (int y = 0; y < grid.getWidth(); y++) {
                if (grid.getCell(x, y) != 0) {
                    newGrid[x][y] = grid.getCell(x, y);
                    continue;
                }
                HashMap<Integer, Integer> neighbours = new HashMap<>();
                for (Integer neighbour : getNeighbours(grid, x, y)) {
                    if (neighbour == 0) continue; //
                    if (neighbours.containsKey(neighbour)) {
                        neighbours.put(neighbour, neighbours.get(neighbour) + 1);
                    } else {
                        neighbours.put(neighbour, 1);
                    }
                }
                if (neighbours.size() == 0) continue;
                Map.Entry<Integer, Integer> max = null;
                for (Map.Entry<Integer, Integer> entry : neighbours.entrySet()) {
                    if (max == null || max.getValue().compareTo(entry.getValue()) < 0) {
                        max = entry;
                    }
                }
                newGrid[x][y] = max.getKey();
            }
        }
        grid.setGrid(newGrid);
    }




    public static ArrayList<Integer> getNeighbours(Grid newGrid, int x, int y) {
        ArrayList<Integer> neighbours = new ArrayList<>();
        int[][] grid = new int[newGrid.getHeight()][newGrid.getWidth()];
        switch (neighbourhoodType) {
            case NEUMANN:
                neighbours.add(newGrid.getCell(x, y - 1));
                neighbours.add(newGrid.getCell(x, y + 1));
                neighbours.add(newGrid.getCell(x - 1, y));
                neighbours.add(newGrid.getCell(x + 1, y));
                break;
            case MOORE:
                neighbours.add(newGrid.getCell(x, y - 1));
                neighbours.add(newGrid.getCell(x, y + 1));
                neighbours.add(newGrid.getCell(x - 1, y));
                neighbours.add(newGrid.getCell(x + 1, y));
                neighbours.add(newGrid.getCell(x + 1, y + 1));
                neighbours.add(newGrid.getCell(x + 1, y - 1));
                neighbours.add(newGrid.getCell(x - 1, y + 1));
                neighbours.add(newGrid.getCell(x - 1, y - 1));
                break;
            case PENTAGONAL:

                switch (rand.nextInt(4)) {
                    case 0:
                        neighbours.add(newGrid.getCell(x, y - 1));
                        neighbours.add(newGrid.getCell(x, y + 1));
                        neighbours.add(newGrid.getCell(x - 1, y - 1));
                        neighbours.add(newGrid.getCell(x - 1, y));
                        neighbours.add(newGrid.getCell(x - 1, y + 1));
                        break;
                    case 1:
                        neighbours.add(newGrid.getCell(x, y - 1));
                        neighbours.add(newGrid.getCell(x, y + 1));
                        neighbours.add(newGrid.getCell(x + 1, y - 1));
                        neighbours.add(newGrid.getCell(x + 1, y));
                        neighbours.add(newGrid.getCell(x + 1, y + 1));
                        break;
                    case 2:
                        neighbours.add(newGrid.getCell(x, y - 1));
                        neighbours.add(newGrid.getCell(x - 1, y));
                        neighbours.add(newGrid.getCell(x - 1, y - 1));
                        neighbours.add(newGrid.getCell(x + 1, y - 1));
                        neighbours.add(newGrid.getCell(x + 1, y));
                        break;
                    case 3:
                        neighbours.add(newGrid.getCell(x, y + 1));
                        neighbours.add(newGrid.getCell(x - 1, y));
                        neighbours.add(newGrid.getCell(x - 1, y + 1));
                        neighbours.add(newGrid.getCell(x + 1, y + 1));
                        neighbours.add(newGrid.getCell(x + 1, y));
                        break;


                }
                break;
            case HEXAGONAL:
                switch (rand.nextInt(2)) {
                    case 0:
                        neighbours.add(newGrid.getCell(x, y + 1));
                        neighbours.add(newGrid.getCell(x, y - 1));
                        neighbours.add(newGrid.getCell(x - 1, y));
                        neighbours.add(newGrid.getCell(x - 1, y - 1));
                        neighbours.add(newGrid.getCell(x + 1, y));
                        neighbours.add(newGrid.getCell(x + 1, y + 1));
                        break;
                    case 1:
                        neighbours.add(newGrid.getCell(x, y + 1));
                        neighbours.add(newGrid.getCell(x, y - 1));
                        neighbours.add(newGrid.getCell(x - 1, y));
                        neighbours.add(newGrid.getCell(x - 1, y + 1));
                        neighbours.add(newGrid.getCell(x + 1, y));
                        neighbours.add(newGrid.getCell(x + 1, y - 1));
                        break;

                }
                break;
            case HEXAGONALLEFT:
                neighbours.add(newGrid.getCell(x, y + 1));
                neighbours.add(newGrid.getCell(x, y - 1));
                neighbours.add(newGrid.getCell(x - 1, y));
                neighbours.add(newGrid.getCell(x - 1, y - 1));
                neighbours.add(newGrid.getCell(x + 1, y));
                neighbours.add(newGrid.getCell(x + 1, y + 1));
                break;
            case HEXAGONALRIGHT:
                neighbours.add(newGrid.getCell(x, y + 1));
                neighbours.add(newGrid.getCell(x, y - 1));
                neighbours.add(newGrid.getCell(x - 1, y));
                neighbours.add(newGrid.getCell(x - 1, y + 1));
                neighbours.add(newGrid.getCell(x + 1, y));
                neighbours.add(newGrid.getCell(x + 1, y - 1));
                break;
        }
        return neighbours;
    }





}
