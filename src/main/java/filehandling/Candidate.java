package filehandling;

public class Candidate extends Person {
    private boolean status;

    public Candidate(int id, String name, int age){
        super(id, name, age);
    }
    public Candidate(String name, int age,boolean status){
        super(name,age);
        this.status = status;
    }
    public Candidate(int id, String name, int age, boolean status) {
        super(id, name, age);
        this.status = status;
    }
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "id=" + this.getId() +
                ", candidateName=" + this.getName() +
                ", candidateAge=" + this.getAge() +

                '}';
    }
}
