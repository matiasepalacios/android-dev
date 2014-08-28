package ues21.ejerciciosfeedback.ues21ejercicofeedback1;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class TravelListActivity extends ListActivity implements
		TravelItemsInterface {

	public static final int REQUEST_CODE_NEW_CITY = 10;
	public static final int REQUEST_CODE_EDIT_CITY = 20;
	private static final int _INIT = 1;
	private static final int _RELOAD = 2;
	private SimpleCursorAdapter adapter = null;

	private static final String[] PROJECTION = { _ID, NAME, COUNTRY, YEAR,
			COMMENTS };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.loadList(_INIT);

		registerForContextMenu(getListView());
		setListAdapter(this.adapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.travel_list_activity_menu, menu);
		return true;

	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
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
				String name = data.getExtras().getString(NAME);
				String country = data.getExtras().getString(COUNTRY);
				int year = data.getExtras().getInt(YEAR);

				ContentValues values = new ContentValues();
				values.put(NAME, name);
				values.put(COUNTRY, country);
				values.put(YEAR, year);

				if (data.hasExtra(COMMENTS)) {

					String comments = data.getExtras().getString(COMMENTS);
					values.put(COMMENTS, comments);

				}

				if (requestCode == REQUEST_CODE_EDIT_CITY) {

					// TODO: code to edit

					Uri uri = Uri.parse(TravelsProvider.CONTENT_URI + "/"
							+ data.getExtras().getInt(ITEM_ID));
					getContentResolver().update(uri, values, null, null);
				} else {

					getContentResolver().insert(TravelsProvider.CONTENT_URI,
							values);
				}

			}
		} catch (Exception e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}

		this.loadList(_RELOAD);
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
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
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

	private void deleteItem(long id) {
		Uri uri = Uri.parse(TravelsProvider.CONTENT_URI + "/" + id);
		getContentResolver().delete(uri, null, null);
		this.loadList(_RELOAD);
	}

	private void editItem(long id) {

		Cursor item = this.getTravel((int) id);

		if (item != null) {
			item.moveToFirst();
			Intent intent = new Intent(this, EditTravelActivity.class);
			intent.putExtra(NAME,
					item.getString(item.getColumnIndexOrThrow(NAME)));
			intent.putExtra(YEAR,
					item.getInt(item.getColumnIndexOrThrow(YEAR)));
			intent.putExtra(COUNTRY,
					item.getString(item.getColumnIndexOrThrow(COUNTRY)));
			intent.putExtra(COMMENTS,
					item.getString(item.getColumnIndexOrThrow(COMMENTS)));
			intent.putExtra(ITEM_ID, (int) id);
			startActivityForResult(intent, REQUEST_CODE_EDIT_CITY);
		}

	}

	private void loadList(int init) {
		ContentResolver cr = getContentResolver();

		Cursor c = cr.query(TravelsProvider.CONTENT_URI, PROJECTION, null,
				null, null);

		String[] from = new String[] { TravelsDatabaseHelper.NAME };
		int[] to = new int[] { android.R.id.text1 };

		switch (init) {
		case _INIT:
			this.adapter = new TravelsCursorAdapter(this,
					android.R.layout.simple_list_item_2, c, from, to, 0);
		case _RELOAD:
			this.adapter.changeCursor(c);
		}

	}

	private Cursor getTravel(int id) {
		Uri uri = Uri.parse(TravelsProvider.CONTENT_URI + "/" + id);

		Cursor item = getContentResolver().query(uri, PROJECTION, null, null,
				null);

		return item;
	}

}
