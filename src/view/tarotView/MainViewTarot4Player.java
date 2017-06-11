package view.tarotView;

import model.Participant;
import model.Trick;
import model.tarot.*;
import model.tarot.tarot4Players.Tarot4PlayersGame;
import view.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
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
    private JComboBox<NumberOfBouts> boutsComboBox;
    private JSpinner attackPointsSpinner;
    private JSpinner defencePointsSpinner;
    private JButton removeButton;

    public MainViewTarot4Player(ArrayList<Participant> participants, ActionListener actionListener) throws HeadlessException {
        super("Card game");

        setContentPane(mainPanel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        pointsContract.setText("(56)");
        resultLabel.setText("Victory");
        resultLabel.setForeground(Color.WHITE);
        resultLabel.setBackground(new Color(0, 114, 0));
        resultLabel.setOpaque(true);

        removeButton.setEnabled(false);

        tarotTableModel = new TarotTableModel(participants);
        trickTable.setModel(tarotTableModel);

        SpinnerNumberModel attackSpinnerNumberModel = new SpinnerNumberModel(56, 0, 92, 1);
        SpinnerNumberModel defenceSpinnerNumberModel = new SpinnerNumberModel(36, 0, 92, 1);
        attackPointsSpinner.setModel(attackSpinnerNumberModel);
        defencePointsSpinner.setModel(defenceSpinnerNumberModel);

        DefaultComboBoxModel<NumberOfBouts> numberOfBoutsDefaultComboBoxModel = new DefaultComboBoxModel<>();
        for (NumberOfBouts numberOfBouts : NumberOfBouts.values()) {
            numberOfBoutsDefaultComboBoxModel.addElement(numberOfBouts);
        }
        boutsComboBox.setModel(numberOfBoutsDefaultComboBoxModel);

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

        ListSelectionModel listSelectionModel = trickTable.getSelectionModel();
        listSelectionModel.addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) {
                return;
            }

            int[] selection = trickTable.getSelectedRows();

            boolean test = true;

            if (selection != null){
                for (int index : selection) {
                    if (index == 0 || index == tarotTableModel.getRowCount() - 1){
                        test = false;
                        break;
                    }
                }
            }

            boolean isSelection = selection != null && selection.length != 0;
            removeButton.setEnabled(isSelection && test);
        });

        addButton.addActionListener(actionListener);
        boutsComboBox.addItemListener(e -> numberOfBoutsComboBoxChanged());

        attackPointsSpinner.addChangeListener(e -> attackPointsChanged());
        defencePointsSpinner.addChangeListener(e -> defencePointsChanged());

        removeButton.setActionCommand("Remove");
        removeButton.addActionListener(actionListener);
    }

    private void defencePointsChanged() {
        attackPointsSpinner.setValue(92 - (int)defencePointsSpinner.getValue());
        refreshResult();
    }

    private void attackPointsChanged() {
        defencePointsSpinner.setValue(92 - (int)attackPointsSpinner.getValue());
        refreshResult();
    }

    private void refreshResult() {
        if ((int)attackPointsSpinner.getValue() < ((NumberOfBouts)boutsComboBox.getSelectedItem()).getContractValue()){
            resultLabel.setText("Defeat");
            resultLabel.setBackground(new Color(114, 2, 0));
        }
        else {
            resultLabel.setText("Victory");
            resultLabel.setBackground(new Color(0, 114, 0));
        }
    }

    private void numberOfBoutsComboBoxChanged() {
        pointsContract.setText("(" +
                Integer.toString(((NumberOfBouts)boutsComboBox.getSelectedItem()).getContractValue()) + ")");
        refreshResult();
    }

    public TrickTarotDescription onAdd() throws IllegalArgumentException{
        PetitAuBoutBonus petitAuBoutBonus;
        if (attackCheckBox.isSelected() && defenceCheckBox.isSelected()){
            throw new IllegalArgumentException("Attack and defense can't bring petit au bout both.");
        }
        else if (attackCheckBox.isSelected()){
            petitAuBoutBonus = PetitAuBoutBonus.ATTACK;
        }
        else if (defenceCheckBox.isSelected()){
            petitAuBoutBonus = PetitAuBoutBonus.DEFENCE;
        }
        else {
            petitAuBoutBonus = PetitAuBoutBonus.NONE;
        }
        return new TrickTarotDescription((PoigneeBonus) attackPoigneeComboBox.getSelectedItem(), petitAuBoutBonus,
                (int)attackPointsSpinner.getValue(), (int)defencePointsSpinner.getValue(),
                (ContractType) contractComboBox.getSelectedItem(), (ChelemBonus) attackChelemComboBox.getSelectedItem(),
                (Participant) attackComboBox.getSelectedItem(), (NumberOfBouts) boutsComboBox.getSelectedItem());
    }

    public List<Participant> getParticipant(){
        return tarotTableModel.getHeader();
    }

    public Participant getTaker(){
        return (Participant) attackComboBox.getSelectedItem();
    }

    public List<Trick> getSelectedTricks(){
        ArrayList<Trick> result = new ArrayList<>();
        for (int index : trickTable.getSelectedRows()) {
            result.add(tarotTableModel.getElementAt(index));
        }
        return result;
    }

    @Override
    public void update(Observable o, Object arg) {
        tarotTableModel.setTricks(((Tarot4PlayersGame)o).getTricks());
    }
}
