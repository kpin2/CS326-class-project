import javafx.scene.image.Image;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

public class databaseOps {


    /**
     * Method to add a new user to the database if the username is not already taken. If the username is taken return false. Returns true only if the user is added to the database.
     *
     * @return boolean value indicating if the user was added to the database
     * @param username  username of the user to be added - String
     * @param password  password of the user to be added - String
     * @param avatarPic avatar of the user to be added - Image
     *
     *                  <p> This method makes a connection to the Mission_Math remote database via JDBC and queries the user_registration table to check if the username already exists. If the username does not exist, the user is added to the user_registration table. If the username already exists, the user is not added to the user_registration table and the method returns false.
     *                  <p> The method uses the following two SQL queries:
     *                  "SELECT * FROM [user_registration] WHERE username = '" + username + "'"
     *                  "INSERT INTO [user_registration] (username, password, avatar)" + "SELECT '" + username + "', '" + password + "' , '" + avatarPic + "'"
     * @author Kevin Pinto
     */
    public boolean addUser(String username, String password, Image avatarPic) throws SQLException {
        boolean b = false;

        try {
            Connection myConnection = DriverManager.getConnection("jdbc:sqlserver://missionmath.database.windows.net:1433;database=Mission_Math;", "MMadmin@missionmath", "faq9Adxxa7XDe7M");
            Statement myStatement = myConnection.createStatement();
            System.out.println("Connection successful");
            //all fields are necessary
            if (username == null || password == null || avatarPic == null) {
                System.out.println("Invalid input");
            } else {
                //check if username already exists, close connection and return if it does
                ResultSet myResult = myStatement.executeQuery("SELECT * FROM [user_registration] WHERE username = '" + username + "'");
                if (myResult.next()) {
                    System.out.println("User already exists");
                    myResult.close();
                    return b;
                }
                //add user to database
                if (!myStatement.execute("INSERT INTO [user_registration] (username, password, avatar)" + "SELECT '" + username + "', '" + password + "' , '" + avatarPic + "'") ) {
                    System.out.println("User added");
                    b = true;
                    return b;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return b;
    }

    public Image getAvatar(String username) {

        Image avatar = null;
        try {
            Connection myConnection = DriverManager.getConnection("jdbc:sqlserver://missionmath.database.windows.net:1433;database=Mission_Math;", "MMadmin@missionmath", "faq9Adxxa7XDe7M");

            Statement myStatement = myConnection.createStatement();
            System.out.println("Connection successful");
            ResultSet myResult = myStatement.executeQuery("SELECT * FROM dbo.user_registration WHERE username = '" + username + "'");

            if (myResult.next()) {
                avatar = new Image(myResult.getBlob("avatar").getBinaryStream());
                return avatar;
            }
            return avatar;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    /**
     * Method to retrieve all the avatar images from the database and store them in an ArrayList of Image objects.
     *
     * @return ArrayList of Image objects containing all avatars from the avatar_images table
     *
     * <p> This method makes a connection to the Mission_Math remote database via JDBC and queries the avatar_images table to retrieve all the avatar images. The avatar images are stored in an ArrayList of Image objects.
     * <p> The method uses the following SQL query:
     * <p>SELECT pic FROM avatar_images</p>
     * @author Kevin Pinto
     */
    protected ArrayList<Image> display_avatars() {

        ArrayList<Image> avatars = new ArrayList<Image>();

        try {
            Connection myConnection = DriverManager.getConnection("jdbc:sqlserver://missionmath.database.windows.net:1433;database=Mission_Math;", "MMadmin@missionmath", "faq9Adxxa7XDe7M");
            Statement myStatement = myConnection.createStatement();
            System.out.println("Connection successful");
            myStatement.execute("SELECT pic FROM avatar_images");
            try (ResultSet myResult = myStatement.getResultSet()) {
                while (myResult.next()) {
                    avatars.add(new Image(myResult.getBlob("pic").getBinaryStream()));
                    System.out.println("Avatar found and added");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return avatars;
    }

    /**
     * Method to verify login credentials against the Mission_Math remote database by making a connection via JDBC and querying the user_registration table for matching username and password.
     *
     * @param username username entered by user - String
     * @param password password entered by user - String
     * @return true if user exists in database, false if not
     * @author Kevin Pinto
     */
    public boolean verifyLogin(String username, String password) {
        boolean result = false;
        try {
            Connection myConnection = DriverManager.getConnection("jdbc:sqlserver://missionmath.database.windows.net:1433;database=Mission_Math;", "MMadmin@missionmath", "faq9Adxxa7XDe7M");
            Statement myStatement = myConnection.createStatement();
            System.out.println("Connection successful");
            ResultSet myResult = myStatement.executeQuery("SELECT * FROM dbo.user_registration WHERE username = '" + username + "' AND password = '" + password + "'");

            if (myResult.next()) {
                System.out.println("User found");
                System.out.println(myResult.getString("username") + " " + myResult.getString("password"));
                result = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
