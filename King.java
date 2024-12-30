package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Move.AttackMove;
import com.chess.engine.board.Move.MajorMove;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;

public class King extends Piece {
    private final static int[] MOVE_DIRECTION_VECTORS = {-9, -8, -7, -1, 1, 7, 8, 9}; // Renamed for clarity

    public King(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board gameBoard) { // Renamed for clarity
        final List<Move> legalMoveList = new ArrayList<>(); // Renamed for clarity

        for (final int moveDirectionVector : MOVE_DIRECTION_VECTORS) { // Renamed for clarity
            final int targetCoordinate = this.getPiecePosition() + moveDirectionVector; // Renamed for clarity

            if (isFirstColumnBlocked(this.getPiecePosition(), moveDirectionVector) || isEighthColumnBlocked(this.getPiecePosition(), moveDirectionVector)) { 
                continue; // Skip invalid moves
            }

            if (BoardUtils.isValidTileCoordinate(targetCoordinate)) {
                final Tile targetTile = gameBoard.getTile(targetCoordinate); // Renamed for clarity
            
                if (!targetTile.isOccupied()) {
                    legalMoveList.add(new MajorMove(gameBoard, this, targetCoordinate)); // Renamed for clarity
                } else {
                    final Piece pieceAtTarget = targetTile.getPiece(); // Renamed for clarity
                    final Alliance targetPieceAlliance = pieceAtTarget.getPieceAlliance(); // Renamed for clarity

                    if (this.pieceAlliance != targetPieceAlliance) {
                        legalMoveList.add(new AttackMove(gameBoard, this, targetCoordinate, pieceAtTarget)); // Renamed for clarity
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoveList); // Renamed for clarity
    }

    private static boolean isFirstColumnBlocked(final int currentPosition, final int moveDirectionVector) { // Renamed for clarity
        return BoardUtils.FIRST_COLUMN[currentPosition] && ((moveDirectionVector == -9) || (moveDirectionVector == -1) || (moveDirectionVector == 7)); // Renamed variable for clarity
    }

    @Override
    public String toString() {
        return PieceType.KING.toString();
    }

    private static boolean isEighthColumnBlocked(final int currentPosition, final int moveDirectionVector) { // Renamed for clarity
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && ((moveDirectionVector == -7) || (moveDirectionVector == 1) || (moveDirectionVector == 9)); // Renamed variable for clarity
    }
}
