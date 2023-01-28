package proj.CSVtoMySQL;
import java.io.*;
import java.sql.*;

public class Main {
    // Method to read from csv file into database
    public static void CopyInfo(String myDBLink, String user, String pass, HSfile file) {
        try {
            // Connection to the database
            Connection conn = DriverManager.getConnection(myDBLink, user, pass);

            // Buffer for each highschool csv table row
            PreparedStatement pst = conn.prepareStatement(file.sql);
            BufferedReader lineBuff = new BufferedReader(new FileReader(file.fileLink));
            String lineTxt = null;

            // Init reading highschool csv
            lineBuff.readLine();
            while ((lineTxt=lineBuff.readLine())!=null) {
                String[] data = lineTxt.split(",");

                pst = file.InsertValues(data, pst);
            }
        
            lineBuff.close();
            pst.close();
        } catch (Exception e) {
            System.out.println("====================================");
            System.out.println("         An Error Accured:");
            e.printStackTrace();
            System.out.println("====================================\n");
        }
    }
    
    public static void main(String[] args) {
        // Create link variables to put into the connection code
        String myDBLink = "jdbc:mysql://localhost:3306/mydb";
        String user = "root";
        String pass = "password";

        // Different csv Files
        HighschoolCSV hs = new HighschoolCSV();
        HsFriendshipsCSV hsFriend = new HsFriendshipsCSV();

        // Upload each
        CopyInfo(myDBLink, user, pass, hs);
        CopyInfo(myDBLink, user, pass, hsFriend);
    }
}
