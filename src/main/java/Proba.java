import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Proba {

    public static String httpRead() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://worldtimeapi.org/api/timezone/Europe/Bucharest.json"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
        }

        public static void soseta() throws IOException {
            ServerSocket server = new ServerSocket(8088);
            while (true) {
                try {
                    Socket socket = server.accept();
                    String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + httpRead();
                    socket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
                    socket.close();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }

        public static void main (String[]args) throws IOException {
            soseta();
        }
}



