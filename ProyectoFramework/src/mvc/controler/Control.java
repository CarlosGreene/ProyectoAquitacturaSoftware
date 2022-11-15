package mvc.controler;

import java.util.Hashtable;

import javax.swing.JFrame;

import mvc.model.Model;

public abstract class Control{
    protected Hashtable<Integer, Model> models;
    protected JFrame view;

    public abstract void addModels(int key, Model model);
    public abstract void UpdateData(int key);
    public void setView(JFrame view){
        this.view = view;
    }
}
