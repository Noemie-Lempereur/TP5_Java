package logics;

public class Player {
    private int nbVies;
    private int orManche;
    private int or;
    private boolean inGame;

    public enum Action {
        NOT_SELECTED, CONTINUE, STOP
    }

    private Action action;

    public Player(int pvMax) {
        inGame=true;
        or=0;
        orManche=0;
        nbVies=pvMax;
        action=Action.NOT_SELECTED;
    }


    public void setOrManche(int orManche) {
        this.orManche = orManche;
    }

    public void setOr(int or) {
        this.or = or;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public void setNbVies(int nbVies) {
        this.nbVies = nbVies;
    }

    public boolean isInGame() {
        return inGame;
    }

    public int getOr() {
        return or;
    }

    public int getNbVies() {
        return nbVies;
    }

    public int getOrManche() {
        return orManche;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
