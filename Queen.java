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

public class Queen extends Piece{
    private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = {-9,-8,-7,-1,1,7,8,9};
    public Queen(int piecePosition, Alliance pieceAlliance){
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();

        for(final int candidateCoordinateOffSet: CANDIDATE_MOVE_VECTOR_COORDINATES){
            int candidateDestinationCoordinate=this.getPiecePosition();
            while(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
                
                if(isFirstColumnExclusion(candidateDestinationCoordinate,candidateCoordinateOffSet) || isEighthColumnExclusion(candidateDestinationCoordinate,candidateCoordinateOffSet)){
                    break;
                }
                
                candidateDestinationCoordinate += candidateCoordinateOffSet;
                if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
                    final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                    if(!candidateDestinationTile.isOccupied()){
                        legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                    }
                    else{
                        final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                        final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
    
                        if(this.pieceAlliance != pieceAlliance){
                            legalMoves.add(new AttackMove(board,this,candidateDestinationCoordinate,pieceAtDestination));
                        }
                        break;
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    
    }

    @Override
    public String toString(){
        return PieceType.QUEEN.toString();
    }

    private static boolean isFirstColumnExclusion(final int currentPosition,final int candidateOffSet){
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffSet==-1|| candidateOffSet==-9 || candidateOffSet==7);
    }
    private static boolean isEighthColumnExclusion(final int currentPosition,final int candidateOffSet){
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffSet==-7 || candidateOffSet==1 || candidateOffSet==9);
    }

}
