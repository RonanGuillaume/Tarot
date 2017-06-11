package view;

import javax.swing.*;
import java.awt.*;
import java.util.Observer;

/**
 * Created by Ronan
 * on 10/06/2017.
 */
public abstract class MainView extends JFrame implements Observer{
    public MainView(String title) throws HeadlessException {
        super(title);
    }
}
