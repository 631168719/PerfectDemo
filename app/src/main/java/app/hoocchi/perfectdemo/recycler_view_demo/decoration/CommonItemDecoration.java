package app.hoocchi.perfectdemo.recycler_view_demo.decoration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by st on 2017/1/12.
 */
public class CommonItemDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = {
            android.R.attr.listDivider,
    };

    public static final int HORIZONTAL = OrientationHelper.HORIZONTAL;
    public static final int VERTICAL = OrientationHelper.VERTICAL;

    private int mOrientation;
    private Drawable mDividerDrawable;

    /**
     * divider的左右或上下之间的padding
     */
    private int mPadding;

    /**
     * divider的大小
     */
    private int mSize ;

    private Paint mPaint;

    private Type mType;

    /**
     * 两种类型：
     * DIVIDER表示使用Drawable ， LINE表示使用Paint绘制
     */
    public enum Type {
        DIVIDER, LINE
    }

    /**
     * 使用android默认的divider
     *
     * @param context
     * @param orientation
     */
    public CommonItemDecoration(Context context, int orientation) {
        TypedArray ta = context.obtainStyledAttributes(ATTRS);
        mDividerDrawable = ta.getDrawable(0);
        ta.recycle();

        setOrientation(orientation);

        mType = Type.DIVIDER;
    }

    /**
     * 使用resId对应的图片作为divider
     *
     * @param context
     * @param orientation
     * @param resId
     */
    public CommonItemDecoration(Context context, int orientation, int resId) {
        this(context, orientation, ContextCompat.getDrawable(context, resId));
    }

    /**
     * 使用drawable作为divider
     *
     * @param context
     * @param orientation
     * @param drawable
     */
    public CommonItemDecoration(Context context, int orientation, Drawable drawable) {
        setOrientation(orientation);
        setDividerDrawable(drawable);

        mType = Type.DIVIDER;
    }

    /**
     * 使用paint绘制divider
     *
     * @param orientation
     * @param paint
     */
    public CommonItemDecoration(int orientation, Paint paint) {
        setOrientation(orientation);
        setPaint(paint);

        mType = Type.LINE;
    }

    /**
     * 使用默认Paint绘制divider
     * @param orientation
     */
    public CommonItemDecoration(int orientation){
        this(orientation , null);
    }

    /**
     * 根据Orientation来绘制分割线
     *
     * @param orientation
     */
    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL && orientation != VERTICAL) {
            throw new IllegalStateException("Invalid Orientation");
        }

        mOrientation = orientation;
    }

    /**
     * 设置画笔
     *
     * @param paint
     */
    public void setPaint(Paint paint) {
        if (paint != null) {
            mPaint = paint;
        } else {
            mPaint = getDefaultPaint();
        }
    }

    /**
     * 获取画笔
     * @return
     */
    public Paint getPaint(){
        return mPaint;
    }

    /**
     * 设置分割线图片
     *
     * @param drawable
     */
    public void setDividerDrawable(Drawable drawable) {
        if (drawable == null) {
            throw new IllegalStateException("Drawable Is Null");
        }

        mDividerDrawable = drawable;
    }

    /**
     * 设置分割线图片
     *
     * @param context
     * @param resId
     */
    public void setDividerDrawable(Context context, int resId) {
        setDividerDrawable(ContextCompat.getDrawable(context, resId));
    }

    /**
     * 当VERTICAL时，设置divider的左右Padding
     * 当HORIZONTAL，设置divider的上下Padding
     *
     * @param padding
     */
    public void setPadding(int padding) {
        if (padding > 0) {
            this.mPadding = padding;
        }
    }

    /**
     * 设置分割线大小
     * @param size
     */
    public void setSize(int size){
        if(mType == Type.DIVIDER){
            mSize = size ;
        }else if(mType == Type.LINE){
            mPaint.setStrokeWidth(size);
        }
    }

    /**
     * 设置分割线颜色
     * @param color
     */
    public void setColor(@ColorInt int color){
        if(mType == Type.DIVIDER){
            DrawableCompat.setTint(mDividerDrawable , color);
        }else if(mType == Type.LINE){
            mPaint.setColor(color);
        }
    }

    private Paint getDefaultPaint() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(6);

        return paint;
    }

    /**
     * 绘制分割线 ， 在绘制Item之前调用
     *
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == HORIZONTAL) {
            drawHorizontal(c, parent);
        } else if (mOrientation == VERTICAL) {
            drawVertical(c, parent);
        }
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();
        final int childCount = parent.getChildCount();

        if(mType == Type.DIVIDER){
            mSize = mSize <= 0 ? mDividerDrawable.getIntrinsicWidth() : mSize ;
        }

        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + lp.rightMargin + Math.round(
                    ViewCompat.getTranslationX(child));

            int right = 0;
            if (mType == Type.DIVIDER) {
                right = left + mSize;
            } else if(mType == Type.LINE){
                right = (int) (left + mPaint.getStrokeWidth());
            }

            Rect rect = new Rect(left, top + mPadding, right, bottom - mPadding);
            drawDivider(c, rect);
        }

    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();

        if(mType == Type.DIVIDER){
            mSize = mSize <= 0 ? mDividerDrawable.getIntrinsicHeight() : mSize;
        }

        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + lp.bottomMargin + Math.round(
                    ViewCompat.getTranslationY(child));

            int bottom = 0 ;
            if (mType == Type.DIVIDER) {
                 bottom = top + mSize;
            }else if(mType == Type.LINE){
                 bottom = (int) (top + mPaint.getStrokeWidth());
            }

            Rect rect = new Rect(left + mPadding, top, right - mPadding, bottom);
            drawDivider(c, rect);
        }
    }

    private void drawDivider(Canvas canvas, Rect rect) {
        if (mType == Type.DIVIDER) {
            mDividerDrawable.setBounds(rect.left, rect.top, rect.right, rect.bottom);
            mDividerDrawable.draw(canvas);

        } else if (mType == Type.LINE) {
            int center =  0 ;

            if(mOrientation == VERTICAL){
                center = (rect.top + rect.bottom) / 2;
            }else {
                center = (rect.left + rect.right) / 2 ;
            }

            canvas.drawLine(rect.left, center, rect.right, center, mPaint);
        }

    }

    /**
     * 在绘制Item之后调用
     *
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    /**
     * @param outRect 当前ItemView的四边间距
     * @param view    当前ItemView
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        setItemOffsets(outRect);
    }

    private void setItemOffsets(Rect outRect) {
        if (mType == Type.DIVIDER) {
            if (mOrientation == VERTICAL) {
                outRect.set(0, 0, 0, mSize);
            } else if (mOrientation == HORIZONTAL) {
                outRect.set(0, 0, mSize, 0);
            }
        } else if (mType == Type.LINE) {
            if (mOrientation == VERTICAL) {
                outRect.set(0, 0, 0, (int) mPaint.getStrokeWidth());
            } else if (mOrientation == HORIZONTAL) {
                outRect.set(0, 0, (int) mPaint.getStrokeWidth(), 0);
            }
        }

    }

}
