import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JsonFileCreator {
    public static void main(String[] args) {
        // Create a JSON array to hold the posts
        JSONArray postsArray = new JSONArray();

        // Create the first post object and add it to the array
        JSONObject post1 = new JSONObject();
        post1.put("id", 1);
        post1.put("title", "Post Title 1");
        post1.put("body", "Body of post 1.");
        post1.put("category", "News");
        post1.put("likes", 1);

        // Create a JSON array to hold the tags
        JSONArray tagsArray1 = new JSONArray();
        tagsArray1.put("news");
        tagsArray1.put("events");
        post1.put("tags", tagsArray1);

        post1.put("date", "2023-02-21T08:24:13.167Z");

        postsArray.put(post1);

        // Create the second post object and add it to the array
        JSONObject post2 = new JSONObject();
        post2.put("id", 2);
        post2.put("title", "Post Title 2");
        post2.put("body", "Body of post 2.");
        post2.put("category", "Technology");
        post2.put("likes", 3);

        // Create a JSON array to hold the tags
        JSONArray tagsArray2 = new JSONArray();
        tagsArray2.put("technology");
        tagsArray2.put("gadgets");
        post2.put("tags", tagsArray2);

        post2.put("date", "2023-02-22T10:15:23.102Z");

        postsArray.put(post2);

        // Write the JSON array to a file
        try (FileWriter file = new FileWriter("posts.json")) {
            file.write(postsArray.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
