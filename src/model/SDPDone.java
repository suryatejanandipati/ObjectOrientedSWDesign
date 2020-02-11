package model;

public class SDPDone implements AllState{
    public SDPDone(){
        
    }

    @Override
    public void goNext(GameFigureState gameFigure) {
        gameFigure.setState(null);
    }
    
}
