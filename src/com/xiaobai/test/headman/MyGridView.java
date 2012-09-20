package com.xiaobai.test.headman;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

/**
 * This class is inherit from GridView, overriding onMeasure method to 
 * remove scroll function in this gridview , and to avoid only one row 
 * displayed when embedded in a ScrollView control.
 * 
* @author yingjun.bai@renren-inc.com
* @date 2012-8-27 上午11:03:26
 */
public class MyGridView extends GridView {
	public MyGridView(Context context) {
		super(context);
	}

	public MyGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override  
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {  
	    int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,  
	            MeasureSpec.AT_MOST);  
	    super.onMeasure(widthMeasureSpec, expandSpec);  
	  
	}  
	
	// 禁止Grid滑动
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if(ev.getAction() == MotionEvent.ACTION_MOVE)
			return true;
		return super.dispatchTouchEvent(ev);
	}
}
