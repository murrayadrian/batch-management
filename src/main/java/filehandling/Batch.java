package filehandling;

public class Batch {
    private int id;
    private int interviewerID;
    private int candidateID;

    public Batch(int id, int interviewerID, int candidateID) {
        this.id = id;
        this.interviewerID = interviewerID;
        this.candidateID = candidateID;
    }

    public Batch(int interviewerID, int candidateID) {
        this.interviewerID = interviewerID;
        this.candidateID = candidateID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInterviewerID() {
        return interviewerID;
    }

    public void setInterviewerID(int interviewerID) {
        this.interviewerID = interviewerID;
    }

    public int getCandidateID() {
        return candidateID;
    }

    public void setCandidateID(int candidateID) {
        this.candidateID = candidateID;
    }

    @Override
    public String toString() {
        return "Batch{" +
                "id=" + id +
                ", interviewerID=" + interviewerID +
                ", candidateID=" + candidateID +
                '}';
    }
}
