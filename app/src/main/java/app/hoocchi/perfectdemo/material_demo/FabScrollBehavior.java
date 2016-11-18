package app.hoocchi.perfectdemo.material_demo;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by st on 2016/11/18.
 * 自定义FAB的Behavior，跟随RecyclerView的滑动而显示和隐藏
 */
public class FabScrollBehavior extends FloatingActionButton.Behavior {

    /**
     * 必须添加该构造方法
     * @param context
     * @param attr
     */
    public FabScrollBehavior(Context context , AttributeSet attr) {
        super();
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL ||
                super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);

        Log.d("HAHA" , "dyConsumed = "+dyConsumed);
        if(dyConsumed > 0 && child.getVisibility() == View.VISIBLE){
            child.hide();
        }else if(dyConsumed < 0 && child.getVisibility() != View.VISIBLE){
            child.show();
        }
    }
}
