package pl.sda.orange2.jdbc;

import pl.sda.orange2.jdbc.config.H2Config;

import java.sql.*;

public class TestConnection {
    public static void main(String[] args) {
        String query = """
                SELECT ID,NAME
                FROM TEST
                """;
        try {
            var h2Connection = DriverManager.getConnection(H2Config.DB_URL, H2Config.USER, H2Config.PASSWORD);
            System.out.println("Got connection: " + (h2Connection != null));

            Statement queryStatement = h2Connection.createStatement();
            ResultSet queryResult =  queryStatement.executeQuery(query);

            while (queryResult.next()){
                System.out.println("id: " + queryResult.getInt(1));
                System.out.println("name: " + queryResult.getString(2));
            }

        } catch (SQLException e) {
            System.out.println("No connection!" + e );
        }
    }
}
