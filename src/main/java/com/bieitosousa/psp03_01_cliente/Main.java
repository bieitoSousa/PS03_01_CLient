/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bieitosousa.psp03_01_cliente;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bieito
 */
public class Main {




    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {

        }

    }

    public void startIN() {
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void startOUT() {
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String sendMessage(String msg) {
        try {

            out.println(msg);

            String a = in.readLine();
            System.out.println("send mensaje responde :[CLI] lee {" + a + "}");
            return a;
        } catch (Exception e) {
            return null;
        }
    }

    public String sendMessage(int msg) {
        try {

            out.println(msg);

            String a = in.readLine();
            System.out.println("send mensaje responde :[CLI] lee {" + a + "}");
            return a;
        } catch (Exception e) {
            return null;
        }
    }

    public String readMessage() {
        try {
            String a = in.readLine();
            System.out.println("[CLI] lee {" + a + "}");
            return a;
        } catch (Exception e) {
            return null;
        }
    }

    public void stopConnection() {
        try {
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {

        }

    }

    public static void main(String[] arg) {
       pInt();

    }

    public static void pInt() { // prueva con strings
        Main c = new Main();
        c.startConnection("localhost", 2000);
        int n = 50; // nº inicial
        int upIndex = 100; // nºrango inferior
        int downIndex = 0; // nº rango superior

        String l;
        while ((l = c.sendMessage(n)).contains("no")) {
            if (l.contains("menor")) {
                upIndex = n;
            } else if (l.contains("mayor")) {
                downIndex = n;
            } else {
                System.out.println("error");
            }
            //se genera un numero que es la mitad entre el limite superior e inferior
            n = (Math.round((upIndex - downIndex) / 2)) + downIndex;
            System.out.println("se carga el mensaje[" + n + "] numero entre {" + downIndex + "," + upIndex + "}");
        }
        System.out.println("[CLI]fin : encontrado numero [" +l+"]");
        c.stopConnection();
    }

    



}


