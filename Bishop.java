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
import com.google.common.collect.ImmutableList;
import com.chess.engine.board.Tile;

public class Bishop extends Piece {
    private final static int[] MOVE_DIRECTIONS = {-9, -7, 7, 9}; // Renamed for clarity

    public Bishop(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) { // Renamed for clarity
        final List<Move> legalMoves = new ArrayList<>(); // Renamed for clarity

        for (final int moveDirection : MOVE_DIRECTIONS) { // Renamed for clarity
            int currentPosition = this.getPiecePosition(); // Renamed for clarity
            while (BoardUtils.isValidTileCoordinate(currentPosition)) { // Renamed for clarity
                
                if (isBlockedOnFirstColumn(currentPosition, moveDirection) || isBlockedOnEighthColumn(currentPosition, moveDirection)) {
                    break;
                }
                
                currentPosition += moveDirection; // Renamed for clarity
                if (BoardUtils.isValidTileCoordinate(currentPosition)) {
                    final Tile targetTile = board.getTile(currentPosition); // Renamed for clarity
                    if (!targetTile.isOccupied()) {
                        legalMoves.add(new MajorMove(board, this, currentPosition)); // Renamed for clarity
                    } else {
                        final Piece targetPiece = targetTile.getPiece(); // Renamed for clarity
                        final Alliance targetPieceAlliance = targetPiece.getPieceAlliance(); // Renamed for clarity

                        if (this.pieceAlliance != targetPieceAlliance) {
                            legalMoves.add(new AttackMove(board, this, currentPosition, targetPiece)); // Renamed for clarity
                        }
                        break;
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves); // Renamed for clarity
    }

    @Override
    public String toString() {
        return PieceType.BISHOP.toString();
    }

    private static boolean isBlockedOnFirstColumn(final int currentPosition, final int moveDirection) { // Renamed for clarity
        return BoardUtils.FIRST_COLUMN[currentPosition] && (moveDirection == -9 || moveDirection == 7); // Renamed for clarity
    }

    private static boolean isBlockedOnEighthColumn(final int currentPosition, final int moveDirection) { // Renamed for clarity
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && (moveDirection == -7 || moveDirection == 9); // Renamed for clarity
    }
}
