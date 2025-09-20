
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

// Servidor simple que empareja jugadores y maneja batallas
public class GameServer {
    // Lista de espera para jugadores que aún no tienen rival
    private static final List<ClientHandler> waiting = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        // Puerto donde escuchará el servidor
        int port = 5000;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Servidor iniciado en puerto " + port);

        // Ciclo infinito para aceptar conexiones
        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Nuevo cliente conectado: " + clientSocket.getRemoteSocketAddress());

            // Creamos un hilo por cliente
            ClientHandler handler = new ClientHandler(clientSocket);
            handler.start();

            // Emparejamos jugadores de dos en dos
            synchronized (waiting) {
                waiting.add(handler);
                if (waiting.size() >= 2) {
                    ClientHandler a = waiting.remove(0);
                    ClientHandler b = waiting.remove(0);
                    a.setOpponent(b);
                    b.setOpponent(a);

                    // Avisamos que empieza la batalla
                    a.sendMessage("MATCH_START");
                    b.sendMessage("MATCH_START");
                }
            }
        }
    }
}

