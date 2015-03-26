package com.jph.dt;

import java.util.Calendar;
import java.util.TimeZone;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DigitalTimer extends LinearLayout {
	private TextView h1;
	private TextView h2;
	private TextView hc;
	private TextView m1;
	private TextView m2;
	private TextView mc;
	private TextView s1;
	private TextView s2;
	private TextView sc;
	/**基准时间**/
	private long baseTime;
	/**改变的时间**/
	private long changTime;
	private static final int REFRESH_DELAY = 1000;
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
		init(context);
	}
	private void init(Context context){
		this.setOrientation(LinearLayout.HORIZONTAL);
		h1 = new TextView(context);
		h2 = new TextView(context);
		hc = new TextView(context);
		m1 = new TextView(context);
		m2 = new TextView(context);
		mc = new TextView(context);
		s1 = new TextView(context);
		s2 = new TextView(context);
		sc = new TextView(context);
		//设置背景
//		h1.setBackgroundResource(R.drawable.digitalclock_selector);
//		m2.setBackgroundResource(R.drawable.digitalclock_selector);
		//设置字颜色
		h1.setTextColor(Color.RED);
		h2.setTextColor(Color.RED);
		m1.setTextColor(Color.RED);
		m2.setTextColor(Color.RED);
		s1.setTextColor(Color.RED);
		s2.setTextColor(Color.RED);
		//设置字内容
		h1.setText("0");
		h2.setText("0");
		hc.setText(":");
		m1.setText("0");
		m2.setText("0");
		mc.setText(":");
		s1.setText("0");
		s2.setText("0");
		//设置view宽高
		LayoutParams lp = new LayoutParams(20,LayoutParams.WRAP_CONTENT, 1);  // 1是可选写的
		lp.setMargins(2,0, 2, 0);
		h1.setLayoutParams(lp);
		h2.setLayoutParams(lp);
		m1.setLayoutParams(lp);
		m2.setLayoutParams(lp);
		s1.setLayoutParams(lp);
		s2.setLayoutParams(lp);
		//设置内容居中
		h1.setGravity(Gravity.CENTER);
		h2.setGravity(Gravity.CENTER);
		hc.setGravity(Gravity.CENTER);
		m1.setGravity(Gravity.CENTER);
		m2.setGravity(Gravity.CENTER);
		s1.setGravity(Gravity.CENTER);
		s2.setGravity(Gravity.CENTER);
		sc.setGravity(Gravity.CENTER);
		//添加view
		this.addView(h1);
		this.addView(h2);
		this.addView(hc);
		this.addView(m1);
		this.addView(m2);
		this.addView(mc);
		this.addView(s1);
		this.addView(s2);
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
}
