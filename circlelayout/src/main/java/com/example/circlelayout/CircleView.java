package com.example.circlelayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class CircleView extends View {
    private Paint mTextPain;                        //初始化画笔
    private String mText = "";                      //初始化文字
    private int radius;                             //当前View的半径

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        configPaint();
    }

    private void configPaint() {
        mTextPain = new Paint();
        mTextPain.setColor(Color.WHITE);            //设置画笔颜色为白色
        mTextPain.setAntiAlias(true);               //开启抗锯齿，平滑文字和圆弧的边缘
        mTextPain.setTextAlign(Paint.Align.CENTER); //设置文本位于相对于原点的中间
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth() / 2;                                 //获取宽度一半
        int height = getHeight() / 2;                               //获取高度一半
        radius = Math.min(width, height);                           //设置半径为宽或者高的最小值
        //paint bg
        mTextPain.setColor(Color.parseColor(getRandomColor()));     //设置画笔颜色为随机颜色
        canvas.drawCircle(width, height, radius, mTextPain);        //利用canvas画一个圆

        //paint font
        mTextPain.setColor(Color.WHITE);                            //设置画笔白颜色
        mTextPain.setTextSize(dp2px(16));                           //设置字体大小为16dp
        Paint.FontMetrics fontMetrics = mTextPain.getFontMetrics(); //获取字体测量对象
        canvas.drawText(mText, 0, mText.length(), radius            //利用canvas画上字
            , radius + Math.abs(fontMetrics.top + fontMetrics.bottom) / 2, mTextPain);
    }

    /**
     * 给View设置文字
     * @param str
     */
    public void setText(String str) {
        if(!TextUtils.isEmpty(str)){
            if(str.length()>1){
                mText = str.substring(0,1);
            }else {
                mText = str;
            }
        }else {
            mText =  "";
        }
        invalidate();
    }

    /**
     * dp转px
     *
     * @param dp
     * @return
     */
    public int dp2px(int dp) {
        // px = dp * (dpi / 160)
        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        int dpi = metrics.densityDpi;
        return (int) (dp * (dpi / 160f) + 0.5f);
    }



    /**
     * 获取随机颜色
     *
     * @return
     */
    private String getRandomColor() {
        List<String> colorList = new ArrayList<String>();
        colorList.add("#303F9F");
        colorList.add("#FF4081");
        colorList.add("#59dbe0");
        colorList.add("#f57f68");
        colorList.add("#f8b552");
        colorList.add("#990099");
        colorList.add("#90a4ae");
        colorList.add("#7baaf7");
        colorList.add("#4dd0e1");
        colorList.add("#4db6ac");
        colorList.add("#aed581");
        colorList.add("#fdd835");
        colorList.add("#f2a600");
        colorList.add("#ff8a65");
        colorList.add("#f48fb1");
        colorList.add("#7986cb");
        colorList.add("#DEB887");
        colorList.add("#FF69B4");
        return colorList.get((int) (Math.random() * colorList.size()));
    }
}
