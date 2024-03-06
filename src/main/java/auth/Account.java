package auth;

import java.io.Serializable;

public class Account implements Serializable {
    public String gmail, NickName;
    protected String password;
    private long id;
    protected boolean online, inGame;
    private int currentPort = -1;

    public  Account(String gmail, String password,String name, long id)
    {
        this.gmail = gmail;
        this.NickName = name;
        this.password = password;
        this.id = id;
        online = false;
        inGame = false;
    }

    @Override
    public int hashCode()
    {
        return currentPort;
    }

    public void setPort(int port){
        currentPort = port;
    }

}
