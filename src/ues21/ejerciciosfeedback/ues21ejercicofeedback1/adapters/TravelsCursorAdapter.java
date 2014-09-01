package ues21.ejerciciosfeedback.ues21ejercicofeedback1.adapters;

import ues21.ejerciciosfeedback.ues21ejercicofeedback1.adapters.TravelAdapter.ViewHolder;
import ues21.ejerciciosfeedback.ues21ejercicofeedback1.helpers.TravelsDatabaseHelper;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public final class TravelsCursorAdapter extends SimpleCursorAdapter {

	private LayoutInflater mInflater;

	public TravelsCursorAdapter(Context context, int layout, Cursor c,
			String[] from, int[] to, int flags) {
		super(context, layout, c, from, to, flags);
		// TODO Auto-generated constructor stub
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		View view = mInflater.inflate(android.R.layout.simple_list_item_2,
				parent, false);
		ViewHolder holder = new ViewHolder();
		TextView text1 = (TextView) view.findViewById(android.R.id.text1);
		TextView text2 = (TextView) view.findViewById(android.R.id.text2);
		holder.text1 = text1;
		holder.text2 = text2;
		view.setTag(holder);
		return view;
	}

	@Override
	public void bindView(View v, Context context, Cursor c) {
		ViewHolder holder = (ViewHolder) v.getTag();
		String name = c.getString(c.getColumnIndex(TravelsDatabaseHelper.NAME));
		String country = c.getString(c
				.getColumnIndex(TravelsDatabaseHelper.COUNTRY));
		int year = c.getInt(c.getColumnIndex(TravelsDatabaseHelper.YEAR));
		
		
		String text2 = country + " | " + year;
		holder.text1.setText(name);
		holder.text2.setText(text2);

	}

}
