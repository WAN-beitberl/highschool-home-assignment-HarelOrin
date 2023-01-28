package proj.CSVtoMySQL;
import java.sql.*;

public class HighschoolCSV extends HSfile {
    // Constructor with personalized SQL code and file path
    HighschoolCSV() {
        super.sql = "INSERT INTO highschool (first_name, last_name, email, gender, ip_address, cm_height, age, has_car, car_color, grade, grade_avg, identification_card) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        super.fileLink = "C:\\Users\\User\\Documents\\Programming\\WanTec\\SQL\\highschool.csv";
    }

    // Insertion of values into a commit        
    @Override
    public PreparedStatement InsertValues(String[] data, PreparedStatement pst) {
        // Read input to variables
        String first_name = data[1];
        String last_name = data[2];
        String email = data[3];
        String gender = data[4];
        String ip_address = data[5];
        
        // Change appropriate input into right datatype
        int cm_height = Integer.parseInt(data[6]);
        int age = Integer.parseInt(data[7]);
        boolean has_car = Boolean.parseBoolean(data[8]);
        String car_color = data[9];
        if (car_color == "") car_color = null;
        if(has_car == false) car_color = null;
        int grade = Integer.parseInt(data[10]);
        double grade_avg = Double.parseDouble(data[11]);
        int identification_card = Integer.parseInt(data[12]);

        // Insert to table read values
        try {
            pst.setString(1, first_name);
            pst.setString(2, last_name);
            pst.setString(3, email);
            pst.setString(4, gender);
            pst.setString(5, ip_address);
            pst.setInt(6, cm_height);
            pst.setInt(7, age);
            pst.setBoolean(8, has_car);
            pst.setString(9, car_color);
            pst.setInt(10, grade);
            pst.setDouble(11, grade_avg);
            pst.setInt(12, identification_card);
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