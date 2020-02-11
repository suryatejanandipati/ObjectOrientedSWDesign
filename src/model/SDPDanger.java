
package model;


public class SDPDanger implements AllState {
    public SDPDanger(){
    }

    @Override
    public void goNext(GameFigureState gameFigure) {
        gameFigure.setState(new SDPDone());
    }
    
}
