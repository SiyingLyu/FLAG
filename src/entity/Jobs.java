package entity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Jobs {
    private String company;
    private String location;
    private String title;

    private Jobs(JobsBuilder builder) { //注意这里private constructor
        this.company = builder.company;
        this.location = builder.location;
        this.title = builder.title;
    }
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public JSONObject toJSONObject() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("company", company);
            obj.put("location", location);
            obj.put("title", title);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }


    // builder pattern
    public static class JobsBuilder {
        private String company;
        private String location;
        private String title;


        public Jobs build() { //对外界公开, 创建内部private constructor的对象
            return new Jobs(this);
        }
        // why use "this"?
        // 原本set是void, 现在把返回类型改成ItemBuilder自己, 这样就可以用链接符的方式去call, call 完一个再call一个
        public JobsBuilder setCompany(String company) {
            this.company = company;
            return this;
        }
        public JobsBuilder setLocation(String location) {
            this.location = location;
            return this;
        }
        public JobsBuilder setTitle(String title) {
            this.title = title;
            return this;
        }


    }
}
