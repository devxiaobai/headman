package com.xiaobai.test.viewpage;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaobai.test.R;

public class ViewPageFra extends Fragment {
	List<Fragment> fragments;
	ViewPager viewPager;
	private View view;
	BaseFragmentPagerAdapter fragmentadapter;
	 TabPageIndicator mIndicator;
	private static final String[] CONTENT = new String[] { "动态", "二度好友" };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.view_page_fra, container,false);
		initViews();
		return view;
	}

	private void initViews() {
		fragments = new ArrayList<Fragment>();
		
		fragments.add(new EmptyFragment(Color.WHITE));
		fragments.add(new EmptyFragment(Color.GREEN));

		fragmentadapter = new Main_page_Adapter(getActivity()
				.getSupportFragmentManager(), fragments);
		viewPager = (ViewPager) view.findViewById(R.id.main_pager);
		viewPager.setAdapter(fragmentadapter);
		
		 mIndicator = (TabPageIndicator) view.findViewById(R.id.mainpage_indicator);
		 mIndicator.setViewPager(viewPager);
	}

	class Main_page_Adapter extends BaseFragmentPagerAdapter {
		public Main_page_Adapter(FragmentManager fm, List<Fragment> fragments) {
			super(fm, fragments);
		}

		@Override
		public Fragment getItem(int position) {
			return fragments.get(position);
		}

		@Override
		public int getCount() {
			return ViewPageFra.CONTENT.length;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return ViewPageFra.CONTENT[position % ViewPageFra.CONTENT.length]
					.toUpperCase();
		}
	}

}
