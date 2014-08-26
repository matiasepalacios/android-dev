/**
 * 
 */
package ues21.ejerciciosfeedback.ues21ejercicofeedback1;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author matiaspalacios
 * 
 */
public class TravelsDatabaseHelper extends SQLiteOpenHelper implements
TravelItemsInterface {

	private final static String TABLE_NAME = "Travels";
	private final static String DATABASE_NAME = "Travel_app";
	private final static int DATABASE_VERSION = 1;
	public static SQLiteDatabase db = null;

	/**
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 */
	public TravelsDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite
	 * .SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +

		_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + " TEXT NOT NULL, "
		+ COUNTRY + " TEXT NOT NULL, " + YEAR + " INTEGER NOT NULL, "
		+ COMMENTS + " TEXT " + ");");
		TravelsDatabaseHelper.db = db;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite
	 * .SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		if (oldVersion < newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + ";");
			onCreate(db);
		}

	}

	public void insertTravel(SQLiteDatabase db, String city, String country,
			int year, String note) {
		ContentValues values = new ContentValues();
		values.put(NAME, city);
		values.put(COUNTRY, country);
		values.put(YEAR, year);
		values.put(COMMENTS, note);
		db.insert(TABLE_NAME, null, values);

	}

	public ArrayList<TravelInfo> getTravelsList() {
		ArrayList<TravelInfo> travels = new ArrayList<TravelInfo>();
		SQLiteDatabase db = getReadableDatabase();
		Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
		if (c.moveToFirst()) {
			int cityIndex = c.getColumnIndex(NAME);
			int countryIndex = c.getColumnIndex(COUNTRY);
			int yearIndex = c.getColumnIndex(YEAR);
			int noteIndex = c.getColumnIndex(COMMENTS);
			do {
				String city = c.getString(cityIndex);
				String country = c.getString(countryIndex);
				int year = c.getInt(yearIndex);
				String note = c.getString(noteIndex);
				TravelInfo travel = new TravelInfo(city, country, year, note);
				travels.add(travel);
			} while (c.moveToNext());

		}
		return travels;
	}

}
