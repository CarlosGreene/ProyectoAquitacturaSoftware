package mvc.view;

import mvc.controler.Control;

import javax.swing.JFrame;

public abstract class View extends JFrame{
    protected Control control;

    public abstract void setControl(Control control);
}
