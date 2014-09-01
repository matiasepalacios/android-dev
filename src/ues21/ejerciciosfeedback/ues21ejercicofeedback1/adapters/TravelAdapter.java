/**
 * 
 */
package ues21.ejerciciosfeedback.ues21ejercicofeedback1.adapters;

import java.util.ArrayList;

import ues21.ejerciciosfeedback.ues21ejercicofeedback1.R;
import ues21.ejerciciosfeedback.ues21ejercicofeedback1.TravelInfo;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author matiaspalacios
 *
 */
public class TravelAdapter extends ArrayAdapter<TravelInfo> {

	static class ViewHolder {
		
		TextView text1;
		TextView text2;

	}

	private Context context;
	private ArrayList<TravelInfo> travels;
	
	public TravelAdapter(Context context,
			ArrayList<TravelInfo> travels) {
		super(context, android.R.layout.simple_list_item_2, travels);
		
		this.context = context;
		this.travels = travels;

		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LinearLayout view;
		ViewHolder holder;
		
		if (convertView == null){
			view = new LinearLayout(context);
			
			LayoutInflater inflater = (LayoutInflater) context.
					getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			inflater.inflate(android.R.layout.simple_list_item_2, view, true);
			
			holder = new ViewHolder();
			holder.text1 = (TextView) view.findViewById(android.R.id.text1);
			holder.text2 = (TextView) view.findViewById(android.R.id.text2);
			view.setTag(holder);
			
		} else {
			view =  (LinearLayout) convertView;
			holder = (ViewHolder) view.getTag();
		}
		
		TravelInfo info = travels.get(position);
		holder.text1.setText(info.getCity() +
				" (" + info.getCountry() + ")");
		
		String textTwo = context.getResources().getString(R.string.year) + 
				" " + info.getYear();
		
		holder.text2.setText((String) textTwo);
		
		if (info.getComments() != null){
			holder.text2.setText((String) textTwo  + " | " +
				context.getResources().getString(R.string.comments) + " " + 
				info.getComments());
		}
		
		return view;
	}

}
