package com.example.cooking;

import android.app.Application;
import android.content.Context;

import com.example.cooking.data.database.CookingDatabase;
import com.example.cooking.data.database.databaseImpl.RoomDatabaseImpl;
import com.example.cooking.domain.LoginLogic;
import com.example.cooking.domain.RegisterLogic;
import com.example.cooking.data.server.Server;
import com.example.cooking.data.server.serverImpl.RetrofitImpl;

public class Inject {

    private static final Server SERVER = new RetrofitImpl();
    private static final CookingDatabase DATABASE = RoomDatabaseImpl.getDatabase(MyApplication.getAppContext());

    public static Server server(){return SERVER;}

    public static RegisterLogic registerActivityLogic(){return new RegisterLogic(SERVER);}

    public static LoginLogic loginActivityLogic(){return new LoginLogic(SERVER);}

    public static CookingDatabase database(){return DATABASE;}
}
