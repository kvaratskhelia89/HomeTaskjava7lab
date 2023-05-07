import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Main {
    public static void main(String[] args) {
        try {
            // Встановлення з'єднання з API
            URL url = new URL("https://api.chucknorris.io/jokes/random?category=animal");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            // Перевірка коду відповіді
            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("Помилка HTTP: код відповіді " + responseCode);
            }

            // Читання відповіді
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Парсинг JSON в об'єкт
            Gson gson = new Gson();
            JsonObject json = gson.fromJson(response.toString(), JsonObject.class);

            // Виведення інформації
            System.out.println("ID: " + json.get("id").getAsString());
            System.out.println("Титул: " + json.get("value").getAsString());
            System.out.println("Тіло: " + json.get("url").getAsString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
