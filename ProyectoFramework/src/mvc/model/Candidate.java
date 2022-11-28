package mvc.model;

import java.util.Observer;

public class Candidate extends Model{
    
    private String name;
    private int numVotes = 0;

    public Candidate(int id, String name){
        this.setId(id);
        this.setName(name);;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumVotes() {
        return numVotes;
    }

    public void setNumVotes(int numVotes) {
        this.numVotes = numVotes;
    }

    @Override
    public void update() {
        numVotes++;
        setChanged();
    }

    @Override
    public synchronized void addObserver(Observer o) {
        // TODO Auto-generated method stub
        super.addObserver(o);
    }
}
