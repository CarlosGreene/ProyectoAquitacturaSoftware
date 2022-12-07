package mvc.model;
import java.util.Observable;
import java.util.Observer;

public abstract class Model extends Observable{

    protected int id;

    public Model() {
    }
    
    public abstract void update();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}