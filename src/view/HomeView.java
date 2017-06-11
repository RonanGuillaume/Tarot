package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ronan
 * on 10/06/2017.
 */
public class HomeView extends JFrame{
    private JList<String> list;
    private JButton exitButton;
    private JButton createButton;
    private JTextField nameTextField;
    private JButton addButton;
    private JComboBox<String> gameComboBox;
    private JButton removeButton;
    private JPanel mainPanel;
    private DefaultListModel<String> listModel;

    public HomeView(ActionListener actionListener) throws HeadlessException {
        super("Create game");

        setContentPane(mainPanel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        listModel = new DefaultListModel<>();
        list.setModel(listModel);

        //Minimize the frame's size and freeze the minimum size
        pack();
        setMinimumSize(getSize());

        //Set the frame on the middle screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        removeButton.setEnabled(false);

        setVisible(true);

        createButton.setActionCommand("Create game");
        createButton.addActionListener(actionListener);

        exitButton.addActionListener(e -> {
            dispose();
            System.exit(0);
        });

        addButton.addActionListener(e -> onAdd());

        //Enable or not the removeButton
        ListSelectionModel listSelectionModel = list.getSelectionModel();
        listSelectionModel.addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) {
                return;
            }

            int[] selection = list.getSelectedIndices();

            boolean isSelection = selection != null && selection.length != 0;
            removeButton.setEnabled(isSelection);
        });

        removeButton.addActionListener(e -> onRemove());
    }

    public String getGame(){
        return gameComboBox.getSelectedItem().toString();
    }

    public List<String> getNames(){
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0 ; i < listModel.size() ; ++i){
            result.add(listModel.getElementAt(i));
        }
        return result;
    }

    private void onRemove() {
        int[] selection = list.getSelectedIndices();

        for (int i = selection.length - 1 ; i >= 0 ; --i) {
            listModel.remove(selection[i]);
        }
    }

    private void onAdd() {
        if (nameTextField.getText().length() == 0){
            //custom title, error icon
            JOptionPane.showMessageDialog(this,
                    "You must add a name...",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        else {
            boolean test = false;
            for (String name : getNames()) {
                if (name.equalsIgnoreCase(nameTextField.getText())){
                    test = true;
                    break;
                }
            }
            if (test){
                nameTextField.setText("");
                //custom title, error icon
                JOptionPane.showMessageDialog(this,
                        "This name already exists...",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            else{
                listModel.addElement(nameTextField.getText());
                nameTextField.setText("");
            }
        }
    }
}
