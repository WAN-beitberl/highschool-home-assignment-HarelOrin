package proj.CSVtoMySQL;

import java.sql.*;

public class HsFriendshipsCSV extends HSfile {
    // Constructor with personalized SQL code and file path
    HsFriendshipsCSV() {
        super.sql = "INSERT INTO highschool_friendships (friend_id, other_friend_id) VALUES (?, ?);";

        super.fileLink = "C:\\Users\\User\\Documents\\Programming\\WanTec\\SQL\\highschool_friendships.csv";
    }

    // Insertion of values into a commit
    @Override
    public PreparedStatement InsertValues(String[] data, PreparedStatement pst) {
        // If some values null or the same skip
        if (data.length < 3) {
            return pst;
        }
        if (data[1] == "" || data[2] == "" || data[1] == data[2]) {
            return pst;
        }

        // Read input to variables
        int friend_id = Integer.parseInt(data[1]);
        int other_friend_id = Integer.parseInt(data[2]);

        // Insert values to table
        try {
            pst.setInt(1, friend_id);
            pst.setInt(2, other_friend_id);
            pst.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("====================================");
            System.out.println("         An Error Accured:");
            e.printStackTrace();
            System.out.println("====================================\n");
        }
        return pst;
    }
}