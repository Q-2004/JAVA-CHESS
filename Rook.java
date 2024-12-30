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

public class Rook extends Piece {

    private final static int[] MOVE_DIRECTIONS = {-8, -1, 1, 8}; // Renamed for consistency

    public Rook(final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>(); // Renamed for consistency

        for (final int moveDirection : MOVE_DIRECTIONS) { // Renamed for consistency
            int currentPosition = this.getPiecePosition(); // Renamed for consistency
            while (BoardUtils.isValidTileCoordinate(currentPosition)) { // Renamed for consistency
                
                if (isBlockedOnFirstColumn(currentPosition, moveDirection) || isBlockedOnEighthColumn(currentPosition, moveDirection)) {
                    break;
                }
                
                currentPosition += moveDirection; // Renamed for consistency
                if (BoardUtils.isValidTileCoordinate(currentPosition)) {
                    final Tile targetTile = board.getTile(currentPosition); // Renamed for consistency
                    if (!targetTile.isOccupied()) {
                        legalMoves.add(new MajorMove(board, this, currentPosition)); // Renamed for consistency
                    } else {
                        final Piece targetPiece = targetTile.getPiece(); // Renamed for consistency
                        final Alliance targetPieceAlliance = targetPiece.getPieceAlliance(); // Renamed for consistency

                        if (this.pieceAlliance != targetPieceAlliance) {
                            legalMoves.add(new AttackMove(board, this, currentPosition, targetPiece)); // Renamed for consistency
                        }
                        break;
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves); // Renamed for consistency
    }

    @Override
    public String toString() {
        return PieceType.ROOK.toString();
    }

    private static boolean isBlockedOnFirstColumn(final int currentPosition, final int moveDirection) { // Renamed for consistency
        return BoardUtils.FIRST_COLUMN[currentPosition] && (moveDirection == -1); // Renamed for consistency
    }

    private static boolean isBlockedOnEighthColumn(final int currentPosition, final int moveDirection) { // Renamed for consistency
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && (moveDirection == 1); // Renamed for consistency
    }
}
