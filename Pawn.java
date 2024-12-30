package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Move.MajorMove;
import com.google.common.collect.ImmutableList;

public class Pawn extends Piece {

    private final static int[] MOVE_DIRECTION_VECTORS = {8}; // Renamed for clarity

    public Pawn(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board gameBoard) { // Renamed for clarity
        final List<Move> legalMoveList = new ArrayList<>(); // Renamed for clarity

        for (final int moveDirectionVector : MOVE_DIRECTION_VECTORS) { // Renamed for clarity
            final int targetCoordinate = (this.pieceAlliance.getMoveDirection() + moveDirectionVector); // Renamed for clarity

            if (!BoardUtils.isValidTileCoordinate(targetCoordinate)) {
                continue; // Skip invalid coordinates
            }

            if (moveDirectionVector == 8 && gameBoard.getTile(targetCoordinate).isOccupied()) {
                // TODO: Add additional logic here for handling the occupied tile case
                legalMoveList.add(new MajorMove(gameBoard, this, targetCoordinate)); // Renamed for clarity
            }
            else if (moveDirectionVector == 16 && this.isFirstMove() && 
                            (BoardUtils.SECOND_ROW[this.getPiecePosition()] && this.getPieceAlliance().isBlack()) || 
                            (BoardUtils.SEVENTH_ROW[this.getPiecePosition()] && this.getPieceAlliance().isWhite())) {
                            
                            final int behindTargetCoordinate = this.getPiecePosition() + (this.pieceAlliance.getMoveDirection() * 8); // Renamed for clarity
                            if (!gameBoard.getTile(behindTargetCoordinate).isOccupied() && !gameBoard.getTile(targetCoordinate).isOccupied()) {
                                // Additional logic for double move
                            }
                        }
                        else if (moveDirectionVector == 7 && !((BoardUtils.EIGHTH_COLUMN[this.getPiecePosition()] && this.pieceAlliance.isWhite()) || 
                                                              (BoardUtils.FIRST_COLUMN[this.getPiecePosition()] && this.pieceAlliance.isBlack()))) {
                            if (gameBoard.getTile(targetCoordinate).isOccupied()) {
                                final Piece pieceOnTarget = gameBoard.getTile(targetCoordinate).getPiece(); // Renamed for clarity
                                if (this.pieceAlliance != pieceOnTarget.getPieceAlliance()) {
                                    // TODO: Add logic for capturing the opponent piece
                                    legalMoveList.add(new MajorMove(gameBoard, this, targetCoordinate)); // Renamed for clarity
                                }
                            }
                        }
                        else if (moveDirectionVector == 9 && !((BoardUtils.FIRST_COLUMN[this.getPiecePosition()] && this.pieceAlliance.isWhite()) || 
                                                              (BoardUtils.EIGHTH_COLUMN[this.getPiecePosition()] && this.pieceAlliance.isBlack()))) {
                            if (gameBoard.getTile(targetCoordinate).isOccupied()) {
                                final Piece pieceOnTarget = gameBoard.getTile(targetCoordinate).getPiece(); // Renamed for clarity
                                if (this.pieceAlliance != pieceOnTarget.getPieceAlliance()) {
                                    // TODO: Add logic for capturing the opponent piece
                                    legalMoveList.add(new MajorMove(gameBoard, this, targetCoordinate)); // Renamed for clarity
                                }
                            }
                        }
                    }
                    return ImmutableList.copyOf(legalMoveList); // Renamed for clarity
                }
            
                private boolean isFirstMove() {
                    // TODO Auto-generated method stub
                    throw new UnsupportedOperationException("Unimplemented method 'isFirstMove'");
                }
            
                @Override
    public String toString() {
        return PieceType.PAWN.toString();
    }
}
