package db.mysql;

import db.DBConnection;
import entity.Jobs;
import external.GitHubJobsAPI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MySQLConnection implements DBConnection {
    private Connection conn;

    public MySQLConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();
            conn = DriverManager.getConnection(MySQLDBUtil.URL);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Jobs> searchJobs(double lat, double lon, String term) {
        // TODO Auto-generated method stub
        GitHubJobsAPI gitHubJobsAPI = new GitHubJobsAPI();
        List<Jobs> jobs = gitHubJobsAPI.search(lat, lon);

        for(Jobs job : jobs) {
            saveJobs(job);
        }

        return jobs;
    }

    @Override
    public void saveJobs(Jobs job) {
        // TODO Auto-generated method stub
        if (conn == null) {
            System.err.println("DB connection failed");
            return;
        }

        try {
            String sql = "INSERT IGNORE INTO items (company, location, title) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, job.getCompany());
            ps.setString(2, job.getLocation());
            ps.setString(3, job.getTitle());

            ps.execute();



        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
