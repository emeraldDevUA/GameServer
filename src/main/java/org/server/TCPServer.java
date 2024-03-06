package org.server;

import SharedData.DataRecord;
import SharedData.GameSessionsBank;

import auth.Account;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import org.xml.sax.InputSource;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;

public class TCPServer extends Thread{
    private int port = 7870;
    private ServerSocket tcpSocket;
    public static ArrayList<GameSessionsBank> gamesList;
    boolean inGame, online;
    public TCPServer(){
        try {
            tcpSocket = new ServerSocket(port);
            tcpSocket.setSoTimeout(5000);
        }catch (IOException e){
            e.printStackTrace();
        }
        inGame = false;
        online = false;
    }


    public void processRequests() {
        try {
            Socket s = tcpSocket.accept();
            EventHandler eventHandler = new EventHandler(s,inGame,online);
            eventHandler.start();
            System.out.println(s.getPort());
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void run(){
        while (true) {
            processRequests();
            Thread.onSpinWait();
        }
    }
}

class EventHandler extends Thread{
    private final Socket Ssocket;
    boolean inGame, onLine;
    LoginTask loginTask;
    public EventHandler(Socket socket, boolean inGame, boolean online){

        Ssocket = socket;

        this.inGame = inGame;
        this.onLine = online;
    }


    @Override
    public void run() {

        try {
            ObjectOutputStream oos = new ObjectOutputStream(Ssocket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(Ssocket.getInputStream());

            StringBuilder stringBuilder = new StringBuilder();

            JAXBContext login = JAXBContext.newInstance(LoginTask.class);

            byte[] bytes = new byte[512];
            StringBuilder resultingTask = new StringBuilder();
            if(ois.read(bytes) !=-1){
                readXMLclass (oos, bytes, resultingTask);
            }
            loginTask = (LoginTask) login.createUnmarshaller().unmarshal(
                    new ByteArrayInputStream(resultingTask.toString().getBytes(StandardCharsets.UTF_8)));
           /*  */
            loginTask.password = new String(Base64.getDecoder().decode(loginTask.password));
            if(loginTask!=null){
                oos.write(200);
                System.out.println(loginTask);
                System.out.println("Success.");
            }else {
                oos.write(400);
                System.out.println("WTF");
            }

            oos.flush();
            while(true){

                while (ois.read()!=-1){
                    resultingTask.delete(0,resultingTask.length());
                    readXMLclass (oos, bytes, resultingTask);

                }System.out.println(resultingTask);
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

            }
       /*     for(int i = 0; i < TCPServer.gamesList.size(); i++){
                Account[] accounts = (Account[]) TCPServer.gamesList.get(i).map.keySet().toArray();
                for(int j = 0; j < accounts.length; j++){
                 if(accounts[i].NickName.compareTo(loginTask.NickName) == 0){
                    accounts[i].setPort(Ssocket.getPort());
                     break;
                 }
                }
            }*/



    } catch (IOException  e) {
        throw new RuntimeException(e);
    } catch (JAXBException e) {
            System.err.println("This class cannot be unmarshalled");
            throw new RuntimeException(e);
        }
    }

    private static void readXMLclass(ObjectOutputStream oos, byte[] bytes, StringBuilder resultingTask) throws IOException {
        oos.write(200);
        int lastBracket = 0;
        for (int i = 0; i < bytes.length; i++) {
           if(bytes[i] == '>'){
               lastBracket = i;
           }
        }
        for (int i = 0; i <= lastBracket; i++) {
            resultingTask.append((char) bytes[i]);
        }
    }

    private BaseTask readTask(){
        BaseTask task = null;



        return task;
    }




}