package graphics;

import logics.GameManager;
import logics.Player;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameWindow extends JFrame {

    static JPanel Button1Panel = new JPanel();
    static JButton player1Exit = new JButton("<HTML><BODY><P ALIGN=CENTER>Rentrer chez soi<BR>(et faire des chaises en bois)</P></BODY></HTML>");
    static JButton player1Continue = new JButton("<HTML><BODY>Continuer dans le donjon<BR>&nbsp</BODY></HTML>");
    static JButton player1Infos = new JButton("<HTML><BODY><P ALIGN=CENTER>Afficher les informations<BR>sur la manche</P></BODY></HTML>");
    static JPanel Button2Panel = new JPanel();
    static JButton player2Exit = new JButton("<HTML><BODY><P ALIGN=CENTER>Rentrer chez soi<BR>(et faire des chaises en bois)</P></BODY></HTML>");
    static  JButton player2Continue = new JButton("<HTML><BODY>Continuer dans le donjon<BR>&nbsp</BODY></HTML>");
    static JButton player2Infos = new JButton("<HTML><BODY><P ALIGN=CENTER>Afficher les informations<BR>sur la manche</P></BODY></HTML>");
    static MyButtonListenerInfos buttonListenerPlayer2Infos = new MyButtonListenerInfos(GameManager.getInstance().getPlayer2(),player2Infos);
    static  MyButtonListenerInfos buttonListenerPlayer1Infos = new MyButtonListenerInfos(GameManager.getInstance().getPlayer1(),player1Infos);

    public GameWindow() {
        super();
        try {
            final Image backgroundImage = javax.imageio.ImageIO.read(
                    new File("src/img/background.png"));
            setContentPane(new JPanel(new BorderLayout()) {
                @Override public void paintComponent(Graphics g) {
                    g.drawImage(backgroundImage, 0, 0, null);
                }
            });

            JPanel PanelJeu = new JPanel();
            PanelJeu.setLayout(new BorderLayout(5,5));
            PanelJeu.setOpaque(false);

            JPanel PlayerPanel = new JPanel();
            PlayerPanel.setLayout(new GridLayout(1,2,5,5));
            PlayerPanel.setOpaque(false);


            JPanel textCenter = new JPanel();
            textCenter.setOpaque(false);
            JLabel labelText = new JLabel("<HTML><BODY><P ALIGN=CENTER>Jeune canard aventurier,<BR>Bercé par les légendes de votre glorieux ancetre Herbert, vous décidez de pénétrer dans le chateau du terrible Ravenduck, le célèbre comte canard vampire.<BR>Vous espérez récolter gloire et richesse, mais le chateau est sombre, terrifiant et dangereux. Qui sait ce qui vous attend ?<BR><BR>Dans ce jeu, chacun.e des deux joueurs.euses choisit à chaque étape si elle ou il poursuit ou si elle ou il préfère rentrer pour mettre sa fortune à l'abri.<BR>Le jeu se joue en cinq manches.<BR>Attention cependant, vous commencez chaque manche avec 75 points de vie, si vous tombez à 0, vous devrez fuir le chateau et rentrer chez vous sans le moindre centime !<BR>Le but du jeu est de terminer les cinq manches avec plus d'or que votre concurrent.e.</P></BODY></HTML>");
            labelText.setFont(new Font("Arial", Font.PLAIN, 11));
            labelText.setOpaque(false);
            textCenter.add(labelText);

            //Player 1 buttons
            Button1Panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
            //Button Exit
            MyButtonListenerExit buttonListenerPlayer1Exit = new MyButtonListenerExit(GameManager.getInstance().getPlayer1(),labelText,1);
            player1Exit.addActionListener(buttonListenerPlayer1Exit);
            Button1Panel.add(player1Exit);

            //Button Continuer
            MyButtonListenerContinue buttonListenerPlayer1Continue = new MyButtonListenerContinue(GameManager.getInstance().getPlayer1(),labelText,1);
            player1Continue.addActionListener(buttonListenerPlayer1Continue);
            Button1Panel.add(player1Continue);

            //Button Infos
           player1Infos.addActionListener(buttonListenerPlayer1Infos);
            Button1Panel.add(player1Infos);

            Button1Panel.setOpaque(false);

            //Player 2 buttons
            Button2Panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
            //Button Exit
            MyButtonListenerExit buttonListenerPlayer2Exit = new MyButtonListenerExit(GameManager.getInstance().getPlayer2(),labelText,2);
            player2Exit.addActionListener(buttonListenerPlayer2Exit);
            Button2Panel.add(player2Exit);

            //Button Continuer
            MyButtonListenerContinue buttonListenerPlayer2Continue = new MyButtonListenerContinue(GameManager.getInstance().getPlayer2(),labelText,2);
            player2Continue.addActionListener(buttonListenerPlayer2Continue);
            Button2Panel.add(player2Continue);

            //Button Infos
            player2Infos.addActionListener(buttonListenerPlayer2Infos);
            Button2Panel.add(player2Infos);


            Button2Panel.setOpaque(false);

            JPanel centerPlayer1 = new JPanel();
            centerPlayer1.setLayout(new GridBagLayout());
            centerPlayer1.setOpaque(false);
             JLabel labelPlayer1 = new JLabel("<HTML><BODY><B>Player 1</B></BODY></HTML>");
            labelPlayer1.setFont(new Font("Serif", Font.PLAIN, 30));
            labelPlayer1.setOpaque(false);

            JPanel centerPlayer2 = new JPanel();
            centerPlayer2.setLayout(new GridBagLayout());
            centerPlayer2.setOpaque(false);
            JLabel labelPlayer2 = new JLabel("<HTML><BODY><B>Player 2</B></BODY></HTML>");
            labelPlayer2.setFont(new Font("Serif", Font.PLAIN, 30));
            labelPlayer2.setOpaque(false);

            centerPlayer1.add(labelPlayer1);
            centerPlayer2.add(labelPlayer2);

            JPanel Player1Panel = new JPanel();
            Player1Panel.setLayout(new BorderLayout(5,5));
            Player1Panel.add(centerPlayer1,BorderLayout.NORTH);
            Player1Panel.add(Button1Panel,BorderLayout.CENTER);
            Player1Panel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));

            JPanel Player2Panel = new JPanel();
            Player2Panel.setLayout(new BorderLayout(5,5));
            Player2Panel.add(centerPlayer2,BorderLayout.NORTH);
            Player2Panel.add(Button2Panel,BorderLayout.CENTER);
            Player2Panel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));

            PlayerPanel.add(Player1Panel);
            PlayerPanel.add(Player2Panel);

            JPanel titre = new JPanel();
            titre.setOpaque(false);
            JLabel labelTitre = new JLabel("<HTML><BODY>Le Chateau de Ravenduck</BODY></HTML>", SwingConstants.CENTER);
            labelTitre.setFont(new Font("Times New Roman", Font.ITALIC , 40));
            labelTitre.setOpaque(false);
            titre.add(labelTitre);


            PanelJeu.add(titre,BorderLayout.NORTH);
            PanelJeu.add(textCenter,BorderLayout.CENTER);
            PanelJeu.add(PlayerPanel, BorderLayout.SOUTH);

            this.add(PanelJeu);


            this.setPreferredSize(new Dimension(1500,800));
            this.setIconImage(new ImageIcon("src/img/icon.jpg").getImage());
            this.setTitle("                                                                                      " +
                    "                                                                                                          " +
                    "               Le Chateau de Ravenduck");



            this.pack();
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setVisible(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static public void setButtonPlayerPanelApresAction(int numeroPlayer){
        if(numeroPlayer==1) {
            Button1Panel.remove(player1Continue);
            Button1Panel.remove(player1Exit);
            Button1Panel.revalidate();
            Button1Panel.repaint();
        }
        if(numeroPlayer==2) {
            Button2Panel.remove(player2Continue);
            Button2Panel.remove(player2Exit);
            Button2Panel.revalidate();
            Button2Panel.repaint();
        }
    }

    static public void setButtonPlayerPanelAvantAction(boolean test) {
        if(test){
            Button1Panel.remove(player1Infos);
            Button1Panel.revalidate();
            Button1Panel.repaint();
            Button1Panel.add(player1Exit);
            Button1Panel.add(player1Continue);
            Button1Panel.add(player1Infos);
            Button2Panel.remove(player2Infos);
            Button2Panel.revalidate();
            Button2Panel.repaint();
            Button2Panel.add(player2Exit);
            Button2Panel.add(player2Continue);
            Button2Panel.add(player2Infos);
        }else {
            if ((GameManager.getInstance().getPlayer1().getAction() != Player.Action.NOT_SELECTED || !GameManager.getInstance().getPlayer1().isInGame()) && (GameManager.getInstance().getPlayer2().getAction() != Player.Action.NOT_SELECTED || !GameManager.getInstance().getPlayer2().isInGame())) {
                if (GameManager.getInstance().getPlayer1().isInGame()) {
                    Button1Panel.remove(player1Infos);
                    Button1Panel.revalidate();
                    Button1Panel.repaint();
                    Button1Panel.add(player1Exit);
                    Button1Panel.add(player1Continue);
                    Button1Panel.add(player1Infos);
                    player1Infos.setText("<HTML><BODY><P ALIGN=CENTER>Afficher les informations<BR>sur la manche</P></BODY></HTML>");
                    buttonListenerPlayer1Infos.setActiveButton(false);
                }
                if (GameManager.getInstance().getPlayer2().isInGame()) {
                    Button2Panel.remove(player2Infos);
                    Button2Panel.revalidate();
                    Button2Panel.repaint();
                    Button2Panel.add(player2Exit);
                    Button2Panel.add(player2Continue);
                    Button2Panel.add(player2Infos);
                    player2Infos.setText("<HTML><BODY><P ALIGN=CENTER>Afficher les informations<BR>sur la manche</P></BODY></HTML>");
                    buttonListenerPlayer2Infos.setActiveButton(false);
                }
            }
        }
    }
}
