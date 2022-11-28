package mvc.controler;

import javax.swing.JFrame;

import mvc.model.Model;

public class ControlImplementation extends Control{

    public ControlImplementation(){
        super();
    }

    @Override
    public void addModels(String name, Model model) {
        models.put(name, model);
    }

    @Override
    public void UpdateData(String name) {
        models.get(name).update();
        models.get(name).notifyObservers();
    }

    @Override
    public void setView(JFrame view){
        this.view = view;
    }
}
