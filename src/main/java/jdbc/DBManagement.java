package jdbc;

import filehandling.Batch;
import filehandling.Candidate;
import filehandling.Interviewer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class DBManagement {
    DBConnection db = new DBConnection();
    Scanner scanner = new Scanner(System.in);

    public void inputCandidate() throws SQLException, ClassNotFoundException {

        System.out.println("name");
        String name = scanner.nextLine();

        System.out.println("age");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.println("status");
        Boolean status = Boolean.parseBoolean(scanner.nextLine());

        Candidate candidate = new Candidate(name, age, status);
        saveCandidate(candidate);
    }

    public void saveCandidate(Candidate candidate) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Candidate(name,age,status) VALUES (?,?,?)";
        PreparedStatement ps = db.getConnection().prepareStatement(sql);

        ps.setString(1, candidate.getName());
        ps.setInt(2, candidate.getAge());
        ps.setString(3, String.valueOf(candidate.isStatus()));

        int result = ps.executeUpdate();
        if (result > 0) {
            System.out.println("success");
        } else {
            System.out.println("fail");
        }
        db.getConnection().close();
    }

    public void inputInterviewer() throws SQLException, ClassNotFoundException {
        System.out.println("name");
        String name = scanner.nextLine();

        System.out.println("age");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.println("experience");
        int experience = scanner.nextInt();
        scanner.nextLine();

        Interviewer interviewer = new Interviewer(name, age, experience);
        saveInterviewer(interviewer);
    }

    public void saveInterviewer(Interviewer interviewer) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Interviewer VALUES (?,?,?)";
        PreparedStatement ps = db.getConnection().prepareStatement(sql);

        ps.setString(1, interviewer.getName());
        ps.setInt(2, interviewer.getAge());
        ps.setInt(3, interviewer.getExperience());

        int result = ps.executeUpdate();
        if (result > 0) {
            System.out.println("success");
        } else {
            System.out.println("fail");
        }
        db.getConnection().close();
    }

    public void inputBatch() throws SQLException, ClassNotFoundException {

        System.out.println("Interviewer Id");
        int interviewerID = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Candidate Id");
        int candidateID = scanner.nextInt();
        scanner.nextLine();

        Batch batch = new Batch(interviewerID, candidateID);
        saveBatch(batch);
    }

    public void saveBatch(Batch batch) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Batch VALUES (?,?)";
        PreparedStatement ps = db.getConnection().prepareStatement(sql);

        ps.setInt(1, batch.getInterviewerID());
        ps.setInt(2, batch.getCandidateID());

        int result = ps.executeUpdate();
        if (result > 0) {
            System.out.println("success");
        } else {
            System.out.println("fail");
        }
        db.getConnection().close();
    }

    public List<Batch> fetchAllBatch() throws SQLException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        Statement statement = db.getConnection().createStatement();
        String sql = "SELECT * FROM Batch";
        ResultSet rs = statement.executeQuery(sql);

        List<Batch> batches = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            int interviewerID = rs.getInt("interviewerID");
            int candidateID = rs.getInt("candidateID");
            Batch batch = new Batch(id, interviewerID, candidateID);
            batches.add(batch);
        }
        db.getConnection().close();
        return batches;
    }

    public void updateCandidateStatus(int candidateID, String result) throws SQLException, ClassNotFoundException {
        String sql = String.format("UPDATE Candidate SET status = '%s' WHERE id ='%s'", result, candidateID);
        PreparedStatement ps = db.getConnection().prepareStatement(sql);

        if (ps.executeUpdate() > 0) {
            System.out.println("success");
        } else {
            System.out.println("fail");
        }
        db.getConnection().close();
    }
    public List<Candidate> fetchCandidate() throws SQLException, ClassNotFoundException {

        Scanner sc = new Scanner(System.in);
        Statement statement = db.getConnection().createStatement();
        String sql = "SELECT * FROM Candidate ORDER BY name ASC";
        ResultSet rs = statement.executeQuery(sql);

        List<Candidate> candidateList = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            String st = rs.getString("status");
            Candidate candidate = new Candidate(id,name,age);
            if("pass".equals(st)){
                candidate.setStatus(true);
            }else{
                candidate.setStatus(false);
            }
            candidateList.add(candidate);
        }
        return candidateList;
    }
    public List<Interviewer> fetchInterviewer() throws SQLException, ClassNotFoundException {

        Scanner sc = new Scanner(System.in);
        Statement statement = db.getConnection().createStatement();
        String sql = "SELECT * FROM Interviewer";
        ResultSet rs = statement.executeQuery(sql);

        List<Interviewer> interviewerList = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            int experience = rs.getInt("experience");
            Interviewer interviewer = new Interviewer(id, name, age, experience);
            interviewerList.add(interviewer);
        }
        return interviewerList;
    }
    public void printAllInformation() throws SQLException, ClassNotFoundException {
        List<Batch> batchList = fetchAllBatch();
        List<Interviewer> interviewerList = fetchInterviewer();
        List<Candidate> candidateList = fetchCandidate();

        System.out.println("-----------------------------------------");
        for (int i = 0; i < batchList.size(); i++) {
            System.out.println(batchList.get(i));
        }
        System.out.println("------------------------");
        for (int i = 0; i < interviewerList.size(); i++) {
            System.out.println(interviewerList.get(i));
        }
        System.out.println("------------------------");
        for (int i = 0; i < candidateList.size(); i++) {
            System.out.println(candidateList.get(i));
        }
        System.out.println("-----------------------------------------");
    }

    public void deleteOneBatch() throws SQLException, ClassNotFoundException {
        List<Batch> batchList = fetchAllBatch();
        for(Batch batch : batchList){
            System.out.println(batch);
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Which batch id that you want to delete: ");
        int batchId = sc.nextInt();
        String sql = String.format("DELETE FROM Batch WHERE id ='%d'",batchId);
        PreparedStatement ps = db.getConnection().prepareStatement(sql);

        int result = ps.executeUpdate();
        if (result > 0) {
            System.out.println("delete success");
        } else {
            System.out.println("delete fail");
        }
        db.getConnection().close();
    }
    public Map<Integer, ArrayList<Integer>> printCandidateQueue() throws SQLException, ClassNotFoundException {

        String sql = "SELECT interviewerID,candidateID FROM Batch";
        Statement statement = db.getConnection().createStatement();
        ResultSet rs = statement.executeQuery(sql);

        List<Integer> interviewerIdList = new ArrayList<>();
        List<Integer> candidateIdList = new ArrayList<>();

        while(rs.next()){
            interviewerIdList.add(rs.getInt("interviewerID"));
            candidateIdList.add(rs.getInt("candidateID"));
        }
        db.getConnection().close();
        Map<Integer, ArrayList<Integer>> list = new HashMap<>();
        for(int i = 0; i < interviewerIdList.size(); i++) {
            for(int j = 0; j < candidateIdList.size(); j++) {
                if(i == j){
                    list.putIfAbsent(interviewerIdList.get(i),new ArrayList<>());
                    ArrayList<Integer> temp = list.get(interviewerIdList.get(i));
                    temp.add(candidateIdList.get(j));
                }
            }
        }
        return list;
    }
    public void removeOneCandidateFromBatch() throws SQLException, ClassNotFoundException {

        Scanner sc = new Scanner(System.in);

        System.out.println("There are bunch of batches, which batch he is in ?");
        System.out.println("I mean his interviewer ID: ");
        int interviewerID = sc.nextInt();
        sc.nextLine();

        System.out.println("Who is that candidate, enter his ID: ");
        int candidateID = sc.nextInt();
        sc.nextLine();

        String sql = String.format("DELETE FROM Batch WHERE interviewerID='%d' AND candidateID='%d'",interviewerID,candidateID);
        PreparedStatement ps = db.getConnection().prepareStatement(sql);

        int result = ps.executeUpdate();
        if (result > 0) {
            System.out.println("delete success");
        } else {
            System.out.println("delete fail");
        }
        db.getConnection().close();
    }

    public Map<Integer, ArrayList<Integer>> removeListOfCandidates() throws SQLException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter key id to remove the whole list: ");
        int id = sc.nextInt();
        sc.nextLine();
        Map<Integer, ArrayList<Integer>> list = printCandidateQueue();
        list.remove(id);
        System.out.println("List of remaining batches: ");
        return list;
    }

}
