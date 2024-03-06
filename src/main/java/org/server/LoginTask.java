package org.server;

//import auth.Account;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.InputStream;
import java.io.OutputStream;

@XmlRootElement
public class LoginTask implements BaseTask {
    private static final long serialVersionUID = 1234567L;
    @Override
    public String toString() {
        return "LoginTask{" +
                "gmail='" + gmail + '\'' +
                ", NickName='" + NickName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @XmlElement
    public String gmail, NickName;
    @XmlElement
    public String password;

    public LoginTask(String mail, String name, String pWord) {
        this.gmail = mail;
        this.NickName = name;
        this.password = pWord;

    }

    public LoginTask(){

    }
    @Override
    public void execute(InputStream is, OutputStream os) {

    }





}
