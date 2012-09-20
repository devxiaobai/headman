package com.xiaobai.test;

import com.xiaobai.test.headman.ManagermentActivity;
import com.xiaobai.test.scroll.ScrollViewActivity;
import com.xiaobai.test.viewpage.ViewPageDemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class DemoActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo);
		findViewById(R.id.headMan).setOnClickListener(this);
		findViewById(R.id.scroll).setOnClickListener(this);
		findViewById(R.id.viewpageBtn).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch(v.getId()){
		case R.id.headMan:
			intent.setClass(this, ManagermentActivity.class);
			break;
		case R.id.scroll:
			intent.setClass(this,ScrollViewActivity.class);
			break;
		case R.id.viewpageBtn:
			intent.setClass(this, ViewPageDemo.class);
			break;
		}
		startActivity(intent);
	}
}
