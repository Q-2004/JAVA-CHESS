package com.chess.engine;

public enum Alliance {
    WHITE {
        @Override
        public int getMoveDirection() {
            return -1; // White moves up the board (negative direction)
        }

        @Override
        public boolean isWhiteAlliance() {
            return true;    
        }

        @Override
        public boolean isBlackAlliance() {
            return false;
        }

        @Override
        public boolean isBlack() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'isBlack'");
        }

        @Override
        public boolean isWhite() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'isWhite'");
        }
    },
    BLACK {
        @Override
        public int getMoveDirection() {
            return 1; // Black moves down the board (positive direction)
        }

        @Override
        public boolean isWhiteAlliance() {
            return false;    
        }

        @Override
        public boolean isBlackAlliance() {
            return true;
        }

        @Override
        public boolean isBlack() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'isBlack'");
        }

        @Override
        public boolean isWhite() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'isWhite'");
        }
    };

    // Abstract methods to be implemented by each alliance
    public abstract int getMoveDirection();
    public abstract boolean isWhiteAlliance();
    public abstract boolean isBlackAlliance();
    public abstract boolean isBlack();
    public abstract boolean isWhite();
}
