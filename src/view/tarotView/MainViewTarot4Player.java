package view.tarotView;

import model.Participant;
import model.tarot.ChelemBonus;
import model.tarot.ContractType;
import model.tarot.PoigneeBonus;
import model.tarot.tarot4Players.Tarot4PlayersGame;
import view.MainView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Ronan
 * on 10/06/2017.
 */
public class MainViewTarot4Player extends MainView implements Observer{

    private JPanel mainPanel;
    private TarotTableModel tarotTableModel;
    private JTable trickTable;
    private JButton exitButton;
    private JComboBox<ContractType> contractComboBox;
    private JTextField attackMarkedTextField;
    private JTextField defenceTextField;
    private JComboBox<PoigneeBonus> attackPoigneeComboBox;
    private JComboBox<PoigneeBonus> defencePoigneeComboBox;
    private JComboBox<ChelemBonus> attackChelemComboBox;
    private JComboBox<ChelemBonus> defenceChelemComboBox;
    private JComboBox<Participant> attackComboBox;
    private JButton addButton;
    private JCheckBox attackCheckBox;
    private JCheckBox defenceCheckBox;
    private JLabel pointsContract;
    private JLabel resultLabel;
    private JLabel attackPoints;
    private JLabel defencePoints;
    private JLabel attackTotal;
    private JLabel defenceTotal;

    public MainViewTarot4Player(ArrayList<Participant> participants) throws HeadlessException {
        super("Card game");

        setContentPane(mainPanel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        tarotTableModel = new TarotTableModel(participants);
        trickTable.setModel(tarotTableModel);

        DefaultComboBoxModel<Participant> participantDefaultComboBoxModel = new DefaultComboBoxModel<>();
        for (Participant participant : participants) {
            participantDefaultComboBoxModel.addElement(participant);
        }
        attackComboBox.setModel(participantDefaultComboBoxModel);

        ContractType[] myContractTypes = ContractType.values();
        DefaultComboBoxModel<ContractType> contractTypeDefaultComboBoxModel = new DefaultComboBoxModel<>();
        for (ContractType contractType : myContractTypes) {
            contractTypeDefaultComboBoxModel.addElement(contractType);
        }
        contractComboBox.setModel(contractTypeDefaultComboBoxModel);

        PoigneeBonus[] myPoigneBonus = PoigneeBonus.values();
        DefaultComboBoxModel<PoigneeBonus> poigneeBonusAttackDefaultComboBoxModel = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<PoigneeBonus> poigneeBonusDefenceDefaultComboBoxModel = new DefaultComboBoxModel<>();
        for (PoigneeBonus poigneeBonus : myPoigneBonus) {
            poigneeBonusAttackDefaultComboBoxModel.addElement(poigneeBonus);
            poigneeBonusDefenceDefaultComboBoxModel.addElement(poigneeBonus);
        }
        attackPoigneeComboBox.setModel(poigneeBonusAttackDefaultComboBoxModel);
        defencePoigneeComboBox.setModel(poigneeBonusDefenceDefaultComboBoxModel);

        ChelemBonus[] myChelemBonus = ChelemBonus.values();
        DefaultComboBoxModel<ChelemBonus> chelemBonusAttackDefaultComboBoxModel = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<ChelemBonus> chelemBonusDefenceDefaultComboBoxModel = new DefaultComboBoxModel<>();
        for (ChelemBonus chelemBonus : myChelemBonus) {
            chelemBonusAttackDefaultComboBoxModel.addElement(chelemBonus);
            chelemBonusDefenceDefaultComboBoxModel.addElement(chelemBonus);
        }
        attackChelemComboBox.setModel(chelemBonusAttackDefaultComboBoxModel);
        defenceChelemComboBox.setModel(chelemBonusDefenceDefaultComboBoxModel);

        //Minimize the frame's size and freeze the minimum size
        pack();
        setMinimumSize(getSize());

        //Set the frame on the middle screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        setVisible(true);

        exitButton.addActionListener(e -> {
            dispose();
            System.exit(0);
        });

        addButton.addActionListener(e -> onAdd());
    }

    private void onAdd() {

    }

    @Override
    public void update(Observable o, Object arg) {
        tarotTableModel.setTricks(((Tarot4PlayersGame)o).getTricks());
    }
}
