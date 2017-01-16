package app.hoocchi.perfectdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by st on 2017/1/16.
 */
public abstract class BaseActivity extends AppCompatActivity{

    /**
     * 直接使用ActionBar设置标题
     * @param text
     */
    protected void setTitle(String text){
        getSupportActionBar().setTitle(text);
    }

    /**
     * 使用Toolbar设置标题
     * @param text
     */
    protected void setBarTitle(String text){
        Toolbar bar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(bar);
        bar.setTitle(text);
    }

    /**
     * 使用Toolbar设置标题并且有返回键
     * @param text
     */
    protected void setTitleAndUpEnabled(String text){
        setBarTitle(text);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
