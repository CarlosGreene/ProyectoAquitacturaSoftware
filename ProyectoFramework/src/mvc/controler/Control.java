package mvc.controler;

import java.util.Hashtable;

import mvc.model.Model;

public abstract class Control{
    protected Hashtable<Integer, Model> models;

    public abstract void addModels(int key, Model model);
    public abstract void UpdateData(int key);
}
