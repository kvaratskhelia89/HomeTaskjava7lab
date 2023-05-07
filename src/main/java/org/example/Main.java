import java.io.BufferedReader; //класс для чтения символьных потоков ввода, который может быть связан с любым объектом Reader.
import java.io.IOException; //класс, который описывает исключение, возникающее при ошибке ввода/вывода.
import java.io.InputStreamReader; // класс, который связывает поток ввода байтов с символьным вводом.
import java.net.HttpURLConnection; //класс, который предоставляет методы для работы с HTTP-соединениями.
import java.net.URL; //класс, который представляет собой универсальный идентификатор ресурса
import com.google.gson.Gson; //класс для преобразования JSON-строк в объекты Java и обратно.
import com.google.gson.JsonObject; //класс, который представляет собой элемент данных JSON в виде пар ключ-значение.

public class Main {
    public static void main(String[] args) throws IOException {
        // генерируем случайный id в диапазоне от 1 до 100
        int id = (int) (Math.random() * 100) + 1;

        // создаем URL для GET-запроса
        String url = "https://jsonplaceholder.typicode.com/posts/" + id;

        // создаем объект HttpURLConnection и настраиваем его
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestMethod("GET");

        // читаем ответ от сервера
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // парсим JSON в объект класса Post с помощью библиотеки Gson
        Gson gson = new Gson();
        Post post = gson.fromJson(response.toString(), Post.class);

        // выводим номер айди, титул и тело в консоль
        System.out.println("ID: " + post.getId());
        System.out.println("Title: " + post.getTitle());
        System.out.println("Body: " + post.getBody());
    }
}

// класс для парсинга JSON
class Post {
    private int id;
    private String title;
    private String body;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}

