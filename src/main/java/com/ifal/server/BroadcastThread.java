package com.ifal.server;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class BroadcastThread implements Runnable{
    
    private Socket msgOwnerSc;
    private server s;

    BroadcastThread(Socket msgOwnerSocket, server s) {
        this.msgOwnerSc = msgOwnerSocket;
        this.s = s;
    }
    
    public void run(){
        try{
            Scanner sc = new Scanner(this.msgOwnerSc.getInputStream());
            while(sc.hasNextLine()){
                s.broadcastMessage(this.msgOwnerSc, sc.nextLine());
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
}
