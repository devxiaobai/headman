/**
 * ceshi_Fragment
 * @Author Deli.yang
 * @Email Deli.yang@renren-inc.com
 */
package com.xiaobai.test.viewpage;

//import android.app.Fragment;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaobai.test.R;

@SuppressLint("NewApi")
public class EmptyFragment extends Fragment {
	
	private int color;
	
	public EmptyFragment(int color) {
		this.color = color;
	}
	public EmptyFragment() {
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceBundle) {
		View view = inflater.inflate(R.layout.view_page_empity_fra, container, false);
		view.setBackgroundColor(color);
		return view;
	}
}
