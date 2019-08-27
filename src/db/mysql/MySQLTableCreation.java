package db.mysql;

import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Connection;

public class MySQLTableCreation {
    // Run this as Java application to reset db schema.
    public static void main(String[] args) {
        try {
            // Step 1 Connect to MySQL.
            System.out.println("Connecting to " + MySQLDBUtil.URL);
            // create jdbc object
            Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
            Connection conn = DriverManager.getConnection(MySQLDBUtil.URL);

            if (conn == null) {
                return;
            }

            // Step 2 Drop tables in case they exist.
            Statement statement = conn.createStatement();

            String sql = "DROP TABLE IF EXISTS items";
            statement.executeUpdate(sql);

            // Step 3 Create new tables
            sql = "CREATE TABLE items ("
                    + "item_id int NOT NULL AUTO_INCREMENT,"
//                    + "item_id VARCHAR(255) NOT NULL,"
//                    + "type VARCHAR(255),"
//                    + "url FLOAT,"
//                    + "create_at VARCHAR(255),"
                    + "company VARCHAR(255),"
//                    + "company_url VARCHAR(255),"
                    + "location VARCHAR(255),"
                    + "title VARCHAR(255),"
//                    + "how_to_apply VARCHAR(255),"
//                    + "company_logo VARCHAR(255),"
//                    + "description VARCHAR(15000)"
                    + "PRIMARY KEY (item_id)"
                    + ")";
            statement.executeUpdate(sql);


            // Step 4: insert fake test data
            sql = "INSERT INTO items (company, location, title) VALUES('Apple', 'San Jose', 'Software Engineer')";
            statement.executeUpdate(sql);


            conn.close();
            System.out.println("Import done successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

