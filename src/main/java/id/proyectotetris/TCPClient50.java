package id.proyectotetris;

import id.proyectotetris.sockets.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class TCPClient50 {

    private Object servermsj; 
//    private String servermsj; 
    public String SERVERIP;
    public static final int SERVERPORT = 4444;
    private OnMessageReceived mMessageListener = null;
    private boolean mRun = false;

    ObjectOutputStream out; 
//    PrintWriter out; 
    ObjectInputStream in;   
//    BufferedReader in; 

    public TCPClient50(String ip, OnMessageReceived listener) {
        SERVERIP = ip;
        mMessageListener = listener;
    }

    public void sendMessage(Object message) { // public void sendMessage(String message)
        try {
            if (out != null) {
                out.reset(); 
                out.writeObject(message); 
//                out.println(message); 
                out.flush();
            }
        } catch (IOException e) {
            System.out.println("Error enviando objeto: " + e);
        }
    }

    public void stopClient() {
        mRun = false;
    }

    public void run() {
        mRun = true;
        try {
            InetAddress serverAddr = InetAddress.getByName(SERVERIP);
            System.out.println("TCP Client" + "C: Conectando...");
            Socket socket = new Socket(serverAddr, SERVERPORT);
            try {
                out = new ObjectOutputStream(socket.getOutputStream()); 

                in = new ObjectInputStream(socket.getInputStream()); 
                
                System.out.println("TCP Client" + "C: Sent.");
                System.out.println("TCP Client" + "C: Done.");

                while (mRun) {
                    servermsj = in.readObject(); 
//                    servermsj = in.readLine();

                    if (servermsj != null && mMessageListener != null) {
                        mMessageListener.messageReceived(servermsj);
                    }
                    servermsj = null;
                }
            } catch (Exception e) {
                System.out.println("TCP" + "S: Error" + e);
            } finally {
                socket.close();
            }
        } catch (Exception e) {
            System.out.println("TCP" + "C: Error" + e);
        }
    }

    public interface OnMessageReceived {
        public void messageReceived(Object message); 
//        public void messageReceived(String message);
    }
}