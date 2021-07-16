/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifal.client;

import java.io.InputStream;
import java.util.Scanner;
/**
 *
 * @author Victor
 */
public class clientReceiveMsgThread implements Runnable{

    private InputStream ReceivedStream;

    public clientReceiveMsgThread(InputStream ReceivedStream) {
        this.ReceivedStream = ReceivedStream;
    }
    
    @Override
    public void run() {
        
        try(Scanner s = new Scanner(this.ReceivedStream)){
            while(s.hasNextLine()){
                System.out.println(s.nextLine());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
