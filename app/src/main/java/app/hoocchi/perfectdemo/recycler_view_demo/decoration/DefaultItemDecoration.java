package app.hoocchi.perfectdemo.recycler_view_demo.decoration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by st on 2017/1/12.
 */
public class DefaultItemDecoration extends RecyclerView.ItemDecoration{

    private static final int [] ATTRS = {
            android.R.attr.listDivider ,
    };

    public static final int HORIZONTAL = OrientationHelper.HORIZONTAL ;
    public static final int VERTICAL = OrientationHelper.VERTICAL ;

    private int mOrientation ;
    private Drawable mDividerDrawable ;

    public DefaultItemDecoration(Context context , int orientation){
        TypedArray ta = context.obtainStyledAttributes(ATTRS);
        mDividerDrawable = ta.getDrawable(0);
        ta.recycle();

        setOrientation(orientation);
    }

    public void setOrientation(int orientation){
        if(orientation != HORIZONTAL && orientation != VERTICAL){
            throw new IllegalStateException("Invalid Orientation");
        }

        mOrientation = orientation ;
    }

    /**
     * 绘制分割线 ， 在绘制Item之前调用
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if(mOrientation == HORIZONTAL){
            drawHorizontal(c , parent);
        }else if(mOrientation == VERTICAL){
            drawVertical(c , parent);
        }
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop() ;
        final int bottom = parent.getHeight() - parent.getPaddingBottom() ;
        final int childCount = parent.getChildCount();

        for(int i = 0 ; i < childCount ; i++){
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + lp.rightMargin + Math.round(
                    ViewCompat.getTranslationX(child));
            final int right = left + mDividerDrawable.getIntrinsicWidth() ;

            mDividerDrawable.setBounds(left , top , right , bottom);
            mDividerDrawable.draw(c);
        }

    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft() ;
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();

        for(int i =0 ; i < childCount ; i++){
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + lp.bottomMargin + Math.round(
                    ViewCompat.getTranslationY(child));
            final int bottom = top + mDividerDrawable.getIntrinsicHeight() ;

            mDividerDrawable.setBounds(left , top , right , bottom);
            mDividerDrawable.draw(c);
        }
    }

    /**
     * 在绘制Item之后调用
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    /**
     * 设置分割线的宽和高
     * @param outRect 当前ItemView的四边间距
     * @param view    当前ItemView
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if(mOrientation == VERTICAL){
            outRect.set(0 , 0 , 0 , mDividerDrawable.getIntrinsicHeight());
        }else if(mOrientation == HORIZONTAL){
            outRect.set(0 , 0 , mDividerDrawable.getIntrinsicWidth(), 0);
        }
    }

}
