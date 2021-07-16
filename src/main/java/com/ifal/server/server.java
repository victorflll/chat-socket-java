package com.ifal.server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class server {
    private int port;
    private List<Socket> clientList;
    
    public server(int port){
        this.port = port;
        this.clientList = new ArrayList<>();
    }
    
    public void execute() throws IOException{
        try{
            ServerSocket serverSocket = new ServerSocket(this.port);
            System.out.println("Porta " +this.port+ "do servidor aberta... Aguardando conexão.");
            
            while(true){
                Socket newClient = serverSocket.accept();
                System.out.println("Nova conexão com o cliente " +newClient.getInetAddress().getHostAddress()+ ":" + newClient.getLocalPort());
                
                this.clientList.add(newClient);
                
                BroadcastThread tc = new BroadcastThread(newClient, this);
                new Thread(tc).start();
            }
        }catch (Exception e){
        }
    }
    
    public void broadcastMessage(Socket msgOwner, String msg){
        for(Socket oneClient : this.clientList){
            if(!oneClient.equals(msgOwner)){
                try{
                    PrintStream ps = new PrintStream(oneClient.getOutputStream());
                    ps.println(msg);
                } catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static void main(String[] args) throws IOException{
        server myServer = new server(23412);
        try{
            myServer.execute();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
