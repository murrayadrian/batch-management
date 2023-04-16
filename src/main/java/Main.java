import jdbc.DBManagement;
import filehandling.Batch;
import filehandling.Candidate;
import filehandling.Management;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {

        Management management = new Management();
        DBManagement dbManagement = new DBManagement();

        System.out.println("Choose database to work with:");
        System.out.println("1: CSV File");
        System.out.println("2: SQL Server");

        Scanner scanner = new Scanner(System.in);
        int choice;
        int database = scanner.nextInt();
        scanner.nextLine();

        do {
            System.out.println("> 1. Enter interviewer");
            System.out.println("> 2. Enter candidate");
            System.out.println("> 3. Enter batch");
            System.out.println("> 4. Update candidate status");
            System.out.println("> 5. Print all information");
            System.out.println("> 6. Delete one batch");
            System.out.println("> 7. Remove 1 candidate out of the batch");
            System.out.println("> 8. Remove list candidates out of batch");
            System.out.println("> 9. Display a list of candidates ordered by candidate's name");
            choice = scanner.nextInt();

            if (database == 1) {
                switch (choice) {
                    case 1:
                        management.inputInterviewer();
                        break;
                    case 2:
                        management.inputCandidate();
                        break;
                    case 3:
                        management.inputBatch();
                        break;
                    case 4:
                        management.updateStatus();
                        break;
                    case 5:
                        management.printAll();
                        break;
                    case 6:
                        management.deleteBatch();
                        break;
                    case 7:
                        management.processBatch();
                        break;
                    case 8:
                        management.processBatch();
                        break;
                    case 9:
                        management.printCandidate();
                        break;
                }
            } else{
                switch (choice) {
                    case 1:
                        dbManagement.inputInterviewer();
                        break;
                    case 2:
                        dbManagement.inputCandidate();
                        break;
                    case 3:
                        dbManagement.inputBatch();
                        break;
                    case 4:
                        List<Batch> batches = dbManagement.fetchAllBatch();
                        for (Batch batch : batches) {
                            System.out.println(batch);
                        }
                        System.out.println("Which candidate id that you want to update: ");
                        int candidateID = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Did he pass or fail the interview :");
                        String result = scanner.nextLine();

                        dbManagement.updateCandidateStatus(candidateID,result);
                        System.out.println("----------------");
                        break;

                    case 5:
                        dbManagement.printAllInformation();
                        break;
                    case 6:
                        dbManagement.deleteOneBatch();
                        break;

                    case 7:
                        System.out.println("every interviewer will interview a list of candidates :");
                        System.out.println(dbManagement.printCandidateQueue().toString());
                        dbManagement.removeOneCandidateFromBatch();
                        break;

                    case 8:
                        System.out.println(dbManagement.removeListOfCandidates().toString());
                        break;

                    case 9:
                        List<Candidate> candidateList = dbManagement.fetchCandidate();
                        for(Candidate candidate : candidateList){
                            System.out.println(candidate);
                        }
                        break;
                }
            }
        }while (choice != 0) ;
    }
}
