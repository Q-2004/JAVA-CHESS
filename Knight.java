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

public class Knight extends Piece {
    
    private final static int[] MOVE_DIRECTION_VECTORS = {-17, -15, -10, -6, 6, 10, 15, 17}; // Renamed for clarity

    public Knight(final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board gameBoard) { // Renamed for clarity
        int targetCoordinate;
        final List<Move> legalMoveList = new ArrayList<>(); // Renamed for clarity
        for (final int moveDirectionVector : MOVE_DIRECTION_VECTORS) { // Renamed for clarity

            targetCoordinate = this.getPiecePosition() + moveDirectionVector; // Renamed for clarity

            if (BoardUtils.isValidTileCoordinate(targetCoordinate)) {
                
                if (isFirstColumnBlocked(this.getPiecePosition(), moveDirectionVector) || 
                    isSecondColumnBlocked(this.getPiecePosition(), moveDirectionVector) || 
                    isSeventhColumnBlocked(this.getPiecePosition(), moveDirectionVector) || 
                    isEighthColumnBlocked(this.getPiecePosition(), moveDirectionVector)) {
                    continue; // Skip invalid moves
                }

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

    @Override
    public String toString() {
        return PieceType.KNIGHT.toString();
    }

    private static boolean isFirstColumnBlocked(final int currentPosition, final int moveDirectionVector) { // Renamed for clarity
        return BoardUtils.FIRST_COLUMN[currentPosition] && 
               ((moveDirectionVector == -17) || (moveDirectionVector == -10) || (moveDirectionVector == 6) || (moveDirectionVector == 15)); // Renamed variable for clarity
    }

    private static boolean isSecondColumnBlocked(final int currentPosition, final int moveDirectionVector) { // Renamed for clarity
        return BoardUtils.SECOND_COLUMN[currentPosition] && 
               ((moveDirectionVector == -10) || (moveDirectionVector == 6)); // Renamed variable for clarity
    }

    private static boolean isSeventhColumnBlocked(final int currentPosition, final int moveDirectionVector) { // Renamed for clarity
        return BoardUtils.SEVENTH_COLUMN[currentPosition] && 
               ((moveDirectionVector == -6) || (moveDirectionVector == 10)); // Renamed variable for clarity
    }

    private static boolean isEighthColumnBlocked(final int currentPosition, final int moveDirectionVector) { // Renamed for clarity
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && 
               ((moveDirectionVector == -15) || (moveDirectionVector == -6) || (moveDirectionVector == 10) || (moveDirectionVector == 17)); // Renamed variable for clarity
    }
}
