
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

// Cliente que se conecta al servidor y envía comandos
public class GameClient {
    public static void main(String[] args) throws IOException {
        String host = "10.10.13.116"; // Cambia si el servidor está en otra máquina
        int port = 5000;

        Socket socket = new Socket(host, port);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        // Hilo para leer mensajes del servidor
        Thread reader = new Thread(() -> {
            try {
                String s;
                while ((s = in.readLine()) != null) {
                    System.out.println("[SERVER] " + s);
                }
            } catch (IOException e) {
                System.out.println("Conexión cerrada.");
            }
        });
        reader.start();

        Scanner sc = new Scanner(System.in);
        System.out.print("Tu nombre: ");
        String name = sc.nextLine();
        out.println("NAME:" + name); // enviar nombre al servidor

        // Bucle principal: enviar comandos
        while (true) {
            System.out.print("Comando (ATTACK/STATUS/EXIT): ");
            String cmd = sc.nextLine().trim();
            if (cmd.equalsIgnoreCase("EXIT")) {
                break;
            }
            out.println(cmd);
        }

        socket.close();
        sc.close();
    }
}

