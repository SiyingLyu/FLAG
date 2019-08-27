package external;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import entity.Jobs;
import entity.Jobs.JobsBuilder;


public class GitHubJobsAPI {
    private static final String URL = "https://jobs.github.com/positions.json";
//    private static final String DEFAULT_KEYWORD = ""; // no restriction
//    private static final String API_KEY = "xfpENcPHJH79w70PGVxq8KLpUKBNwwGQ";

    public List<Jobs> search(double lat, double lon) {
        List<Jobs> jobs = new ArrayList<>();

        String query = String.format("lat=%s&long=%s", lat, lon);
        String url = URL + "?" + query;

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            System.out.println("Sending request to url: " + url);
            System.out.println("Response code: " + responseCode);

            if (responseCode != 200) {
                return new ArrayList<>();
            }

            // 读 response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            System.out.print(response.toString());
            // 把内容再转成JSONOArray - 我们拿到的数据就是JSONArray
            JSONArray obj = new JSONArray(response.toString());
            System.out.println("HERE");
            return getJobsList(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Jobs> getJobsList(JSONArray jobsjsons) throws JSONException {
        List<Jobs> jobs = new ArrayList<>();
        for (int i = 0; i < jobsjsons.length(); i++) {
            JSONObject jobsjson = jobsjsons.getJSONObject(i);

            JobsBuilder builder = new JobsBuilder();

//            if(!jobsjson.isNull("company")) {
//                builder.setCompany(jobsjson.getString("company"));
//            }
//
//            if (!jobsjson.isNull("title")) {
//                builder.setTitle(jobsjson.getString("title"));
//            }
//
//            if (!jobsjson.isNull("location")) {
//                builder.setLocation(jobsjson.getString("location"));
//            }

            builder.setCompany(getCompany(jobsjson))
                    .setTitle(getTitle(jobsjson))
                    .setLocation(getLocation(jobsjson));

            jobs.add(builder.build());
        }

        return jobs;
    }


    // helper method
    private String getCompany(JSONObject jobsjson) throws JSONException {
        StringBuilder builder = new StringBuilder();
        if (!jobsjson.isNull("company")) {
//            builder.append("company");
            builder.append(jobsjson.getString("company"));
            return builder.toString();
        }
        return "";
    }

    private String getTitle(JSONObject jobsjson) throws JSONException {
        StringBuilder builder = new StringBuilder();
        if (!jobsjson.isNull("title")) {
//            builder.append("title");
            builder.append(jobsjson.getString("title"));
            return builder.toString();
        }
        return "";
    }

    private String getLocation(JSONObject jobsjson) throws JSONException {
        StringBuilder builder = new StringBuilder();
        if (!jobsjson.isNull("location")) {
//            builder.append("location");
            builder.append(jobsjson.getString("location"));
            return builder.toString();
        }
        return "";
    }

    private void queryAPI(double lat, double lon) {
        List<Jobs> jobs = search(lat, lon);

//        for (Jobs job : jobs) {
//            System.out.println(job.toJSONObject());
//        }
    }

    public static void main(String[] args) {
        GitHubJobsAPI tmApi = new GitHubJobsAPI();
        tmApi.queryAPI(37.3229978, -122.0321823);
    }
}
