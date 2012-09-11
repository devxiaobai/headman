package com.xiaobai.test;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ManagerFragment extends Fragment {
	private Integer[] mThumbIds = { R.drawable.head, R.drawable.head,
			R.drawable.head, R.drawable.head };
	GridView grid;
	boolean removeState = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.management_fra, null);
		grid = (GridView) view.findViewById(R.id.myGrid);
		ArrayList<HeadName> data = new ArrayList<HeadName>();
		for (int i = 0; i < mThumbIds.length; i++) {
			data.add(new HeadName(mThumbIds[i], "姓名" + i));
		}
		grid.setAdapter(new ImageAdapter(getActivity(), data, 4));

		grid.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				ImageAdapter adapter = (ImageAdapter) grid.getAdapter();
				if (arg2 < adapter.getSize()) {
					removeState = true;
					adapter.notifyDataSetChanged();
				}
				return true;
			}
		});
		grid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Toast.makeText(getActivity(), "onItemClcik", Toast.LENGTH_SHORT)
						.show();
				ImageAdapter adapter = (ImageAdapter) grid.getAdapter();
				if (removeState) {
					if (arg2 >= adapter.getSize()) {
						removeState = !removeState;
						adapter.notifyDataSetChanged();
					} else
						adapter.remove(arg2);

				} else { // 非删除状态点击事件
					if(arg2==adapter.getAddFriendPosition()){
						adapter.add();
					}
				}
			}
		});

		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	public class ImageAdapter extends BaseAdapter {
		private int headInOneRow;
		private int allItemCount;
		private int dataSize;
		private ArrayList<HeadName> data;

		private void addBlankItem() {
			data.add(new HeadName(R.drawable.head, null));
		}

		private int calculateAllDataCount() {
			return headInOneRow * ((dataSize + headInOneRow) / headInOneRow);
		}

		private void populateData() { // 计算
			allItemCount = calculateAllDataCount();
			data.add(new HeadName(R.drawable.picture_add, null));// add friend
			for (int i = data.size(); i < allItemCount; i++) {// 空白
				addBlankItem();
			}
		}
		
		public int getAddFriendPosition(){
			return dataSize;
		}

		public void remove(int position) {
			data.remove(position);
			dataSize--;
			int newItemsCount = calculateAllDataCount();
			if (allItemCount == newItemsCount) {
				addBlankItem();
			} else {
				for (int i = data.size() - 1, count = 0; i >= 0
						&& count < headInOneRow-1; count++, i--) {
					data.remove(i);
				}
			}
			allItemCount = newItemsCount;
			this.notifyDataSetChanged();
		}

		public void add() {
			data.add(dataSize, new HeadName(R.drawable.head, "姓名"));
			dataSize++;
			int newItemsCount = calculateAllDataCount();
			if (allItemCount == newItemsCount) {
				data.remove(data.size() - 1);
			} else {
				for (int i = 0; i < headInOneRow-1; i++) {
					addBlankItem();
				}
			}
			allItemCount = newItemsCount;
			this.notifyDataSetChanged();
		}

		public int getSize() {
			return dataSize;
		}

		public ImageAdapter(Context c, ArrayList<HeadName> data,
				int headInOneRow) {
			mContext = c;
			this.headInOneRow = headInOneRow;
			this.dataSize = data.size();
			this.data = data;
			populateData();
		}

		public int getCount() {
			return data.size();
		}

		public Object getItem(int position) {
			return data.get(position);
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				LayoutInflater inflater = (LayoutInflater) getActivity()
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(R.layout.manage_grid_item, null);
			}
			ImageView imageView = (ImageView) convertView
					.findViewById(R.id.grid_item_image);
			TextView textView = (TextView) convertView
					.findViewById(R.id.grid_item_label);
			View deleteImg = convertView.findViewById(R.id.deleteLayout);

			HeadName item = data.get(position);
			imageView.setAdjustViewBounds(false);
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setImageResource(item.getHeadId());

			if (position <= dataSize) {

				imageView.setVisibility(View.VISIBLE);
				textView.setVisibility(View.VISIBLE);
				textView.setText(item.getName());
				if (removeState) {
					// Display delete icon
					deleteImg.setVisibility(View.VISIBLE);

					// remove add friend icon from the gridview
					if (item.getHeadId() == R.drawable.picture_add) {
						imageView.setVisibility(View.INVISIBLE);
						textView.setVisibility(View.INVISIBLE);
						deleteImg.setVisibility(View.INVISIBLE);
					}
				} else {
					deleteImg.setVisibility(View.INVISIBLE);
					convertView.setVisibility(View.VISIBLE);
				}
			} else {
				imageView.setVisibility(View.INVISIBLE);
				textView.setVisibility(View.INVISIBLE);
				deleteImg.setVisibility(View.INVISIBLE);
			}
			return convertView;
		}

		private Context mContext;

	}

	class HeadName {

		int headId;

		String name;

		HeadName(int id, String name) {
			headId = id;
			this.name = name;
		}

		public int getHeadId() {
			return headId;
		}

		public String getName() {
			return name;
		}

	}
}
