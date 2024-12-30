package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

public abstract class Move {
    protected final Board board;
    protected final Piece pieceMoved;
    protected final int destinationCoordinate;

    // Private constructor to be called by subclasses
    private Move(final Board board, final Piece pieceMoved, final int destinationCoordinate) {
        this.board = board;
        this.pieceMoved = pieceMoved;
        this.destinationCoordinate = destinationCoordinate;
    }

    // Represents a regular move (not an attack)
    public static final class MajorMove extends Move {
        public MajorMove(final Board board, final Piece pieceMoved, final int destinationCoordinate) {
            super(board, pieceMoved, destinationCoordinate);
        }
    }

    // Represents a move where the piece attacks another piece
    public static final class AttackMove extends Move {
        private final Piece pieceAttacked;

        public AttackMove(final Board board, final Piece pieceMoved, final int destinationCoordinate, final Piece pieceAttacked) {
            super(board, pieceMoved, destinationCoordinate);
            this.pieceAttacked = pieceAttacked;
        }

        public Piece getAttackedPiece() {
            return this.pieceAttacked;
        }
    }
}
