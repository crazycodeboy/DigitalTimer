package com.jph.dt;

import android.content.Context;
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
public class CopyOfDigitalTimer2 extends LinearLayout {
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
	private CountDownTimer countDownTimer;
	private Time time;
	public CopyOfDigitalTimer2(Context context) {
		super(context);
		init(context);
	}
	public CopyOfDigitalTimer2(Context context, AttributeSet attrs) {
		super(context, attrs);
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
		h1.setBackgroundResource(R.drawable.digitalclock_selector);
		h2.setBackgroundResource(R.drawable.digitalclock_selector);
		m1.setBackgroundResource(R.drawable.digitalclock_selector);
		m2.setBackgroundResource(R.drawable.digitalclock_selector);
		s1.setBackgroundResource(R.drawable.digitalclock_selector);
		s2.setBackgroundResource(R.drawable.digitalclock_selector);
		//设置字颜色
		h1.setTextColor(Color.WHITE);
		h2.setTextColor(Color.WHITE);
		m1.setTextColor(Color.WHITE);
		m2.setTextColor(Color.WHITE);
		s1.setTextColor(Color.WHITE);
		s2.setTextColor(Color.WHITE);
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
		LayoutParams lp = new LayoutParams(20,LayoutParams.WRAP_CONTENT, 1);
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
}
