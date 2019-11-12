package charter.test;

import org.json.JSONArray;
import org.json.JSONException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestUtil {

    public static JSONArray parseJSONArrayFromFile(String filename) throws JSONException, IOException {
        String content = new String(Files.readAllBytes(Paths.get(filename)));
        return new JSONArray(content);
    }

}
