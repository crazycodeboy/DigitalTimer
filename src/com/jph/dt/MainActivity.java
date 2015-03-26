package com.jph.dt;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener{
    private DigitalTimer dt;
    private DigitalTimer2 dt2;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dt=(DigitalTimer) findViewById(R.id.dt);
        dt2=(DigitalTimer2) findViewById(R.id.dt2);
    }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			dt.setBaseTime(System.currentTimeMillis());
			dt.start();
			dt2.setBaseTime(System.currentTimeMillis());
			dt2.start();
			break;
		case R.id.button2:
			dt.stop();
			dt2.stop();
			break;
		default:
			break;
		}
	}
}
