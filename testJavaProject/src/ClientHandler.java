
import java.io.*;
import java.net.Socket;

// Maneja la comunicación con un cliente (un jugador)
public class ClientHandler extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private ClientHandler opponent; // Oponente en la batalla
    private String playerName;
    private int hp = 100; // Vida inicial del jugador

    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
    }

    // Asigna un oponente
    public void setOpponent(ClientHandler opp) {
        this.opponent = opp;
    }

    // Enviar mensaje al cliente
    public void sendMessage(String msg) {
        out.println(msg);
    }

    @Override
    public void run() {
        try {
            String line;
            // Leer mensajes que envía el cliente
            while ((line = in.readLine()) != null) {
                System.out.println("Recibido: " + line);

                if (line.startsWith("NAME:")) {
                    // El cliente envía su nombre
                    playerName = line.substring(5);
                    sendMessage("WELCOME " + playerName);

                } else if (line.equals("ATTACK") && opponent != null) {
                    // Atacar al oponente
                    synchronized (opponent) {
                        opponent.hp -= 20; // daño fijo
                        opponent.sendMessage("DAMAGE:20");

                        if (opponent.hp <= 0) {
                            sendMessage("YOU_WIN");
                            opponent.sendMessage("YOU_LOSE");
                        }
                    }

                } else if (line.equals("STATUS")) {
                    sendMessage("HP:" + hp);

                } else {
                    sendMessage("UNKNOWN_CMD");
                }
            }
        } catch (IOException e) {
            System.out.println("Error en handler: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException ignored) {}
        }
    }
}
