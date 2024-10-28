package utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketManager {
    private static SocketManager instance;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private ObjectOutputStream os;
    private ObjectInputStream is;

    private SocketManager() {
        try {
            this.socket = new Socket("localhost", 8080);
            this.out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            this.os= new ObjectOutputStream(socket.getOutputStream());
            this.is= new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized SocketManager getInstance() {
        if (instance == null) {
            instance = new SocketManager();
        }
        return instance;
    }

    public PrintWriter getPrintWriter() {
        return out;
    }

    public ObjectOutputStream getObjectOutputStream() {
        return os;
    }
    
    public ObjectInputStream getObjectInputStream() {
        return is;
    }
    
    public BufferedReader getBufferedReader() {
        return in;
    }

    public void close() {
        try {
            if (socket != null) {
                socket.close();
            }
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}