package filehandling;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;

public class Management {

    public void printAll() throws IOException {

        BufferedReader readCandidate = new BufferedReader(new FileReader("candidate.csv"));
        BufferedReader readInterviewer = new BufferedReader(new FileReader("interviewer.csv"));
        String line="";

        List<Candidate> candidateList = new ArrayList<>();
        List<Interviewer> interviewerList = new ArrayList<>();

        while((line = readCandidate.readLine()) != null) {
          String[] values = line.split((","));
          Candidate candidate = createCandidate(values);
          candidateList.add(candidate);
        }
        for(int i = 0;i<candidateList.size();i++){
            System.out.println(candidateList.get(i));
        }
        while((line = readInterviewer.readLine()) != null) {
            String[] values = line.split((","));
            Interviewer interviewer = createInterviewer(values);
            interviewerList.add(interviewer);
        }
        for(int i = 0;i<interviewerList.size();i++){
            System.out.println(interviewerList.get(i));
        }
        readCandidate.close();
        readInterviewer.close();
    }

    private static Candidate createCandidate(String[] values){

        int id = Integer.parseInt(values[0]);
        String name = values[1];
        int age = Integer.parseInt(values[2]);
        boolean status;
        if("pass".equals(values[3])){
            status = true;
        }else{
            status = false;
        }

        return new Candidate(id,name,age,status);
    }

    public void printCandidate() throws IOException {

        String path = "candidate.csv";
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line = "";
        List<Candidate> candidateList = new ArrayList<>();
        while((line = br.readLine()) != null) {
            String[] values = line.split(",");
            Candidate candidate = createCandidate(values);
            candidateList.add(candidate);
        }
        for(int i = 0;i<candidateList.size();i++){
            System.out.println(candidateList.get(i));
        }
    }
    private Interviewer createInterviewer(String[] values){

        int id = Integer.parseInt(values[0]);
        String name = values[1];
        int age = Integer.parseInt(values[2]);
        int experience = Integer.parseInt(values[3]);
        return new Interviewer(id, name, age, experience);
    }
    public void inputInterviewer() throws IOException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("people.Interviewer id :");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("people.Interviewer name :");
        String name = scanner.nextLine();

        System.out.println("people.Interviewer age :");
        int age = scanner.nextInt();

        System.out.println("people.Interviewer experience :");
        int experience = scanner.nextInt();

        Interviewer interviewer = new Interviewer(id,name,age,experience);

        FileWriter fw = new FileWriter("interviewer.csv",true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);


        pw.println(id+","+name+","+age+","+experience);
        System.out.println("Data Successfully appended into file");
        pw.flush();

        pw.close();
        bw.close();
        fw.close();
    }

    public void inputCandidate() throws IOException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("people.Candidate id :");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("people.Candidate name :");
        String name = scanner.nextLine();

        System.out.println("people.Candidate age :");
        int age = scanner.nextInt();

        Candidate candidate = new Candidate(id, name, age);

        FileWriter fw = new FileWriter("candidate.csv", true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);

        pw.println(id+","+name+","+age+","+candidate.isStatus());
        System.out.println("Data Successfully appended into file");
        pw.flush();

        pw.close();
        bw.close();
        fw.close();
    }

    public void inputBatch() throws IOException {

        Scanner scanner = new Scanner(System.in);
        List<Integer> candidateIdList = new ArrayList<>();

        System.out.println("people.Interviewer id :");
        int interviewerId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("How many candidate: ");
        int num = scanner.nextInt();
        scanner.nextLine();

        while (num !=0){
            System.out.println("people.Candidate id: ");
            int candidateId = scanner.nextInt();
            scanner.nextLine();
            candidateIdList.add(candidateId);
            num--;
        }

        FileWriter fw = new FileWriter("batch.csv", true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);

        StringBuilder result = new StringBuilder();
        for(int id : candidateIdList) {
            result.append(id);
            result.append(",");
        }
        String candidateIdString = result.length() > 0 ? result.substring(0, result.length() - 1): "";

        pw.println(interviewerId+"_"+candidateIdString);
        System.out.println("Data Successfully appended into file");
        pw.flush();

        pw.close();
        bw.close();
        fw.close();
    }
    public void updateStatus() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter candidate id you want to process: ");
        int num = sc.nextInt();

        String tempFile = "temp.csv";
        File newFile = new File(tempFile);
        File oldFile = new File("candidate.csv");

        FileWriter fw = new FileWriter(tempFile, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);

        Scanner x = new Scanner(new File("candidate.csv"));
        x.useDelimiter("[,\n]");

        while(x.hasNext()) {
            int id = Integer.parseInt(x.next());
            String name = x.next();
            int age = Integer.parseInt(x.next());
            boolean status = Boolean.parseBoolean(x.next());
            if(id == num) {
                pw.println(id+","+name+","+age+","+true);
                System.out.println("Update success");
            }else{
                pw.println(id+","+name+","+age+","+status);
            }
        }
        x.close();
        pw.flush();
        pw.close();
        bw.close();
        fw.close();
        if(oldFile.delete()){
            System.out.println("success");
        }else{
            System.out.println("fail");
        }

        File dump = new File("candidate.csv");
        if(newFile.renameTo(dump)){
            System.out.println("success");
        }else{
            System.out.println("fail");
        }

    }
    public void deleteBatch() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Batch Id that you wanna delete :");
        int num = scanner.nextInt();

       String path = "batch.csv";
        File oldFile = new File("batch.csv");
        File newFile = new File("tempBatch.csv");

        FileWriter fw = new FileWriter("tempBatch.csv",true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);

        Scanner x = new Scanner(new File(path));
        x.useDelimiter("[_\n]");

        while(x.hasNext()){
            int batchId = Integer.parseInt(x.next());
            String str = x.next();
            if(batchId != num) {
                pw.println(batchId+"_"+str);
            }
        }
        x.close();

        pw.flush();
        pw.close();
        bw.close();
        fw.close();
        if(oldFile.delete()){
            System.out.println("success");
        }else{
            System.out.println("fail");
        }

        File dump = new File("batch.csv");
        if(newFile.renameTo(dump)){
            System.out.println("success");
        }else{
            System.out.println("fail");
        }
    }
