package logics;

public class Story {
    private int viePerdue;
    private int orGagne;
    private String message;

    public Story(int viePerdue, int orGagne, String message){
        this.message=message;
        this.viePerdue=viePerdue;
        this.orGagne=orGagne;
    }

    public int getViePerdue() {
        return viePerdue;
    }

    public int getOrGagne() {
        return orGagne;
    }

    public String getMessage() {
        return message;
    }
}
