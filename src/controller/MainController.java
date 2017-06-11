package controller;

import model.CardGame;
import model.Participant;
import model.Trick;
import model.tarot.tarot4Players.Tarot4PlayersGame;
import model.tarot.tarot4Players.Trick4Players;
import view.HomeView;
import view.MainView;
import view.tarotView.MainViewTarot4Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Ronan
 * on 10/06/2017.
 */
public class MainController implements ActionListener{
    private HomeView homeView;
    private CardGame cardGame;
    private MainView mainView;

    public MainController() {
        homeView = new HomeView(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            switch (e.getActionCommand()){
                case "Create game":
                    onCreate();
                    break;
                case "Add":
                    cardGame.addTrick(new Trick4Players(((MainViewTarot4Player)mainView).getParticipant(), ((MainViewTarot4Player)mainView).getTaker(), ((MainViewTarot4Player)mainView).onAdd()));
                    break;
                case "Remove":
                    for (Trick trick : ((MainViewTarot4Player)mainView).getSelectedTricks()) {
                        cardGame.removeTrick(trick);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid command");
            }
            cardGame.notifyObservers();
        }catch (Exception e1){
            //custom title, error icon
            JOptionPane.showMessageDialog(homeView,
                    e1.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onCreate() {
        ArrayList<Participant> participants = new ArrayList<>();
        for (String name : homeView.getNames()) {
            Participant participant = new Participant(name);
            participants.add(participant);
        }

        switch (homeView.getGame()){
            case "Tarot":
                cardGame = new Tarot4PlayersGame(participants);
                cardGame.addTrick(new Trick4Players(participants));
                homeView.dispose();
                mainView = new MainViewTarot4Player(participants, this);
                cardGame.addObserver(mainView);
                break;
            default:
                throw new IllegalArgumentException("Game not found...");
        }
    }
}
