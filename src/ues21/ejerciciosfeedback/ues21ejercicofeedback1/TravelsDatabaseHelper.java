/**
 * 
 */
package ues21.ejerciciosfeedback.ues21ejercicofeedback1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author matiaspalacios
 * 
 */
public class TravelsDatabaseHelper extends SQLiteOpenHelper implements
		TravelItemsInterface {

	public final static String TABLE_NAME = "Travels";
	public final static String DATABASE_NAME = "Travel_app.db";
	private final static int DATABASE_VERSION = 3;

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

		_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + " TEXT NOT NULL,"
				+ COUNTRY + " TEXT NOT NULL, " + YEAR +" INTEGER NOT NULL, "
				+ COMMENTS + " TEXT " + ");");

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

}
