import javafx.scene.image.Image;

import java.sql.*;

public class databaseOps {


    public void addUser(String username, String password, Image avatar) {
        try {
            Connection myConnection = DriverManager.getConnection("jdbc:sqlserver://missionmath.database.windows.net:1433;database=Mission_Math;", "MMadmin@missionmath", "faq9Adxxa7XDe7M");
            Statement myStatement = myConnection.createStatement();
            System.out.println("Connection successful");
            ResultSet myResult = myStatement.executeQuery("INSERT INTO dbo.user_registration (username, password, avatar) VALUES ('" + username + "', '" + password + "' , '" + avatar + "')");

            while (myResult.next()) {
                myResult.getBlob("avatar");
                System.out.println("User added");
                System.out.println(myResult.getString("username") + " " + myResult.getString("password") + " " + myResult.getString("avatar"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Image getAvatar(String username) throws SQLException {
        Connection myConnection = DriverManager.getConnection("jdbc:sqlserver://missionmath.database.windows.net:1433;database=Mission_Math;", "MMadmin@missionmath", "faq9Adxxa7XDe7M");
        Statement myStatement = myConnection.createStatement();
        System.out.println("Connection successful");
        /*ResultSet myResult = myStatement.executeQuery("SELECT * FROM dbo.user_registration WHERE username = '" + username + "'");*/

        ResultSet myResults = myStatement.executeQuery("SELECT * FROM avatar_images WHERE id = 1");

        if (myResults.next()) {
            return new Image(myResults.getBlob("pic").getBinaryStream());
        }
        return null;
    }

    public void getUserList() {
        try {
            Connection myConnection = DriverManager.getConnection("jdbc:sqlserver://missionmath.database.windows.net:1433;database=Mission_Math;", "MMadmin@missionmath", "faq9Adxxa7XDe7M");
            Statement myStatement = myConnection.createStatement();
            System.out.println("Connection successful");
            ResultSet myResult = myStatement.executeQuery("SELECT * FROM dbo.user_registration");
            while (myResult.next()) {
                System.out.println("User found");
                System.out.println(myResult.getString("username") + " " + myResult.getString("password") + " " + myResult.getBlob("avatar"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {


       /* try {

            Connection myConnection = DriverManager.getConnection("jdbc:sqlserver://missionmath.database.windows.net:1433;database=Mission_Math;", "MMadmin@missionmath", "faq9Adxxa7XDe7M");

            Statement myStatement = myConnection.createStatement();

            System.out.println("Connection successful");

            ResultSet myResult = myStatement.executeQuery("SELECT * FROM dbo.user_registration");

            while (myResult.next()) {
                System.out.println(myResult.getString("username") + " " + myResult.getString("password"));
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/

    }
}
