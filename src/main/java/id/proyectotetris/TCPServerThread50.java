package id.proyectotetris;

import id.proyectotetris.sockets.*;
import java.io.*;
import java.net.Socket;

public class TCPServerThread50 extends Thread {
    
    private Socket client;
    private TCPServer50 tcpserver;
    public int clientID;                  
    private boolean running = false;
    
    public ObjectOutputStream mOut; 
//    public PrintWriter mOut; 
    public ObjectInputStream in;    
//    public BufferedReader in; 
    
    private TCPServer50.OnMessageReceived messageListener = null;
    private Object message; 
//    private String message;
    TCPServerThread50[] cli_amigos;

    public TCPServerThread50(Socket client_, TCPServer50 tcpserver_, int clientID_, TCPServerThread50[] cli_ami_) {
        this.client = client_;
        this.tcpserver = tcpserver_;
        this.clientID = clientID_;
        this.cli_amigos = cli_ami_;
    }
    
    public void trabajen(int cli) {      
        sendMessage("TRABAJAMOS [" + cli + "]..."); 
    }
    
    @Override
    public void run() {
        running = true;
        try {
            try {               
                mOut = new ObjectOutputStream(client.getOutputStream()); 
                mOut.flush(); 
                in = new ObjectInputStream(client.getInputStream()); 
                System.out.println("TCP Server: Streams de objetos creados.");

                sendMessage("ID:" + clientID); //

                messageListener = tcpserver.getMessageListener();

                while (running) {
                    message = in.readObject(); 
                    
                    if (message != null && messageListener != null) {
                        messageListener.messageReceived(message);
                    }

                    message = null;
                }
            } catch (Exception e) {
                System.out.println("TCP Server S: Error " + e);
            } finally {
                client.close();
            }

        } catch (Exception e) {
            System.out.println("TCP Server C: Error " + e);
        }
    }
    
    public void stopClient() {
        running = false;
    }
    
    public void sendMessage(Object message) { 
        try {
            if (mOut != null) {
                mOut.reset();
                mOut.writeObject(message);
                mOut.flush();
            }
        } catch (IOException e) {
            System.out.println("Error al enviar objeto al cliente " + clientID + ": " + e);
        }
    }
}