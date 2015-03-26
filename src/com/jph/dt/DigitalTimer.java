package com.jph.dt;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DigitalTimer extends LinearLayout {
	private static final float DEFAULT_TEXT_SIZE = 12;
    private static final int DEFAULT_TEXT_COLOR = Color.WHITE;
    private static final int DEFAULT_TEXT_BG = Color.parseColor("#969696");
	private TextView h1;
	private TextView h2;
	private TextView hc;
	private TextView m1;
	private TextView m2;
	private TextView mc;
	private TextView s1;
	private TextView s2;
	/**基准时间**/
	private long baseTime;
	/**改变的时间**/
	private long changTime;
	private float textSize;
	private int textColor;
	private int textBgRes;
	private static final int REFRESH_DELAY = 1000;
	/** 用于显示文字的控件的集合**/
	private ArrayList<TextView>textViews;
	private final Handler mHandler = new Handler();
	private final Runnable mTimeRefresher = new Runnable() {
		@Override
		public void run() {
			changTime+=1000;
			reSetTime();
			mHandler.postDelayed(this, REFRESH_DELAY);
		}
	};
	public DigitalTimer(Context context) {
		super(context);
		init(context);
	}
	public DigitalTimer(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DigitalTimer);
		textSize = a.getDimension(R.styleable.DigitalTimer_textSize, DEFAULT_TEXT_SIZE);
        textColor = a.getColor(R.styleable.DigitalTimer_textColor, DEFAULT_TEXT_COLOR);
        textBgRes = a.getResourceId(R.styleable.DigitalTimer_textBgRes, DEFAULT_TEXT_BG);
        a.recycle();
		init(context);
	}
	private void init(Context context){
		this.setOrientation(LinearLayout.HORIZONTAL);
		textViews=new ArrayList<TextView>();
		textViews.add(h1 = new TextView(context));
		textViews.add(h2 = new TextView(context));
		textViews.add(hc = new TextView(context));
		textViews.add(m1 = new TextView(context));
		textViews.add(m2 = new TextView(context));
		textViews.add(mc = new TextView(context));
		textViews.add(s1 = new TextView(context));
		textViews.add(s2 = new TextView(context));
		
		//设置view宽高
		LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT, 0);
		lp.setMargins(2,0, 2, 0);
		h1.setLayoutParams(lp);
		h2.setLayoutParams(lp);
		m1.setLayoutParams(lp);
		m2.setLayoutParams(lp);
		s1.setLayoutParams(lp);
		s2.setLayoutParams(lp);
		for(TextView view:textViews){
			view.setPadding(1, 0, 1, 0);
			view.setText("0");//设置字内容
			view.setBackgroundResource(textBgRes);//设置背景
			view.setTextColor(textColor);//设置字颜色
			view.setTextSize(textSize);
			view.setGravity(Gravity.CENTER);//设置内容居中
			this.addView(view);//添加view
		}
		hc.setText(":");
		hc.setBackgroundColor(Color.TRANSPARENT);
		mc.setText(":");
		mc.setBackgroundColor(Color.TRANSPARENT);
	}
	/**
	 * 设置基准时间
	 * @param baseTime
	 */
	public void setBaseTime(long baseTime) {
		this.baseTime=baseTime;
	}
	/**
	 * 开始计时
	 */
	public void start() {
		changTime+=(System.currentTimeMillis()-baseTime);//计算计时开始时间
		mHandler.post(mTimeRefresher);
	}
	/**
	 * 停止计时
	 */
	public void stop(){
		mHandler.removeCallbacks(mTimeRefresher);
	}
	/**
	 * 重置时间
	 */
	private void reSetTime() {
		Calendar calendar=Calendar.getInstance(TimeZone.getTimeZone("GMT+0"));
		calendar.setTimeInMillis(changTime);
		String hour=String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
		String minute=String.valueOf(calendar.get(Calendar.MINUTE));
		String second=String.valueOf(calendar.get(Calendar.SECOND));
		setText(hour,h1,h2);
		setText(minute,m1,m2);
		setText(second,s1,s2);
	}
	/**
	 * 将相应的时间设置的控件上去
	 * @param text
	 * @param textView1
	 * @param textView2
	 */
	private void setText(String text,TextView textView1,TextView textView2) {
		if (text.length()==2) {
			textView1.setText(text.substring(0, 1));
			textView2.setText(text.substring(1, 2));
		}else {
			textView1.setText("0");
			textView2.setText(text);
		}
	}
	public float getTextSize() {
		return textSize;
	}
	public void setTextSize(float textSize) {
		this.textSize = textSize;
	}
	public int getTextColor() {
		return textColor;
	}
	public void setTextColor(int textColor) {
		this.textColor = textColor;
	}
	public int getTextBgRes() {
		return textBgRes;
	}
	public void setTextBgRes(int textBgRes) {
		this.textBgRes = textBgRes;
	}
}
