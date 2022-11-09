package graphics;

import logics.GameManager;
import logics.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyButtonListenerContinue implements ActionListener {
    private Player player;
    private JLabel labelText;
    private int numeroPlayer;
    public MyButtonListenerContinue(Player player,JLabel labelText,int numeroPlayer){
        this.player=player;
        this.labelText=labelText;
        this.numeroPlayer=numeroPlayer;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(player.getAction()== Player.Action.NOT_SELECTED) {
            GameWindow.setButtonPlayerPanelApresAction(numeroPlayer);
            player.setAction(Player.Action.CONTINUE);
            GameWindow.setButtonPlayerPanelAvantAction(false);
            GameManager.getInstance().play(labelText);
        }
    }
}