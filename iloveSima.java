package proj;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class iloveSima {
    public static void main(String[] args) {
        String myDBLink = "jdbc:mysql://localhost:3306/mydb";
        String user = "root";
        String pass = "password";
        Scanner scan = new Scanner(System.in);
        String input;

        System.out.println("==================================================================");
        System.out.println("Hey Sima!");
        System.out.println("\tWelcome to your personal complaining machine :)");
        System.out.println("\tWhere you can easily and freely complain about\n\thow bad you students are doing, even if they are pretty good...");
        System.out.println("==================================================================\n\n");

        try {
            // Connection to database
            Connection conn = DriverManager.getConnection(myDBLink, user, pass);
            Statement stmt = conn.createStatement();
            ResultSet rs = null;

            do {
                // Input from user
                System.out.print("\nPress 1 to return the school average:\n");
                System.out.print("\nPress 2 to return the boys average:\n");
                System.out.print("\nPress 3 to return the girls average:\n");
                System.out.print("\nPress 4 to return the average height of the students above 2 meters that have a purple car:\n");
                System.out.print("\nPress 5 to return the IDs of all the friends of a student and all their friends:\n");
                System.out.print("\nPress 6 to return the percentage of kids in different social statuses:\n");
                System.out.print("\nPress 7 to return grade average of a student:\n");
                System.out.println("Press 8 to exit the program:");
                System.out.print("\n\n======> ");
        
                input = scan.nextLine();

                switch (input) {
                    case ("1"):
                        rs = stmt.executeQuery("SELECT AVG(grade_avg) FROM highschool");
                        if (rs.next()) {
                            double averageGrade = rs.getDouble(1);
                            System.out.println("\nThe average grade is: " + averageGrade);
                        }

                        rs.close();

                        break;
                    
                    case ("2"):
                        rs = stmt.executeQuery("SELECT AVG(grade_avg) FROM highschool WHERE gender = \"Male\"");
                        if (rs.next()) {
                            double averageGrade = rs.getDouble(1);
                            System.out.println("\nThe average grade of THE BOYS is: " + averageGrade);
                        }

                        rs.close();

                        break;

                    case ("3"):
                        rs = stmt.executeQuery("SELECT AVG(grade_avg) FROM highschool WHERE gender = \"Female\"");
                        if (rs.next()) {
                            double averageGrade = rs.getDouble(1);
                            System.out.println("\nThe average grade of the washing machines is: " + averageGrade);
                        }

                        rs.close();

                        break;

                    case ("4"):
                        rs = stmt.executeQuery("SELECT AVG(cm_height) FROM highschool WHERE cm_height >= 200 AND car_color = \"Purple\"");
                        if (rs.next()) {
                            double averageGrade = rs.getDouble(1);
                            System.out.println("\nThe average hieght of the tall douchebags is: " + averageGrade);
                        }
    
                        rs.close();
    
                        break;
                    
                    case ("5"):
                        ArrayList<Integer> friendIDs = new ArrayList<Integer>();
                        ArrayList<Integer> newFriends = new ArrayList<Integer>();
                        System.out.println("Enter ID:");
                        int inputId = scan.nextInt();
                        scan.nextLine();
                        // It's 5am and im doing this because i love u talbar
                        rs = stmt.executeQuery("SELECT friend_id, other_friend_id FROM highschool_friendships WHERE friend_id = " + inputId + " OR other_friend_id = " + inputId);
                        while (rs.next()) {
                            int friend_id = rs.getInt("friend_id");
                            int other_friend_id = rs.getInt("other_friend_id");
                            if (friend_id != inputId) {
                                friendIDs.add(friend_id);
                            } else {
                                friendIDs.add(other_friend_id);
                            }
                        }

                        for (Integer ID : friendIDs) {
                            rs = stmt.executeQuery("SELECT friend_id, other_friend_id FROM highschool_friendships WHERE friend_id = " + ID + " OR other_friend_id = " + ID);
                            while (rs.next()) {
                                int friend_id = rs.getInt("friend_id");
                                int other_friend_id = rs.getInt("other_friend_id");
                                // Add the friends to the newFriends list if they are not already in the friendsIDs list
                                if (!friendIDs.contains(friend_id) && friend_id != ID && friend_id != inputId) {
                                    newFriends.add(friend_id);
                                }
                                if (!friendIDs.contains(other_friend_id) && other_friend_id != ID && other_friend_id != inputId) {
                                    newFriends.add(other_friend_id);
                                }
                            }
                        }

                        friendIDs.addAll(newFriends);
                        System.out.println("All friends ids: " + friendIDs);
    
                        rs.close();

                        break;

                    case ("6"):
                        rs = stmt.executeQuery("SELECT AVG(cm_height) FROM highschool WHERE cm_height >= 200 AND car_color = \"Purple\"");
                        if (rs.next()) {
                            double averageGrade = rs.getDouble(1);
                            System.out.println("\nThe average grade is: " + averageGrade);
                        }
    
                        rs.close();
    
                        break;

                    case ("7"):
                        inputId = scan.nextInt();
                        scan.nextLine();
                        
                        rs = stmt.executeQuery("SELECT grade_avg FROM ImaSima WHERE identification_card = " + inputId);
                        if (rs.next()) {
                            double averageGrade = rs.getDouble(1);
                            System.out.println("\nThe average grade is: " + averageGrade);
                        }
    
                        rs.close();

                        break;
                    default:
                    System.out.println("\nInvalid Input\n");
                        
                        break;
                }

            } while (input != "8");
        stmt.close();
        conn.close();
        } catch (SQLException e) {
            System.out.println("====================================");
            System.out.println("         An Error Accured:");
            e.printStackTrace();
            System.out.println("====================================\n");
        }
        scan.close();
    }    
}
