package app.hoocchi.perfectdemo.custom_view_demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by Administrator on 2017/1/13.
 */
public class BasicTexts extends View{

    public BasicTexts(Context context) {
        this(context , null);
    }

    public BasicTexts(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //关闭硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE , null);
    }

    public BasicTexts(Context context, AttributeSet attrs) {
        this(context, attrs , 0);
    }

    private Paint getPaint(){
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(4);
        /**
         * 设置字体大小
         */
        paint.setTextSize(dpToPx(16));
        /**
         * 设置字体对齐方式
         * Align.LEFT ： 文字被绘制在基点的右方
         * Align.CENTER : 文字的中间部分绘制在基点位置
         * Align.RIGHT ； 文字被绘制在基点的左方
         */
        paint.setTextAlign(Paint.Align.LEFT);

        /**
         * 是否绘制带有删除线的文字
         */
        paint.setStrikeThruText(false);
        /**
         * 是否绘制带有下划线的文字
         */
        paint.setUnderlineText(false);
        /**
         * 是否绘制粗体文字
         */
        paint.setFakeBoldText(false);
        /**
         * 设置字体
         *
         * Typeface.DEFAULT 默认字体
         * Typeface.DEFAULT_BOLD 粗体
         * Typeface.ITALIC 斜体
         * Typeface.BOLD_ITALIC 粗斜体
         */
        paint.setTypeface(Typeface.DEFAULT);

        /**
         * top : 该字符串距离基线上方的最大距离，为负数
         * bottom : 该字符串距离基线下方的最大距离，为正数
         * ascent : 该字符串最高字符距离基线的距离，为负数
         * descent : 该字符串最低字符距离基线的距离，为正数
         */
        Paint.FontMetrics metrics = paint.getFontMetrics();

        /**
         * 获取该字符串最小边界，基于基点（0，0）计算
         */
//        paint.getTextBounds();

        return paint ;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawTextByAlign(canvas);

        drawTextByStyle(canvas);

        drawTextByTypeface(canvas);

        drawTextBounds(canvas);
    }

    private void drawTextByAlign(Canvas canvas) {
        String text = "Hello Android !";
        float baseX = dpToPx(200) ;
        float baseY = dpToPx(30) ;

        Paint paint = getPaint();
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setStyle(Paint.Style.FILL);
        float textWidth = paint.measureText(text);

        canvas.drawText("LEFT ：" , dpToPx(10) , baseY , getPaint());

        //绘制Style.FILL类型文字
        canvas.drawText(text , baseX , baseY , paint);
        //绘制基线，文字显示在基点的右边
        canvas.drawLine(baseX , baseY  , baseX + textWidth , baseY , paint);

        baseY = dpToPx(50);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawText("CENTER ：" , dpToPx(10) , baseY , getPaint());
        //绘制Style.STROKE类型文字
        canvas.drawText(text , baseX , baseY , paint);
        //绘制基线，文字中间部分显示在基点的位置
        canvas.drawLine(baseX , baseY  , baseX + textWidth , baseY , paint);

        baseY = dpToPx(80);
        paint.setTextAlign(Paint.Align.RIGHT);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawText("RIGHT :" , dpToPx(10) , baseY , getPaint());
        //绘制Style.FILL_AND_STROKE类型文字
        canvas.drawText(text, baseX, baseY, paint);
        //绘制基线，文字显示在基点的左边
        canvas.drawLine(baseX, baseY, baseX + textWidth, baseY, paint);

    }

