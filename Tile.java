package com.chess.engine.board;

import java.util.HashMap;
import java.util.Map;
import com.google.common.collect.ImmutableMap;
import com.chess.engine.pieces.Piece;

public abstract class Tile {
    
    protected final int tileCoordinate;

    // Cache of all possible empty tiles
    private static final Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createAllEmptyTiles();

    /**
     * Creates all possible empty tiles and caches them in an immutable map.
     */
    private static Map<Integer, EmptyTile> createAllEmptyTiles() {
        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();
        for (int coordinate = 0; coordinate < BoardUtils.NUM_TILES; coordinate++) {
            emptyTileMap.put(coordinate, new EmptyTile(coordinate));
        }
        return ImmutableMap.copyOf(emptyTileMap);
    }

    /**
     * Factory method to create a tile.
     * 
     * @param tileCoordinate The coordinate of the tile.
     * @param piece The piece on the tile, if any.
     * @return A tile (empty or occupied).
     */
    public static Tile createTile(final int tileCoordinate, final Piece piece) {
        return piece != null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILES_CACHE.get(tileCoordinate);
    }

    /**
     * Constructor for the Tile class.
     * 
     * @param tileCoordinate The coordinate of the tile.
     */
    private Tile(final int tileCoordinate) {
        this.tileCoordinate = tileCoordinate;
    }

    public abstract boolean isOccupied();

    public abstract Piece getPiece();

    /**
     * Represents an empty tile.
     */
    public static final class EmptyTile extends Tile {

        EmptyTile(final int coordinate) {
            super(coordinate);
        }

        @Override
        public String toString() {
            return "-";
        }

        @Override
        public boolean isOccupied() {
            return false;
        }

        @Override
        public Piece getPiece() {
            return null;
        }
    }

    /**
     * Represents an occupied tile.
     */
    public static final class OccupiedTile extends Tile {
        private final Piece pieceOnTile;

        OccupiedTile(final int tileCoordinate, final Piece pieceOnTile) {
            super(tileCoordinate);
            this.pieceOnTile = pieceOnTile;
        }

        @Override
        public String toString() {
            return pieceOnTile.getPieceAlliance().isBlackAlliance() ? pieceOnTile.toString().toLowerCase() : pieceOnTile.toString();
        }

        @Override
        public boolean isOccupied() {
            return true;
        }

        @Override
        public Piece getPiece() {
            return this.pieceOnTile;
        }
    }
}
