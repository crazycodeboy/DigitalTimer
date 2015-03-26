package com.jph.dt;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.format.Time;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 自定时计时控件
 * @author JPH
 * @date 2015-1-30 上午10:36:12
 */
public class DigitalTimer2 extends LinearLayout {
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
	private CountDownTimer countDownTimer;
	private Time time;
	private float textSize;
	private int textColor;
	private int textBgRes;
	/** 用于显示文字的控件的集合**/
	private ArrayList<TextView>textViews;
	public DigitalTimer2(Context context) {
		super(context);
		init(context);
	}
	public DigitalTimer2(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DigitalTimer);
		textSize = a.getDimension(R.styleable.DigitalTimer_textSize, DEFAULT_TEXT_SIZE);
        textColor = a.getColor(R.styleable.DigitalTimer_textColor, DEFAULT_TEXT_COLOR);
        textBgRes = a.getResourceId(R.styleable.DigitalTimer_textBgRes, DEFAULT_TEXT_BG);
        a.recycle();
		init(context);
	}
	private void init(Context context){
		countDownTimer=new CountDownTimer(999999999,1000) {
			@Override
			public void onTick(long millisUntilFinished) {
				changTime=System.currentTimeMillis()-baseTime+1000;
				reSetTime();
			}
			@Override
			public void onFinish() {
			}
		};
		time=new Time("GMT+0");
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
		countDownTimer.cancel();
		countDownTimer.start();
	}
	/**
	 * 停止计时
	 */
	public void stop(){
		countDownTimer.cancel();
	}
	/**
	 * 重置时间
	 */
	private void reSetTime() {
		time.set(changTime);
		String hour=String.valueOf(time.hour);
		String minute=String.valueOf(time.minute);
		String second=String.valueOf(time.second);
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
	public int getTextBg() {
		return textBgRes;
	}
	public void setTextBg(int textBg) {
		this.textBgRes = textBg;
	}
}
