class Move{
    Square start;
    Square landing;
    int moveScore;


    public Move(){

    }

    public Move(Square x, Square y){
        start = x;
        landing = y;
    }

    public Move(Square x, Square y, int score){
        start = x;
        landing = y;
        moveScore = score;
    }


    public Square getStart(){
        return start;
    }

    public Square getLanding(){
        return landing;
    }

    public int getScore(){

        return moveScore;
    }
}
