package RESTService.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequestUtils {
    /**
     * GET запрос по URL
     * @param stringURL - URL в виде строки
     * @return возращает Response в виде String
     * @throws IOException
     */
    public static String executeGetRequest(String stringURL) throws IOException{
        URL url = new URL(stringURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            StringBuilder response  = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null){
                response.append(line);
            }
            return response.toString();
        }
    }
}
