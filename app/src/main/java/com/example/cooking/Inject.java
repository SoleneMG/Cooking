package com.example.cooking;

import com.example.cooking.domain.LoginActivityLogic;
import com.example.cooking.domain.RegisterLogic;
import com.example.cooking.server.Server;
import com.example.cooking.server.serverImpl.RetrofitImpl;

public class Inject {
    private static final Server SERVER = new RetrofitImpl();

    public static Server server(){return SERVER;}

    public static RegisterLogic registerActivityLogic(){return new RegisterLogic(SERVER);}

    public static LoginActivityLogic loginActivityLogic(){return new LoginActivityLogic(SERVER);}
}
