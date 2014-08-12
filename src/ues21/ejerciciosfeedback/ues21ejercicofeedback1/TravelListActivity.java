package ues21.ejerciciosfeedback.ues21ejercicofeedback1;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class TravelListActivity extends ListActivity implements TravelItemsInterface {

	public static final int REQUEST_CODE_NEW_CITY = 10;
	public static final int REQUEST_CODE_EDIT_CITY = 20;
	public static final String ITEM_ID = "ITEM_ID";
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
		registerForContextMenu(getListView());
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
		try {
			if (resultCode == RESULT_OK) {
				if (requestCode == REQUEST_CODE_EDIT_CITY) {
					this.deleteItem(data.getExtras().getLong(ITEM_ID));
				}
				String name = data.getExtras().getString(NAME);
				String country = data.getExtras().getString(COUNTRY);
				int year = Integer.parseInt(data.getExtras().getString(YEAR));
				String comments = data.getExtras().getString(EditTravelActivity.COMMENTS);

				this.adapter.add(new TravelInfo(name,country, year, comments));
				setListAdapter(adapter);
			}
		} catch (Exception e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}


	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.long_click_travel_list_activity_menu, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		switch (item.getItemId()) {
		case R.id.menu_item_delete:
			this.deleteItem(info.id);
			return true;
		case R.id.menu_item_edit_travel:
			this.editItem(info.id);
			return true;
		default:
			return super.onContextItemSelected(item);
		}
	}

	private void deleteItem(long id){		
		this.adapter.remove(this.adapter.getItem((int) id));
		this.adapter.notifyDataSetChanged();
	}

	private void editItem(long id) {
		TravelInfo item = this.adapter.getItem((int) id);
		Intent intent = new Intent(this, EditTravelActivity.class);
		intent.putExtra(NAME, item.getCity());
		intent.putExtra(YEAR, item.getYear());
		intent.putExtra(COUNTRY, item.getCountry());
		intent.putExtra(COMMENTS, item.getComments());
		intent.putExtra(ITEM_ID, id);
		startActivityForResult(intent, REQUEST_CODE_EDIT_CITY);
	}

}
