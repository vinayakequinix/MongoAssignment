import org.json.JSONArray;
import org.json.JSONObject;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

    /**
     A class mimicking a MongoDB database, with methods for reading and writing JSON data to a file,
     as well as adding, updating, and deleting documents from the database (JSON file).
     */
public class Mongo {
    private static final String FILE_NAME = "posts.json";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

    /**
            * The main method for the Mongo class, which demonstrates basic CRUD operations on the database.
            * Reads in the JSON file into a JSON array, adds a new post, updates the title of the second post,
            * removes the first post, writes the updated JSON array back to the file, and then deletes a post
            * with a given ID.
            */
    public static void main(String[] args) {
        // Read the JSON file into a JSON array
        JSONArray posts = readJsonFile(FILE_NAME);

        // Add a new post
        JSONObject newPost = new JSONObject();
        newPost.put("id", 3);
        newPost.put("title", "Post Title 3");
        newPost.put("body", "Body of post 3.");
        newPost.put("category", "Sports");
        newPost.put("likes", 2);

        // Create a JSON array to hold the tags
        JSONArray tagsArray = new JSONArray();
        tagsArray.put("sports");
        tagsArray.put("events");
        newPost.put("tags", tagsArray);

        newPost.put("date", LocalDateTime.now().format(DATE_FORMATTER));

        posts.put(newPost);

        // Update the title of the second post
        JSONObject post2 = posts.getJSONObject(1);
        post2.put("title", "Updated Post Title 2");

        // Remove the first post
        posts.remove(0);

        // Write the updated JSON array to the file
        writeJsonFile(FILE_NAME, posts);
        deleteById(88);
    }
    /**
     * Reads in the contents of a JSON file and returns them as a JSON array.
     * @param fileName The name of the JSON file to be read
     * @return The JSON array containing the contents of the file
     */
    public static JSONArray readJsonFile(String fileName) {
        try {
            String jsonString = new String(Files.readAllBytes(Paths.get(fileName)));
            return new JSONArray(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
            return new JSONArray();
        }
    }
    /**
     * Writes a given JSON array to a file in JSON format.
     * @param fileName The name of the file to write the JSON array to
     * @param jsonArray The JSON array to write to the file
     */
    public static void writeJsonFile(String fileName, JSONArray jsonArray) {
        try (FileWriter file = new FileWriter(new File(fileName))) {
            file.write(jsonArray.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Prints the document with a given ID to the console.
     * @param iid The ID of the document to print
     */
    public static void PrintByID(int iid)
    {
        int documentIdToFind = iid;
        try {
            // Read the JSON file into a JSON array
            String jsonString = new String(Files.readAllBytes(Paths.get(FILE_NAME)));
            JSONArray jsonArray = new JSONArray(jsonString);

            // Loop through the JSON array and find the object with the given ID
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                if (id == documentIdToFind) {
                    // Print the object with the given ID
                    System.out.println(jsonObject.toString());
                    return;
                }
            }

            // If the object with the given ID is not found, print an error message
            System.out.println("Document with ID " + documentIdToFind + " not found.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**

     Inserts a new document with the specified data into a JSON file of blog posts.
     The data includes an id, title, body, category, likes, and date.
     The method reads the existing JSON file, creates a new JSONObject with the specified data,
     appends the new JSONObject to the existing JSONArray, and writes the updated JSONArray back to the file.
     @param id The id of the new document to be inserted.
     @param title The title of the new document to be inserted.
     @param body The body text of the new document to be inserted.
     @param category The category of the new document to be inserted.
     @param likes The number of likes of the new document to be inserted.
     @param date The date of the new document to be inserted.
     */
    public static void InsertOne(int id, String title, String body, String category, int likes, String date)
    {
        JSONArray jsonArray = readJsonFile("posts.json");

        // Create a new JSONObject with the data for the new document
        JSONObject newDocument = new JSONObject();
        newDocument.put("id", id);
        newDocument.put("title", title);
        newDocument.put("body", body);
        newDocument.put("category", category);
        newDocument.put("likes", likes);
        JSONArray tagsArray = new JSONArray();
        tagsArray.put("sports");
        newDocument.put("tags", tagsArray);
        newDocument.put("date", date);

        // Append the new JSONObject to the JSONArray
        jsonArray.put(newDocument);

        // Write the updated JSONArray back to the file
        writeJsonFile("posts.json", jsonArray);

        System.out.println("New document inserted successfully");
    }


    /**

     Returns the number of documents in the "posts.json" file.
     @return The number of documents in the file.
     */
    public static int getLength()
    {
        JSONArray jsonArray = readJsonFile("posts.json");

        // Count the number of documents
        int documentCount = jsonArray.length();
        return documentCount;

    }
    /**

     Deletes a JSON object from a file by its ID.
     @param id The ID of the document to be deleted
     */
    public static void deleteById(int id) {
        JSONArray jsonArray = readJsonFile("posts.json");

        // Loop through the JSON array and find the object with the given ID
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            int documentId = jsonObject.getInt("id");
            if (documentId == id) {
                // Remove the object with the given ID
                jsonArray.remove(i);

                // Write the updated JSONArray back to the file
                writeJsonFile("posts.json", jsonArray);

                System.out.println("Document with ID " + id + " deleted successfully.");
                return;
            }
        }

        // If the object with the given ID is not found, print an error message
        System.out.println("Document with ID " + id + " not found.");
    }

}
