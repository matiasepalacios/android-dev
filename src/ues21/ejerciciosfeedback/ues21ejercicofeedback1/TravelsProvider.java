package ues21.ejerciciosfeedback.ues21ejercicofeedback1;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class TravelsProvider extends ContentProvider {

	private TravelsDatabaseHelper mDbHelper;
	private static final String AUTHORITY = "ues21.ejerciciosfeedback.travellist";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
			+ "/travels");
	private static final int URI_TRAVELS = 1;
	private static final int URI_TRAVEL_ITEM = 2;

	private static final UriMatcher mUriMatcher;

	static {
		mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		mUriMatcher.addURI(AUTHORITY, "travels", URI_TRAVELS);
		mUriMatcher.addURI(AUTHORITY, "travels/#", URI_TRAVEL_ITEM);
	}

	@Override
	public boolean onCreate() {
		this.mDbHelper = new TravelsDatabaseHelper(getContext());
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		
		int match = mUriMatcher.match(uri);
		
		// Using SQLiteQueryBuilder instead of query() method
		SQLiteQueryBuilder qBuilder = new SQLiteQueryBuilder();
		
		
		qBuilder.setTables(TravelsDatabaseHelper.TABLE_NAME);
		
		
		switch (match) {
		case URI_TRAVELS:
			break;
		case URI_TRAVEL_ITEM:
			String id = uri.getLastPathSegment();
			qBuilder.appendWhere(Travels._ID + "=" + id);
			break;
		default:
			Log.w("Travel", "Uri didn't match: " + uri);
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}
		

		// get db
		SQLiteDatabase db = this.mDbHelper.getWritableDatabase();
		
		// get cursor
		Cursor c = qBuilder.query(db, projection, selection, selectionArgs,
				null, null, sortOrder);
		
		//return cursor
		return c;
	}

	@Override
	public String getType(Uri uri) {
		int match = mUriMatcher.match(uri);
		switch (match) {
		case URI_TRAVELS:
			return "vnd.android.cursor.dir/vnd." + AUTHORITY + ".travels";
		case URI_TRAVEL_ITEM:
			return "vnd.android.cursor.item/vnd." + AUTHORITY + ".travels";
		default:
			Log.w("Travels", "Uri didn't match: " + uri);
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db = mDbHelper.getWritableDatabase();
		long id = db.insert(TravelsDatabaseHelper.TABLE_NAME, null, values);
		Uri result = null;
		if (id >= 0) {
			result = ContentUris.withAppendedId(CONTENT_URI, id);
			getContext().getContentResolver().notifyChange(uri, null);
		}
		
		return result;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int uriType = mUriMatcher.match(uri);
	    SQLiteDatabase sqlDB = mDbHelper.getWritableDatabase();
	    int rowsDeleted = 0;
	    switch (uriType) {
	    case URI_TRAVELS:
	      rowsDeleted = sqlDB.delete(TravelsDatabaseHelper.TABLE_NAME, selection,
	          selectionArgs);
	      break;
	    case URI_TRAVEL_ITEM:
	      String id = uri.getLastPathSegment();
	      if (TextUtils.isEmpty(selection)) {
	        rowsDeleted = sqlDB.delete(TravelsDatabaseHelper.TABLE_NAME,
	        		TravelsDatabaseHelper._ID + "=" + id, 
	            null);
	      } else {
	        rowsDeleted = sqlDB.delete(TravelsDatabaseHelper.TABLE_NAME,
	        		TravelsDatabaseHelper._ID + "=" + id 
	            + " and " + selection,
	            selectionArgs);
	      }
	      break;
	    default:
	      throw new IllegalArgumentException("Unknown URI: " + uri);
	    }
	    getContext().getContentResolver().notifyChange(uri, null);
	    return rowsDeleted;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		
		int uriType = mUriMatcher.match(uri);
	    SQLiteDatabase sqlDB = mDbHelper.getWritableDatabase();
	    int rowsUpdated = 0;
	    switch (uriType) {
	    case URI_TRAVELS:
	      rowsUpdated = sqlDB.update(TravelsDatabaseHelper.TABLE_NAME, 
	          values, 
	          selection,
	          selectionArgs);
	      break;
	    case URI_TRAVEL_ITEM:
	      String id = uri.getLastPathSegment();
	      if (TextUtils.isEmpty(selection)) {
	        rowsUpdated = sqlDB.update(TravelsDatabaseHelper.TABLE_NAME, 
	            values,
	            TravelsDatabaseHelper._ID + "=" + id, 
	            null);
	      } else {
	        rowsUpdated = sqlDB.update(TravelsDatabaseHelper.TABLE_NAME, 
	            values,
	            TravelsDatabaseHelper._ID + "=" + id 
	            + " and " 
	            + selection,
	            selectionArgs);
	      }
	      break;
	    default:
	      throw new IllegalArgumentException("Unknown URI: " + uri);
	    }
	    getContext().getContentResolver().notifyChange(uri, null);
	    return rowsUpdated;
		 
	}

}
