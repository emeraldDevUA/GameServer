package org.server;

import SharedData.DataRecord;
import SharedData.GameSessionsBank;
import auth.Account;

import java.io.*;

public class MultiplayerDataExchangeTask implements BaseTask{
    private final GameSessionsBank gameSessionsBank;

    public MultiplayerDataExchangeTask(){
        gameSessionsBank = new GameSessionsBank();

    }
    @Override
    public void execute(InputStream is, OutputStream os) {
        try {
            ((ObjectOutputStream) (os)).writeObject(gameSessionsBank);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Account account = (Account) ((ObjectInputStream)(is)).readObject();
            DataRecord dr = (DataRecord) ((ObjectInputStream)(is)).readObject();
            gameSessionsBank.update(account,dr);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    public GameSessionsBank getGameSessionBank()
    {
        return gameSessionsBank;
    }
}
