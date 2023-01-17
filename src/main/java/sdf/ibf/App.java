package sdf.ibf;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public final class App {

    public static void main(String[] args) throws NumberFormatException, UnknownHostException, IOException {
        Socket socket = new Socket("localhost", Integer.parseInt(args[0]));
        try (OutputStream os = socket.getOutputStream()) {
            BufferedOutputStream bos = new BufferedOutputStream(os);
            DataOutputStream dos = new DataOutputStream(bos);

            Console cons = System.console();
            String readInput = "";
            String response = "";

            while (!readInput.equalsIgnoreCase("close")) {
                readInput = cons.readLine("Enter a command: ");
                dos.writeUTF(readInput);
                dos.flush();
                InputStream is = socket.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                DataInputStream dis = new DataInputStream(bis);
                response = dis.readUTF();
                System.out
                        .println(response.substring(0, 1).toUpperCase()
                                + response.substring(1, response.length()) + " cookie received.");
            }
        }
    }
}
