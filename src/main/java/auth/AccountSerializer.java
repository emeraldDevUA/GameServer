package auth;

import org.server.LoginTask;

import java.io.*;
import java.util.ArrayList;

public class AccountSerializer {
    private ArrayList<Account> accounts = new ArrayList<>();
    public AccountSerializer(){

    }


    public void serialize(){

        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    new FileOutputStream("src/main/java/auth/data.dat"));
            objectOutputStream.writeObject(accounts);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void deserialize(){
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(
                    new FileInputStream("src/main/java/auth/data.dat"));
            accounts = (ArrayList<Account>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            accounts = new ArrayList<>();
            throw new RuntimeException(e);
        }

    }

    public void addAccount(LoginTask task){
        //accounts.add(task.getAccount());
    }

}
