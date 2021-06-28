package com.example.cooking;

import com.example.cooking.domain.MainActivityLogic;
import com.example.cooking.server.Server;
import com.example.cooking.server.ServerImpl;

public class Inject {
    private static final Server SERVER = new ServerImpl();

    public static Server server(){return SERVER;}

    public static MainActivityLogic mainActivityLogic(){return new MainActivityLogic(SERVER);}
}
