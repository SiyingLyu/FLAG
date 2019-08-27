package db;

import java.util.List;
import java.util.Set;

import entity.Jobs;

public interface DBConnection {
    /**
     * Close the connection.
     */
    public void close();

    /**
     * Search items near a geolocation and a term (optional).
     *
     * @param lat
     * @param lon
     * @param term
     *            (Nullable)
     * @return list of items
     */
    public List<Jobs> searchJobs(double lat, double lon, String term);

    /**
     * Save item into db.
     *
     * @param item
     */
    public void saveJobs(Jobs item);


}