package com.chess.engine.board;

public class BoardUtils {

    public static final boolean[] FIRST_COLUMN = initColumn(0);
    public static final boolean[] SECOND_COLUMN = initColumn(1);
    public static final boolean[] SEVENTH_COLUMN = initColumn(6);
    public static final boolean[] EIGHTH_COLUMN = initColumn(7);
    
    public static final boolean[] SECOND_ROW = initRow(8);
    public static final boolean[] SEVENTH_ROW = initRow(48);
    
    public static final int TOTAL_TILES = 64;
    public static final int TILES_PER_ROW = 8;

    private BoardUtils() {
        throw new RuntimeException("This is a utility class and cannot be instantiated.");
    }

    private static boolean[] initColumn(int columnIndex) {
        final boolean[] column = new boolean[TOTAL_TILES];
        
        for (int i = columnIndex; i < TOTAL_TILES; i += TILES_PER_ROW) {
            column[i] = true;
        }
        
        return column;
    }

    private static boolean[] initRow(int rowIndex) {
        final boolean[] row = new boolean[TOTAL_TILES];
        
        for (int i = rowIndex; i < rowIndex + TILES_PER_ROW; i++) {
            row[i] = true;
        }
        
        return row;
    }

    public static boolean isValidTileCoordinate(final int coordinate) {
        return coordinate >= 0 && coordinate < TOTAL_TILES;
    }
}
