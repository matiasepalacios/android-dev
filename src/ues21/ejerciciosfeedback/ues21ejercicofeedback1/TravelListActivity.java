package ues21.ejerciciosfeedback.ues21ejercicofeedback1;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class TravelListActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/*
		 * getting the intent and doing stuff with it
		 */
		Intent intent = getIntent();
		String matt = intent.getExtras().getString(EditTravelActivity.NAME);
		
		
		Toast.makeText(TravelListActivity.this, matt,
				Toast.LENGTH_SHORT).show();
		
		ArrayList<TravelInfo> travels = new ArrayList<TravelInfo>();
		
		travels.add(new TravelInfo("Roma","Italia", 2000));
		travels.add(new TravelInfo("Ciudad del Cabo","Sudafrica", 2002, "Mucho Calor"));
		travels.add(new TravelInfo("San Luis","Argentina", 2010, "no volver en invierno NUNCA MAS"));
		travels.add(new TravelInfo("Austin","USA", 2012, "Recordar visitar los Outlets Nuevamente"));
		
		TravelAdapter adapter = new TravelAdapter(this, travels);
		setListAdapter(adapter);
		
	}
	
	
}
