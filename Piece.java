package com.chess.engine.pieces;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

import java.util.Collection;

import com.chess.engine.Alliance;

public abstract class Piece {
    protected final int currentPosition; // Renamed for clarity
    protected final Alliance pieceAlliance;
    protected final boolean hasMoved; // Renamed for clarity

    Piece(final int currentPosition, final Alliance pieceAlliance) { // Renamed for clarity
        this.pieceAlliance = pieceAlliance;
        this.currentPosition = currentPosition; // Renamed for clarity
        this.hasMoved = false; // Renamed for clarity
    }

    public int getPiecePosition() {
        return this.currentPosition; // Renamed for clarity
    }

    public Alliance getPieceAlliance() {
        return this.pieceAlliance;
    }

    public boolean hasMoved() { // Renamed for clarity
        return this.hasMoved; // Renamed for clarity
    }

    public abstract Collection<Move> calculateLegalMoves(final Board board);

    public enum PieceType {

        PAWN("P"),
        KNIGHT("N"),
        BISHOP("B"),
        ROOK("R"),
        QUEEN("Q"),
        KING("K");

        private final String pieceSymbol;

        PieceType(final String pieceSymbol) { // Renamed for clarity
            this.pieceSymbol = pieceSymbol;
        }

        @Override
        public String toString() {
            return this.pieceSymbol; // Renamed for clarity
        }
    }
}
