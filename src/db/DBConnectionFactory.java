package db;

import db.mysql.MySQLConnection;

public class DBConnectionFactory {
    // This should change based on the pipeline.
    private static final String DEFAULT_DB = "mysql";
    //private static final String DEFAULT_DB = "mongodb";

    public static DBConnection getConnection(String db) {
        //可以选不一样的数据库
        switch(db) {
            case "mysql":
                return new MySQLConnection();
            default:
                throw new IllegalArgumentException("Invalid db" + db);
        }
    }
    public static DBConnection getConnection() {
        return getConnection(DEFAULT_DB);
    }
}
