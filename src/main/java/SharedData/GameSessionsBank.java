package SharedData;


import auth.Account;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Random;

public class GameSessionsBank implements Serializable {
    public HashMap<Account,DataRecord> map;
    public long id = 0;
    public GameSessionsBank(){

        map = new HashMap<>();
        id = new Random().nextInt();
    }


    public void update(Account acc,DataRecord dr) {

        map.replace(acc,dr);
    }
}


