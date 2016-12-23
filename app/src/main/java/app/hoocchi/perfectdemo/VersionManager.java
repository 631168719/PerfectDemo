package app.hoocchi.perfectdemo;

import android.os.Build;

/**
 * Created by st on 2016/12/23.
 */
public class VersionManager {

    public static final int KITKAT = 19 ;
    public static final int LOLIPOP = 21 ;

    private VersionManager(){

    }

    /**
     * Android版本是否低于KitKat（19）
     * @return
     */
    public static boolean isLowerKitKat(){
        return (Build.VERSION.SDK_INT < KITKAT);
    }

    /**
     * Andriod版本是否大于等于KitKat（19）
     * @return
     */
    public static boolean isHigherKitKat(){
        return (Build.VERSION.SDK_INT >= KITKAT);
    }

    /**
     * Andriod版本是否小于Lolipop（21）
     *
     * @return
     */
    public static boolean isLowerLolipop() {
        return (Build.VERSION.SDK_INT < LOLIPOP);
    }

    /**
     * Andriod版本是否大于等于Lolipop（21）
     *
     * @return
     */
    public static boolean isHigherLolipop() {
        return (Build.VERSION.SDK_INT >= LOLIPOP);
    }

    /**
     * Andriod版本是否是KitKat（19）
     * @return
     */
    public static boolean isOnKitKat(){
        return (Build.VERSION.SDK_INT == KITKAT);
    }

    /**
     * Andriod版本是否是Lolipop（21）
     *
     * @return
     */
    public static boolean isOnLolipop() {
        return (Build.VERSION.SDK_INT == LOLIPOP);
    }
}
