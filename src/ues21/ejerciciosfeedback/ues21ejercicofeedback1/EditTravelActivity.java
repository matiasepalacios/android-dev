package ues21.ejerciciosfeedback.ues21ejercicofeedback1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditTravelActivity extends Activity implements TravelItemsInterface {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.linear_layout_edit_travel_activity);


		Intent intent = getIntent();
		
		this.putEditInfo(intent);

		this.clickButtonSend(intent);
	}

	public void putEditInfo(Intent intent) {
		try {
			String name;
			int year;
			String country;
			String comments;
			if (intent.hasExtra(NAME)) {
				name = intent.getExtras().getString(NAME);
				EditText mTextCity = (EditText) findViewById(R.id.editText_city);
				mTextCity.setText(name);
			}

			if (intent.hasExtra(YEAR)) {
				year = (int) intent.getExtras().getInt(YEAR);
				EditText mTextYear = (EditText) findViewById(R.id.editText_year);
				mTextYear.setText(String.valueOf(year));
			}

			if (intent.hasExtra(COUNTRY)) {
				country = intent.getExtras().getString(COUNTRY);
				EditText mTextCountry = (EditText) findViewById(R.id.editText_country);
				mTextCountry.setText(country);
			}

			if (intent.hasExtra(COMMENTS)) {
				comments = intent.getExtras().getString(COMMENTS);
				EditText mTextComments = (EditText) findViewById(R.id.editText_comments);
				mTextComments.setText(comments);
			}		
		} catch (Exception e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}


	}

	public void clickButtonSend(final Intent intent) {
		Button button = (Button) findViewById(R.id.button_send);
		button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (validateFields()){
					String textCity = getTextCity();
					int textYear = getTextYear();				
					String textCountry = getTextCountry();
					String textComments = getTextComments();

					
					intent.putExtra(NAME, textCity);
					intent.putExtra(YEAR, textYear);
					intent.putExtra(COUNTRY, textCountry);
					
					if (!getTextComments().trim().equals("")) {
						intent.putExtra(COMMENTS, textComments);
					}
					setResult(RESULT_OK,intent);
					finish();

				}

			}
		});
	}

	public boolean validateFields() {

		boolean error = false;

		EditText mTextYear = (EditText) findViewById(R.id.editText_year);
		String textYear = mTextYear.getText().toString();
		

		try {

			if (this.getTextCity().trim().equals("")) {
				
				throw new Exception(this.getString(R.string.error_message_empty_city));

			} else if (textYear.trim().equals("")) {				

		        mTextYear.requestFocus();
				
				throw new Exception(this.getString(R.string.error_message_empty_year));
				
			} else if (!textYear.trim().equals("")) {

				mTextYear.requestFocus();
				
				try {

					Integer.parseInt(textYear);

				} catch (NumberFormatException e) {

					throw new Exception(this.getString(R.string.error_message_bad_year_format));

				}
				
				if (this.getTextCountry().trim().equals("")) {
					
					throw new Exception(this.getString(R.string.error_message_empty_country));
					
				}

				
			}

		} catch (Exception e) {
			error = true;
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}

		return (error == false) ? true : false;
	}


	protected String getTextCity() {
		EditText mTextCity = (EditText) findViewById(R.id.editText_city);
		mTextCity.requestFocus();
		return mTextCity.getText().toString();
	}

	protected int getTextYear() {
		EditText mTextYear = (EditText) findViewById(R.id.editText_year);
        mTextYear.requestFocus();
		return Integer.parseInt(mTextYear.getText().toString());
	}

	protected String getTextComments() {
		EditText mTextComments = (EditText) findViewById(R.id.editText_comments);
		mTextComments.requestFocus();
		return mTextComments.getText().toString();
	}

	protected String getTextCountry() {
		EditText mTextCountry = (EditText) findViewById(R.id.editText_country);
		mTextCountry.requestFocus();
		return mTextCountry.getText().toString();
	}


}
