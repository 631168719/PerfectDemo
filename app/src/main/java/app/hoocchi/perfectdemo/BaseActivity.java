package app.hoocchi.perfectdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by st on 2017/1/16.
 */
public abstract class BaseActivity extends AppCompatActivity{

    protected void setBarTitle(String text){
        getSupportActionBar().setTitle(text);
    }

    protected void jumpActivity(Class clz){
        startActivity(new Intent(this , clz));
    }

    protected void jumpActivity(Class clz , Bundle data){
        Intent i = new Intent(this  , clz);
        i.putExtras(data);
        startActivity(i);
    }
}
