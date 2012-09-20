package com.xiaobai.test.viewpage;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.xiaobai.test.R;

public class ViewPageDemo extends FragmentActivity {

	FragmentManager manager;
	ViewPageFra feedfragment;


	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.view_page_act);
		manager =  getSupportFragmentManager();
		feedfragment = new ViewPageFra();
		FragmentTransaction fragTrs = manager.beginTransaction();
		fragTrs.replace(R.id.mainfragment_container, feedfragment);
//		fragTrs.addToBackStack(null);
		fragTrs.commit();
	}
	
}
