package com.p8;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    //request;id;longitude;latitude;
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(61532);
            Socket socket = server.accept();
            System.out.println("Connected" + socket.getLocalAddress());
            BufferedReader bis = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bos = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String data = "";
            while(!data.equals("close")) {
                if(bis.ready()){
                    data = bis.readLine();
                    if(!data.equals("close")) {
                        String values[] = data.split(";");
                        for (String value:values) {
                            System.out.println(value);
                        }
                    }
                }
                bos.write("2;4;Universite;Quebec;Montreal;NewYork\n");
                bos.flush();
                bos.write("2;-71.92598;45.378041;-71.21454;46.81228;-73.58781;45.50884;-73.935242;40.73061\n");
                bos.flush();
                Thread.sleep(1000);
            }
            bis.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
