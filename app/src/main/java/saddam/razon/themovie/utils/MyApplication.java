package saddam.razon.themovie.utils;

import android.app.Application;
import android.content.Context;

import javax.inject.Inject;

import saddam.razon.themovie.dataParser.Client;
import saddam.razon.themovie.dataParser.Parser;
import saddam.razon.themovie.utils.DI.DaggerMyComponent;
import saddam.razon.themovie.utils.DI.MyComponent;


/**
 * Created by RAZON on 20-Jan-18.
 */

public class MyApplication extends Application {

    public static MyApplication sInstance = null;

    public static MyApplication getMyApplication(Context context){
        if (sInstance == null){
            sInstance = (MyApplication) context.getApplicationContext();

        }

        return sInstance;
    }

    MyComponent component;

    @Inject
    Parser parser;
    private static Client client;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerMyComponent.builder().build();
        component.inject(getMyApplication(getApplicationContext()));

    }

    public MyComponent getComponent(){
        return component;
    }


    public Client getClient(){
        if (client == null){
            client = parser.getRetroClient();
        }
        return client;
    }


}
