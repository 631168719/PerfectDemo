package app.hoocchi.perfectdemo;

import android.graphics.Color;

/**
 * Created by st on 2016/11/18.
 */
public class DataCenter {
    private DataCenter() {

    }

    /**
     * 字符串数组
     */
    public static String[] mStrArrays = {
            "Item One", "Item Two", "Item Three", "Item Four",
            "Item Five", "Item Six", "Item Seven", "Item Eight",
            "Item Nine", "Item Ten", "Item One", "Item Two",
            "Item Three", "Item Four", "Item Five", "Item Six",
            "Item Seven", "Item Eight", "Item Nine", "Item Ten",
    };

    /**
     * TabLayout的Tab标题
     */
    public static String[] mTabTitles = {
            "Tab1" , "Tab2" , "Tab3"
    };

    /**
     * 图片的ID数组
     */
    public static int [] mImgIds = {
            R.drawable.a , R.drawable.b , R.drawable.c ,
            R.drawable.d , R.drawable.e , R.drawable.f ,
            R.drawable.g , R.drawable.h , R.drawable.i ,
    };

    /**
     * 颜色数组
     */
    public static int [] mColorArrays = {
            Color.RED, Color.CYAN, Color.GRAY,
            Color.GREEN, Color.DKGRAY, Color.MAGENTA,
            Color.LTGRAY , Color.YELLOW, Color.BLUE,
    };

}
