package logics;

import graphics.GameWindow;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class GameManager {
    private static GameManager single_instance;
    private Player player1;
    private Player player2;
    private int manche;
    private int pvMax;
    private List<Story> stories;
    private List<Story> storiesAleatoire;

    private GameManager(List<Story> stories) {
        single_instance=null;
        this.pvMax = 100;
        player1 = new Player(pvMax);
        player2 = new Player(pvMax);
        this.stories =stories;
        this.manche=1;
        storiesAleatoire = new ArrayList<>();
        for (Story story:stories) {
            Story newStory = new Story(story.getViePerdue(),story.getOrGagne(),story.getMessage());
            storiesAleatoire.add(newStory);
        }

    }

    public static GameManager getInstance(){
        if (single_instance == null) {
            generateInstance();
        }
        return single_instance;
    }

    public static void generateInstance(){
        if (single_instance == null) {
            List<Story> storiesNew = new ArrayList<Story>();
            //Connexion BDD
            String url = "jdbc:mysql://localhost/ravenduck";
            String user = "guest";
            String password = "guest";
            Connection connection = null;
            Statement statement = null;
            ResultSet resultSet = null;
            List<Story> listOfStories = new ArrayList<Story>();
            try{
                connection = DriverManager.getConnection(url, user, password);
                statement = connection.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM stories");
                while (resultSet.next()) {
                    String descriptionStory = resultSet.getString(2);
                    int pvStory = resultSet.getInt(3);
                    int goldStory = resultSet.getInt(4);
                    Story story = new Story(pvStory,goldStory,descriptionStory);
                    listOfStories.add(story);
                }
                single_instance=new GameManager(listOfStories);
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    public int getManche() {
        return manche;
    }

    public void setManche(int manche) {
        this.manche = manche;
    }

    public void play(JLabel labelText){
        if((player1.getAction()!= Player.Action.NOT_SELECTED||!player1.isInGame())&&(player2.getAction()!= Player.Action.NOT_SELECTED||!player2.isInGame())) {
            player1.setAction(Player.Action.NOT_SELECTED);
            player2.setAction(Player.Action.NOT_SELECTED);

            if (storiesAleatoire.size() == 0) {
                for (Story story:stories) {
                    Story newStory = new Story(story.getViePerdue(),story.getOrGagne(),story.getMessage());
                    storiesAleatoire.add(newStory);
                }
            }

            String text = new String("<HTML><BODY><P ALIGN=CENTER>");

            if (player1.isInGame()) {
                int nbStory = storiesAleatoire.size();
                int nombreAleatoire = (int) (Math.random() * (nbStory));
                System.out.println(storiesAleatoire.get(nombreAleatoire));
                Story story = new Story(storiesAleatoire.get(nombreAleatoire).getViePerdue(), storiesAleatoire.get(nombreAleatoire).getOrGagne(), storiesAleatoire.get(nombreAleatoire).getMessage());
                storiesAleatoire.remove(nombreAleatoire);
                if (storiesAleatoire.size() == 0) {
                    for (Story s:stories) {
                        Story newStory = new Story(s.getViePerdue(),s.getOrGagne(),s.getMessage());
                        storiesAleatoire.add(newStory);
                    }
                }
                player1.setNbVies(player1.getNbVies() + story.getViePerdue());
                if (player1.getNbVies() < 1) {
                    text+="Player 1 message : <BR>" + story.getMessage();
                    text+="<BR><BR>";
                    player1.setInGame(false);
                    player1.setOrManche(0);
                } else {
                    player1.setOrManche(player1.getOrManche() + story.getOrGagne());
                    text+="Player 1 message : <BR>" + story.getMessage();
                    text+="<BR><BR>";
                }
            }
            if (player2.isInGame()) {
                int nbStory = storiesAleatoire.size();
                int nombreAleatoire = (int) (Math.random() * (nbStory));
                Story story = new Story(storiesAleatoire.get(nombreAleatoire).getViePerdue(), storiesAleatoire.get(nombreAleatoire).getOrGagne(), storiesAleatoire.get(nombreAleatoire).getMessage());
                System.out.println(storiesAleatoire.get(nombreAleatoire));
                storiesAleatoire.remove(nombreAleatoire);
                player2.setNbVies(player2.getNbVies() + story.getViePerdue());
                if (player2.getNbVies() < 1) {
                    text+="Player 2 message : <BR>" + story.getMessage();
                    text+="<BR>";
                    player2.setInGame(false);
                    player2.setOrManche(0);
                } else {
                    player2.setOrManche(player2.getOrManche() + story.getOrGagne());
                    text+="Player 2 message : <BR>" + story.getMessage();
                    text+="<BR>";
                }
            }
            if(!text.equals("<HTML><BODY><P ALIGN=CENTER>")){
                String text2 = new String("");
                for(int i=0;i<text.length();i++){
                    text2+=text.charAt(i);
                    if(text.charAt(i)=='.'||text.charAt(i)=='!'||text.charAt(i)=='?'){
                        text2+="<BR>";
                    }
                }
                text2+="</P></BODY></HTML>";
                labelText.setFont(new Font("Arial", Font.ITALIC , 11));
                labelText.setText(text2);
            }
        }

        if (!player1.isInGame() && !player2.isInGame()) {
            reinitialize(labelText);
        }
    }

    public int getPvMax() {
        return pvMax;
    }

    public void reinitialize(JLabel labelText){

        GameWindow.setButtonPlayerPanelAvantAction(true);
        String text = new String("");
        if(getManche()<5){
            setManche(getManche()+1);
            //Update player 1
            player1.setNbVies(getPvMax());
            player1.setOr(player1.getOr()+player1.getOrManche());
            player1.setOrManche(0);
            player1.setInGame(true);
            player1.setAction(Player.Action.NOT_SELECTED);
            //Update player 2
            player2.setNbVies(getPvMax());
            player2.setOr(player2.getOr()+player2.getOrManche());
            player2.setOrManche(0);
            player2.setInGame(true);
            player1.setAction(Player.Action.NOT_SELECTED);
            text+="<HTML><BODY><P ALIGN=CENTER>Manche terminée !";
            if(player1.getOr()>player2.getOr()){
                text+="<BR>Attention, pour l'instant c'est Player 1 qui gagne.<BR>Ressaisis toi Player 2 ;)";
            } else if (player1.getOr()<player2.getOr()) {
                text+="<BR>Attention, pour l'instant c'est Player 2 qui gagne.<BR>Ressaisis toi PLayer 1 ;)";
            }else{
                text+="<BR>Attention, pour l'instant vous êtes égalités !<BR>Il faut réussir à prendre l'avantage sur l'adversaire !";
            }
            text+="</P></BODY></HTML>";
        }else{
            player1.setOr(player1.getOr()+player1.getOrManche());
            player2.setOr(player2.getOr()+player2.getOrManche());
            text+="<HTML><BODY><P ALIGN=CENTER>Partie terminée !<BR>Le/la gagnant.e est .......<BR><BR><BR><BR>";
            if(player1.getOr()>player2.getOr()){
                text+="Player 1<BR>Félicitations !";
            } else if (player1.getOr()<player2.getOr()) {
                text+="Player 2<BR>Félicitations !";
            }else{
                text+="Personne ! Et oui vous êtes égalités !<BR>Félicitations !";
            }
            text+="</P></BODY></HTML>";
        }
        labelText.setText(text);
        labelText.setFont(new Font("Arial", Font.ITALIC , 25));
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
}
