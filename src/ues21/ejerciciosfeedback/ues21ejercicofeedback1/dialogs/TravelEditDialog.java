package ues21.ejerciciosfeedback.ues21ejercicofeedback1.dialogs;

import java.util.HashMap;

import ues21.ejerciciosfeedback.ues21ejercicofeedback1.R;
import ues21.ejerciciosfeedback.ues21ejercicofeedback1.activities.TravelListActivity;
import ues21.ejerciciosfeedback.ues21ejercicofeedback1.interfaces.TravelItemsInterface;
import ues21.ejerciciosfeedback.ues21ejercicofeedback1.providers.TravelsProvider;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TravelEditDialog implements TravelItemsInterface {

	public TravelEditDialog() {
		return;
	}

	public void createNewTravel(final Context context) {
		final Dialog dialog = new Dialog(context);
		dialog.setContentView(R.layout.linear_layout_edit_travel_activity);
		dialog.setTitle(R.string.new_visit_text);
		Button cancelButton = (Button) dialog.findViewById(R.id.button_cancel);
		Button sendButton = (Button) dialog.findViewById(R.id.button_send);
		// if button is clicked, close the custom dialog
		cancelButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		sendButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (validateFields(dialog, context)) {
					String textCity = getTextCity(dialog);
					int textYear = getTextYear(dialog);
					String textCountry = getTextCountry(dialog);
					String textComments = getTextComments(dialog);

					ContentValues values = new ContentValues();
					values.put(NAME, textCity);
					values.put(COUNTRY, textCountry);
					values.put(YEAR, textYear);
					values.put(COMMENTS, textComments);
					context.getContentResolver().insert(
							TravelsProvider.CONTENT_URI, values);
					dialog.dismiss();

					((TravelListActivity) context).reload();
				}

			}
		});

		dialog.show();
	}
	
	public void editTravel(final Context context, final HashMap<String, String> data) {
		final Dialog dialog = new Dialog(context);
		dialog.setContentView(R.layout.linear_layout_edit_travel_activity);
		dialog.setTitle(R.string.new_visit_text);
		Button cancelButton = (Button) dialog.findViewById(R.id.button_cancel);
		Button sendButton = (Button) dialog.findViewById(R.id.button_send);
		this.putEditInfo(data, dialog);
		
		// if button is clicked, close the custom dialog
		cancelButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		sendButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (validateFields(dialog, context)) {
					String textCity = getTextCity(dialog);
					int textYear = getTextYear(dialog);
					String textCountry = getTextCountry(dialog);
					String textComments = getTextComments(dialog);

					ContentValues values = new ContentValues();
					values.put(NAME, textCity);
					values.put(COUNTRY, textCountry);
					values.put(YEAR, textYear);
					values.put(COMMENTS, textComments);
					Uri uri = Uri.parse(TravelsProvider.CONTENT_URI + "/"
							+ data.get(ITEM_ID));
					context.getContentResolver().update(uri, values, null, null);
					dialog.dismiss();

					((TravelListActivity) context).reload();
				}

			}
		});

		dialog.show();
	}

	public boolean validateFields(Dialog dialog, Context context) {

		boolean error = false;

		EditText mTextYear = (EditText) dialog.findViewById(R.id.editText_year);
		String textYear = mTextYear.getText().toString();

		try {

			if (this.getTextCity(dialog).trim().equals("")) {

				throw new Exception(
						context.getString(R.string.error_message_empty_city));

			} else if (textYear.trim().equals("")) {

				mTextYear.requestFocus();

				throw new Exception(
						context.getString(R.string.error_message_empty_year));

			} else if (!textYear.trim().equals("")) {

				mTextYear.requestFocus();

				try {

					Integer.parseInt(textYear);

				} catch (NumberFormatException e) {

					throw new Exception(
							context.getString(R.string.error_message_bad_year_format));

				}

				if (this.getTextCountry(dialog).trim().equals("")) {

					throw new Exception(
							context.getString(R.string.error_message_empty_country));

				}

			}

		} catch (Exception e) {
			error = true;
			Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
		}

		return (error == false) ? true : false;
	}
	
	public void putEditInfo(HashMap<String, String> data, Dialog dialog) {
		try {
			String name;
			int year;
			String country;
			String comments;

				name = data.get(NAME);
				EditText mTextCity = (EditText) dialog.findViewById(R.id.editText_city);
				mTextCity.setText(name);

				year = Integer.valueOf(data.get(YEAR));
				EditText mTextYear = (EditText) dialog.findViewById(R.id.editText_year);
				mTextYear.setText(String.valueOf(year));
				
				country = data.get(COUNTRY);
				EditText mTextCountry = (EditText) dialog.findViewById(R.id.editText_country);
				mTextCountry.setText(country);

				comments = data.get(COMMENTS);
				EditText mTextComments = (EditText) dialog.findViewById(R.id.editText_comments);
				mTextComments.setText(comments);

		} catch (Exception e) {
			// TODO: show errors.
		}


	}

	protected String getTextCity(Dialog dialog) {
		EditText mTextCity = (EditText) dialog.findViewById(R.id.editText_city);
		mTextCity.requestFocus();
		return mTextCity.getText().toString();
	}

	protected int getTextYear(Dialog dialog) {
		EditText mTextYear = (EditText) dialog.findViewById(R.id.editText_year);
		mTextYear.requestFocus();
		return Integer.parseInt(mTextYear.getText().toString());
	}

	protected String getTextComments(Dialog dialog) {
		EditText mTextComments = (EditText) dialog
				.findViewById(R.id.editText_comments);
		mTextComments.requestFocus();
		return mTextComments.getText().toString();
	}

	protected String getTextCountry(Dialog dialog) {
		EditText mTextCountry = (EditText) dialog
				.findViewById(R.id.editText_country);
		mTextCountry.requestFocus();
		return mTextCountry.getText().toString();
	}

}
