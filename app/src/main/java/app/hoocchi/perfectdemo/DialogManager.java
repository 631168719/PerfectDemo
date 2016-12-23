package app.hoocchi.perfectdemo;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by st on 2016/12/23.
 */
public class DialogManager {


    private DialogManager(){

    }

    /**
     * 显示对话框
     * @param context
     * @param msg
     * @param listener
     * @return
     */
    public static AlertDialog showAlertDialog(Context context , String msg , DialogInterface.OnClickListener listener){
        return new AlertDialog.Builder(context)
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("哦，知道了" , listener)
                .show();
    }




}
