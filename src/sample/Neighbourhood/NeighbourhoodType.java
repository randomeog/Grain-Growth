package sample.Neighbourhood;

import java.util.List;

public enum NeighbourhoodType {
    NEUMANN("Von Neumann"),
    MOORE("Moore"),
    PENTAGONAL("Pentagonal"),
    HEXAGONAL("Hexagonal"),
    HEXAGONALRIGHT("Hexagonal right"),
    HEXAGONALLEFT("Hexagonal left");

    private final String name;



    NeighbourhoodType(String name) {
        this.name = name;
    }


}