//    public void removeOneCandidate() throws IOException {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter candidate id you want to remove: ");
//        String candidateId = sc.nextLine();
//
//        String tempFile = "tempCandidate.csv";
//        File newFile = new File(tempFile);
//        File oldFile = new File("batch.csv");
//
//        FileWriter fw = new FileWriter(tempFile, true);
//        BufferedWriter bw = new BufferedWriter(fw);
//        PrintWriter pw = new PrintWriter(bw);
//
//        Scanner x = new Scanner(new File("batch.csv"));
//        x.useDelimiter("[_\n]");
//
//        String str = null;
//        while(x.hasNext()) {
//            int id = Integer.parseInt(x.next());
//            str = x.next();
//            str.replace(candidateId,"");
//        }
//
//        x.close();
//        pw.flush();
//        pw.close();
//        bw.close();
//        fw.close();
//        oldFile.delete();
//
//        File dump = new File("batch.csv");
//        newFile.renameTo(dump);
//    }

    public void processBatch() throws IOException {
        String path = "batch.csv";
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line = "";
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter batch id that you want to remove candidate: ");
        int rm = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Now enter candidate id to remove: ");
        int candidateId = scanner.nextInt();
        scanner.nextLine();

        List<String> list;
        String result="";
        while((line = br.readLine()) != null) {
            String[] values = line.split("_");
            int id = Integer.parseInt(values[0]);
            if(id == rm) {
                String str = values[1];
                String[] words = str.split(",");

                list = new ArrayList<>(Arrays.asList(words));

                list.remove(String.valueOf(candidateId));

                result = list.stream()
                        .map(n -> String.valueOf(n))
                        .collect(Collectors.joining(","));
                for (String e : list) {
                    System.out.println(e);
                }
                System.out.println("----------");
            }
        }
        br.close();

        String tempFile = "temp.csv";
        File newFile = new File(tempFile);

        FileWriter fw = new FileWriter(tempFile,true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);

        Scanner x = new Scanner(new File("batch.csv"));
        x.useDelimiter("[_\n]");

        while(x.hasNext()){
            int batchId = Integer.parseInt(x.next());
            String str = x.next();
            if(batchId == rm) {
                pw.println(batchId+ "_" + result);
            }else{
                pw.print(batchId +"_"+ str);
            }
        }
        x.close();
        pw.flush();
        pw.close();

        File oldFile = new File("batch.csv");
        if(oldFile.delete()){
            System.out.println("delete success");
        }else{
            System.out.println("delete fail");
        }

        File dump = new File("batch.csv");
        if(newFile.renameTo(dump)){
            System.out.println("rename success");
        }else{
            System.out.println("rename fail");
        }

    }


}
