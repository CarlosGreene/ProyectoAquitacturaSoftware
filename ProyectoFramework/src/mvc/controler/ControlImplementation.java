package mvc.controler;

import mvc.model.Model;

public class ControlImplementation extends Control{

    @Override
    public void addModels(int key, Model model) {
        models.put(key, model);
    }

    @Override
    public void UpdateData(int key) {
        models.get(key).update();
        models.get(key).notifyObservers();
    }
}