    private void drawTextByStyle(Canvas canvas) {
        String text = "Hello Android !";
        float baseX = dpToPx(200);
        float baseY = dpToPx(120);
        Paint paint = getPaint();
        paint.setTextSize(dpToPx(20));

        //绘制删除线文字
        canvas.drawText("删除线" , dpToPx(10) , baseY , getPaint());
        paint.setStrikeThruText(true);
        canvas.drawText(text , baseX , baseY , paint);

        //绘制下划线文字
        baseY = dpToPx(150);
        canvas.drawText("下划线" , dpToPx(10) , baseY , getPaint());
        paint.setStrikeThruText(false);
        paint.setUnderlineText(true);
        canvas.drawText(text , baseX , baseY , paint);

        //绘制粗体文字
        baseY = dpToPx(180);
        canvas.drawText("粗体" , dpToPx(10) , baseY , getPaint());
        paint.setUnderlineText(false);
        paint.setFakeBoldText(true);
        canvas.drawText(text , baseX , baseY , paint);

    }

    private void drawTextByTypeface(Canvas canvas) {
        String text = "Hello Android !";
        float baseX = dpToPx(200);
        float baseY = dpToPx(220);
        Paint paint = getPaint();
        paint.setTextSize(dpToPx(20));

        canvas.drawText("bold" , dpToPx(10) , baseY , getPaint());
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        canvas.drawText(text , baseX , baseY , paint);

        baseY = dpToPx(250);
        canvas.drawText("monospace" , dpToPx(10) , baseY , getPaint());
        paint.setTypeface(Typeface.MONOSPACE);
        canvas.drawText(text , baseX , baseY , paint);

        baseY = dpToPx(280);
        canvas.drawText("serif" , dpToPx(10) , baseY , getPaint());
        paint.setTypeface(Typeface.SERIF);
        canvas.drawText(text , baseX , baseY , paint);

        baseY = dpToPx(310);
        canvas.drawText("sans serif" , dpToPx(10) , baseY , getPaint());
        paint.setTypeface(Typeface.SANS_SERIF);
        canvas.drawText(text, baseX, baseY, paint);

        baseY = dpToPx(340);
        canvas.drawText("custom typeface" , dpToPx(10) , baseY , getPaint());
        Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets() ,
                "custom_font.otf");
        paint.setTypeface(myTypeface);
        canvas.drawText(text, baseX, baseY, paint);
    }


    private void drawTextBounds(Canvas canvas){
        String text = "Hello Android !";
        float baseX = dpToPx(130);
        float baseY = dpToPx(450);
        Paint paint = getPaint();
        paint.setTextSize(dpToPx(30));

        float textWidth = paint.measureText(text);

        canvas.drawText(text , baseX , baseY , paint);

        //绘制基线
        paint.setColor(Color.parseColor("#e91e63"));
        canvas.drawText("基线" , dpToPx(10) , baseY , getPaint());
        canvas.drawLine(baseX , baseY , textWidth + baseX , baseY , paint);

        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        //绘制Top线
        paint.setColor(Color.parseColor("#3f51b5"));
        canvas.drawLine(baseX , fontMetrics.top + baseY , textWidth + baseX , fontMetrics.top + baseY , paint);

        //绘制ascent线
        paint.setColor(Color.parseColor("#00bcd4"));
        canvas.drawLine(baseX , fontMetrics.ascent + baseY , textWidth + baseX , fontMetrics.ascent + baseY , paint);

        //绘制descent线
        paint.setColor(Color.parseColor("#009688"));
        canvas.drawLine(baseX , fontMetrics.descent + baseY , textWidth + baseX , fontMetrics.descent + baseY , paint);

        //绘制bottom线
        paint.setColor(Color.parseColor("#03a9f4"));
        canvas.drawLine(baseX , fontMetrics.bottom + baseY , textWidth + baseX , fontMetrics.bottom + baseY , paint);

        //绘制最小矩形
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        Rect bounds = new Rect();
        //基于(0，0)位置
        paint.getTextBounds(text , 0 , text.length() , bounds);

        canvas.drawRect(bounds.left + baseX , bounds.top + baseY ,
                bounds.right + baseX , bounds.bottom + baseY , paint);

    }

    private int dpToPx(int dpValue){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP , dpValue ,
                getResources().getDisplayMetrics());
    }
}
