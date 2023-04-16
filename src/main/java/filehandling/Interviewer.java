package filehandling;

public class Interviewer extends Person {
    private int experience;
    public Interviewer(){

    }
    public Interviewer(int id, String name, int age, int experience){
        super(id, name, age);
        this.experience = experience;
    }
    public Interviewer(String name,int age,int experience) {
        super(name,age);
        this.experience = experience;
    }
    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "Interviewer{" +
                "id="   + this.getId() +
                ", interviewerName=" +this.getName() +
                ", interviewerAge=" +this.getAge() +
                ", experience=" + experience +
                '}';
    }
}
