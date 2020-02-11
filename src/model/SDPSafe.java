package model;

public class SDPSafe implements AllState{

    public SDPSafe(){
       
    }
    @Override
    public void goNext(GameFigureState gameFigure) {
        gameFigure.setState(new SDPDanger());
    }
    
}
