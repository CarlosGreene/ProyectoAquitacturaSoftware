package mvc.model;

public class Candidate extends Model{
    
    private String name;
    private int numVotes = 0;

    public Candidate(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getNumVotes() {
        return numVotes;
    }

    @Override
    public void update() {
        numVotes++;
        setChanged();
    }

}
