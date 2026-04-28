package id.proyectotetris;

import id.proyectotetris.sockets.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class TCPServer50 {
    private Object message; 
//    private String message;
    public int nrcli = 0;
    public static final int SERVERPORT = 4444;
    private OnMessageReceived messageListener = null;
    private boolean running = false;
    public TCPServerThread50[] sendclis = new TCPServerThread50[10];
    public TCPServer50(OnMessageReceived messageListener) {
        this.messageListener = messageListener;
    }

    public OnMessageReceived getMessageListener(){
        return this.messageListener;
    }


    public void sendMessageTCPServer(Object message){ // public void sendMessageTCPServer(String message)
        for (int i = 1; i <= nrcli; i++) {
            if (sendclis[i] != null) { 
                sendclis[i].sendMessage(message);
                System.out.println("ENVIANDO OBJETO A JUGADOR " + (i));
            }
        }
    }
    
    public void run(){
        running = true;
        try{
            System.out.println("TCP Server"+"S : Connecting...");
            serverSocket = new ServerSocket(SERVERPORT);
            
            while(running){
                Socket client = serverSocket.accept();
                System.out.println("TCP Server"+"S: Receiving...");
                nrcli++;
                sendclis[nrcli] = new TCPServerThread50(client,this,nrcli,sendclis);
                Thread t = new Thread(sendclis[nrcli]);
                t.start();
                System.out.println("Nuevo conectado: "+ nrcli+" jugadores conectados");
            }
            
        }catch( Exception e){
            System.out.println("Error "+e.getMessage());
        }
    }

    public TCPServerThread50[] getClients(){
        return sendclis;
    } 

    public interface OnMessageReceived {
        public void messageReceived(Object message);//
    }
    
    ServerSocket serverSocket;
}