package ues21.ejerciciosfeedback.ues21ejercicofeedback1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class EditTravelActivity extends Activity {
	
	public final static String NAME = "NAME";
	public final static String YEAR = "YEAR";
	public final static String COUNTRY = "COUNTRY";
	public final static String COMMENTS = "COMMENTS";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.linear_layout_edit_travel_activity);
		this.clickButtonSend();
	}

	public void clickButtonSend() {
		Button button = (Button) findViewById(R.id.button_send);
		button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				EditText mTextCity = (EditText) findViewById(R.id.editText_city);
				EditText mTextYear = (EditText) findViewById(R.id.editText_year);
				EditText mTextCountry = (EditText) findViewById(R.id.editText_country);
				EditText mTextComments = (EditText) findViewById(R.id.editText_comments);				
				String textCity = mTextCity.getText().toString();
				String textYear = mTextYear.getText().toString();				
				String textCountry = mTextCountry.getText().toString();
				String textComments = mTextComments.getText().toString();
				
				/*
				 * Instantiate Intent 
				 */
				
				Intent intent = new Intent();
				intent.putExtra(NAME, textCity);
				intent.putExtra(YEAR, textYear);
				intent.putExtra(COUNTRY, textCountry);
				intent.putExtra(COMMENTS, textComments);
				setResult(RESULT_OK,intent);
				finish();

			}
		});
	}

}
