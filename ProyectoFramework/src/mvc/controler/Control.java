package mvc.controler;

import java.util.Hashtable;

import javax.swing.JFrame;

import mvc.model.Model;

public abstract class Control{
    protected Hashtable<String, Model> models;
    protected JFrame view;

    public Control(){  
        models = new Hashtable<String, Model>();      
    }
    
    public abstract void addModels(String name, Model model);
    public abstract void UpdateData(String name);
    public abstract void setView(JFrame view);
}
