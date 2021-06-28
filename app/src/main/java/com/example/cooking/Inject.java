package com.example.cooking;

import com.example.cooking.domain.LoginActivityLogic;
import com.example.cooking.domain.RegisterActivityLogic;
import com.example.cooking.server.Server;
import com.example.cooking.server.ServerImpl;

public class Inject {
    private static final Server SERVER = new ServerImpl();

    public static Server server(){return SERVER;}

    public static RegisterActivityLogic registerActivityLogic(){return new RegisterActivityLogic(SERVER);}

    public static LoginActivityLogic loginActivityLogic(){return new LoginActivityLogic(SERVER);}
}
