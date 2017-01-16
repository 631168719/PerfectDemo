package app.hoocchi.perfectdemo;

import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Window;

/**
 * Created by st on 2017/1/10.
 */
public class DeviceManager {

    /**
     * DP转换为Px
     * @param context
     * @param dpValue
     * @return
     */
    public static int dpToPx(Context context , float dpValue){
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5f);
    }

    /**
     * DP转换为Px
     * @param context
     * @param dpValue
     * @return
     */
    public static float dp2Px(Context context , float dpValue){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP , dpValue ,
                 context.getResources().getDisplayMetrics());
    }

    /**
     * 获取状态栏高度（推荐）
     * @param context
     * @return
     */
    public static float getStatusBarHeight(Context context){
        int id = context.getResources().getIdentifier("status_bar_height" , "dimen" , "android");
        if(id > 0){
            return context.getResources().getDimensionPixelSize(id);
        }

        return 0;
    }

    /**
     * 获取状态栏高度（不可在onCreate()中使用）
     * @param window
     * @return
     */
    public static float getStatusBarHeight(Window window){
        Rect rect = new Rect();
        window.getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect.top;
    }

    /**
     * 获取屏幕的宽度
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context){
        return context.getResources().getDisplayMetrics().widthPixels ;
    }

    /**
     * 获取屏幕的高度
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context){
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取屏幕的宽度
     * @param window
     * @return
     */
    public static int getScreenWidth(Window window){
        DisplayMetrics outMetrics = new DisplayMetrics();
        window.getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获取屏幕的高度
     * @param window
     * @return
     */
    public static int getScreenHeight(Window window){
        DisplayMetrics outMetrics = new DisplayMetrics();
        window.getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

}
