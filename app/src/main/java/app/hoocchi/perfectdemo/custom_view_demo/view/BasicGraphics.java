package app.hoocchi.perfectdemo.custom_view_demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by Administrator on 2017/1/10.
 */
public class BasicGraphics extends View {

    private float mStartDegree  ;
    private float mSweepDegree  ;

    public BasicGraphics(Context context) {
        this(context , null);
    }

    public BasicGraphics(Context context, AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public BasicGraphics(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //关闭硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE , null);
    }

    private Paint getPaint(@ColorInt int color , Paint.Style style){
        Paint paint = new Paint();
        //抗锯齿
        paint.setAntiAlias(true);
        //防抖动，绘制图像效果更好
        paint.setDither(true);
        paint.setColor(color);
        /**
         * Style.FILL : 填充
         * Style.STROKE : 描边
         * Style.FILL_AND_STROKE : 填充且描边
         */
        paint.setStyle(style);
        /**
         * 设置描边的边宽
         */
        paint.setStrokeWidth(10);

        /**
         * 对Line、Point起作用
         * Cap.BUTT ： 无线冒
         * Cap.ROUND : 圆形线冒（超出起始点和终点的部分为圆形）
         * Cap.SQUARE : 矩形线冒（超出起始点和终点的部分为矩形）
         */
//        paint.setStrokeCap(Paint.Cap.BUTT);

        /**
         * 对Path、Rectangle等且Style.STROKE或Style.FILL_AND_STROKE起作用
         * Join.MITER ： 路径交界处为锐角
         * Join.ROUND :  路径交界处为圆形
         * Join.BEVEL :  路径交界处为直线
         */
//        paint.setStrokeJoin(Paint.Join.MITER);

        return paint ;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawPoints(canvas);

        drawLines(canvas);

        drawCircles(canvas);

        drawOvals(canvas);

        drawRectangles(canvas);

        drawRoundRects(canvas);

        drawArcs(canvas);

        drawAutoArcs(canvas);


    }

    private void drawLines(Canvas canvas) {
        Paint paint = getPaint(Color.RED , Paint.Style.FILL);
        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setStrokeWidth(15);
        canvas.drawLine(dpToPx(10) , dpToPx(30) , dpToPx(80) , dpToPx(30) , paint);

        float[] points = {
                dpToPx(120) , dpToPx(30) ,
                dpToPx(180) , dpToPx(30) ,
                dpToPx(200) , dpToPx(30) ,
                dpToPx(240) , dpToPx(30) ,
        };

        //设置圆形线冒
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setColor(Color.MAGENTA);
        /**
         * points中每两个值代表一个点(X,Y)，必须是偶数
         * 绘制的不是连续的直线，而是每两个点绘制一条线段
         */
        canvas.drawLines(points , paint);

        points = new float[]{
                dpToPx(260) , dpToPx(30) ,
                dpToPx(280) , dpToPx(30) ,
                dpToPx(340) , dpToPx(30) ,
                dpToPx(380) , dpToPx(30) ,
        };
        paint.setStrokeCap(Paint.Cap.SQUARE);
        paint.setColor(Color.BLUE);
        /**
         * 从offset开始每两个值绘制count个数值
         */
        canvas.drawLines(points , 2, 4 , paint);

    }

    private void drawPoints(Canvas canvas){
        Paint paint = getPaint(Color.RED , Paint.Style.FILL);
        paint.setStrokeWidth(20);
        paint.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawPoint(dpToPx(30) , dpToPx(60) , paint);

        float [] points = {
                dpToPx(120) , dpToPx(60) ,
                dpToPx(150) , dpToPx(60) ,
                dpToPx(180) , dpToPx(60) ,
                dpToPx(210) , dpToPx(60) ,
        };
        //圆形点
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setColor(Color.MAGENTA);
        canvas.drawPoints(points , paint);

        points = new float[]{
                dpToPx(240) , dpToPx(60) ,
                dpToPx(280) , dpToPx(60) ,
                dpToPx(300) , dpToPx(60) ,
                dpToPx(320) , dpToPx(60) ,
        };

        paint.setStrokeCap(Paint.Cap.SQUARE);
        paint.setColor(Color.BLUE);
        canvas.drawPoints(points , 2 , 4 , paint);
    }


    private void drawCircles(Canvas canvas) {
        Paint paint = getPaint(Color.RED , Paint.Style.FILL);
        canvas.drawCircle(dpToPx(50) , dpToPx(120) , dpToPx(40) , paint);

        paint.setColor(Color.MAGENTA);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(dpToPx(180) , dpToPx(120) , dpToPx(40) , paint);

        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(dpToPx(300) , dpToPx(120) , dpToPx(40) , paint);
    }

    private void drawOvals(Canvas canvas){
        Paint paint = getPaint(Color.RED , Paint.Style.FILL);
        Rect rect = new Rect(dpToPx(10) , dpToPx(180) , dpToPx(100) , dpToPx(240));
        canvas.drawOval(new RectF(rect) , paint);

        paint.setColor(Color.MAGENTA);
        paint.setStyle(Paint.Style.STROKE);
        rect = new Rect(dpToPx(130) , dpToPx(180) , dpToPx(220) , dpToPx(240));
        canvas.drawOval(new RectF(rect) , paint);

        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        rect = new Rect(dpToPx(250) , dpToPx(180) , dpToPx(340) , dpToPx(240));
        canvas.drawOval(new RectF(rect), paint);
    }

    private void drawRectangles(Canvas canvas){
        Paint paint = getPaint(Color.RED , Paint.Style.FILL);
        paint.setStrokeWidth(16);
        Rect rect = new Rect(dpToPx(10) , dpToPx(260) , dpToPx(100) , dpToPx(310));
        canvas.drawRect(rect , paint);

        paint.setColor(Color.MAGENTA);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        rect = new Rect(dpToPx(130) , dpToPx(260) , dpToPx(220) , dpToPx(310));
        canvas.drawRect(rect , paint);

        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeJoin(Paint.Join.BEVEL);
        rect = new Rect(dpToPx(250) , dpToPx(260) , dpToPx(340) , dpToPx(310));
        canvas.drawRect(rect, paint);
    }

    private void drawRoundRects(Canvas canvas){
        Paint paint = getPaint(Color.RED , Paint.Style.FILL);
        float rx = dpToPx(6) ;
        float ry = dpToPx(10) ;
        Rect rect = new Rect(dpToPx(10) , dpToPx(330) , dpToPx(100) , dpToPx(390));
        canvas.drawRoundRect(new RectF(rect) , rx ,ry , paint);

        paint.setColor(Color.MAGENTA);
        paint.setStyle(Paint.Style.STROKE);
        rect = new Rect(dpToPx(130) , dpToPx(330) , dpToPx(220) , dpToPx(390));
        canvas.drawRoundRect(new RectF(rect) , rx , ry ,  paint);

        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        rect = new Rect(dpToPx(250) , dpToPx(330) , dpToPx(340) , dpToPx(390));
        canvas.drawRoundRect(new RectF(rect), rx , ry ,  paint);
    }

    private void drawArcs(Canvas canvas){
        Paint paint = getPaint(Color.RED , Paint.Style.FILL);
        Rect rect = new Rect(dpToPx(10) , dpToPx(420) , dpToPx(70) , dpToPx(480));
        canvas.drawArc(new RectF(rect) , 0 , 270 , true , paint);

        paint.setColor(Color.MAGENTA);
        rect = new Rect(dpToPx(90) , dpToPx(420) , dpToPx(150) , dpToPx(480));
        canvas.drawArc(new RectF(rect) , 0 , 270 , false , paint);

        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        rect = new Rect(dpToPx(170) , dpToPx(420) , dpToPx(230) , dpToPx(480));
        canvas.drawArc(new RectF(rect) , 0 , 270 , true , paint);

        paint.setColor(Color.YELLOW);
        rect = new Rect(dpToPx(250) , dpToPx(420) , dpToPx(310) , dpToPx(480));
        canvas.drawArc(new RectF(rect) , 0 , 270 , false , paint);
    }

    private void drawAutoArcs(Canvas canvas){
        Paint paint = getPaint(Color.RED , Paint.Style.FILL);
        Rect rect = new Rect(dpToPx(10) , dpToPx(490) , dpToPx(70) , dpToPx(550));
        canvas.drawArc(new RectF(rect) , mStartDegree , mSweepDegree , true , paint);

        paint.setColor(Color.MAGENTA);
        rect = new Rect(dpToPx(90) , dpToPx(490) , dpToPx(150) , dpToPx(550));
        canvas.drawArc(new RectF(rect) , mStartDegree , mSweepDegree , false , paint);

        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        rect = new Rect(dpToPx(170) , dpToPx(490) , dpToPx(230) , dpToPx(550));
        canvas.drawArc(new RectF(rect) , mStartDegree , mSweepDegree , true , paint);

        paint.setColor(Color.YELLOW);
        rect = new Rect(dpToPx(250) , dpToPx(490) , dpToPx(310) , dpToPx(550));
        canvas.drawArc(new RectF(rect) , mStartDegree , mSweepDegree , false , paint);

        mSweepDegree += 2 ;
        if(mSweepDegree >= 360f){
            mSweepDegree -= 360f ;
            mStartDegree += 12f;

            if(mStartDegree >= 360f){
                mStartDegree -= 360f ;
            }
        }

        invalidate();
    }


    private int dpToPx(int dpValue){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP , dpValue ,
                getResources().getDisplayMetrics());
    }
}
