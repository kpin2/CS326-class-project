import java.sql.*;

public class databaseOps {
    public static void main(String[] args) {


        try {

            Connection myConnection =  DriverManager.getConnection("jdbc:sqlserver://missionmath.database.windows.net:1433;database=Mission_Math;", "MMadmin@missionmath", "faq9Adxxa7XDe7M");

            Statement myStatement = myConnection.createStatement();

            System.out.println("Connection successful");

            ResultSet myResult = myStatement.executeQuery("SELECT * FROM dbo.user_registration");

            while (myResult.next()) {
                System.out.println(myResult.getString("username") + " " + myResult.getString("password"));
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
