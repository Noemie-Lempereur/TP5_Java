package graphics;

import logics.GameManager;
import logics.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyButtonListenerInfos implements ActionListener {
    private Player player;
    private JButton button;
    private boolean activeButton;
    public MyButtonListenerInfos(Player player, JButton button){
        this.player=player;
        this.button=button;
        this.activeButton=false;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(!activeButton){
            button.setText("<HTML><BODY><P ALIGN=CENTER>PV : "+player.getNbVies()+"<BR>Gold : "+player.getOrManche()+" / "+player.getOr()+"</P></BODY></HTML>");
            activeButton=true;

        }else{
            button.setText("<HTML><BODY><P ALIGN=CENTER>Afficher les informations<BR>sur la manche</P></BODY></HTML>");
            activeButton=false;
        }
    }

    public void setActiveButton(boolean activeButton) {
        this.activeButton = activeButton;
    }
}