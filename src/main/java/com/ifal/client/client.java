/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifal.client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Victor
 */
public class client {
    private String serverIPaddr;
    private int ServerPort;

    public client(String serverIP, int serverPort) {
        this.serverIPaddr = serverIP;
        this.ServerPort = serverPort;
    }
    
    public void execute(){
        try {
            Socket clientSocket = new Socket(this.serverIPaddr, this.ServerPort);
            System.out.println("Cliente conectou-se com sucesso ao servidor.");
            
            //PASSO 1
            clientReceiveMsgThread ct = new clientReceiveMsgThread(clientSocket.getInputStream());
            new Thread(ct).start();
            
            //PASSO 2
            Scanner sc = new Scanner(System.in);
            PrintStream text = new PrintStream(clientSocket.getOutputStream());
            
            while(sc.hasNextLine()){
               text.println(sc.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {        
        try{
            client myclient = new client("localhost", 23412);
            myclient.execute();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
