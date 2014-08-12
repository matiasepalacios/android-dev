package ues21.ejerciciosfeedback.ues21ejercicofeedback1;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class TravelListActivity extends ListActivity {
	
	public static final int REQUEST_CODE_NEW_CITY = 100;
	private TravelAdapter adapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ArrayList<TravelInfo> travels = new ArrayList<TravelInfo>();
		
		travels.add(new TravelInfo("Roma","Italia", 2000));
		travels.add(new TravelInfo("Ciudad del Cabo","Sudafrica", 2002, "Mucho Calor"));
		travels.add(new TravelInfo("San Luis","Argentina", 2010, "no volver en invierno NUNCA MAS"));
		travels.add(new TravelInfo("Austin","USA", 2012, "Recordar visitar los Outlets Nuevamente"));
		
		this.adapter = new TravelAdapter(this, travels);
		setListAdapter(adapter);
		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.travel_list_activity_menu, menu);
		return true;
		
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()){
		case R.id.menu_new_travel:
			Intent intent = new Intent(this, EditTravelActivity.class);
			startActivityForResult(intent, REQUEST_CODE_NEW_CITY);
			break;
		}
		
		return super.onMenuItemSelected(featureId, item);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		String name = data.getExtras().getString(EditTravelActivity.NAME);
		String country = data.getExtras().getString(EditTravelActivity.COUNTRY);
		int year = Integer.parseInt(data.getExtras().getString(EditTravelActivity.YEAR));
		String comments = data.getExtras().getString(EditTravelActivity.COMMENTS);
		
		this.adapter.add(new TravelInfo(name,country, year, comments));
		setListAdapter(adapter);
	}
	
}
