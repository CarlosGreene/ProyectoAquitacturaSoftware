package mvc.model;
import java.util.Observable;

public abstract class Model extends Observable{

    public abstract void update();
}