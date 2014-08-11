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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linear_layout_matt);
		this.clickButtonSend();
	}

	public void clickButtonSend() {
		Button button = (Button) findViewById(R.id.button_send);
		button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				EditText mDestinatario = (EditText) findViewById(R.id.editText_city); 
				String destinatario = mDestinatario.getText().toString();
//				mMensaje = (EditText) findViewById(R.id.mensaje);

//				String message = getResources().getString(
//						R.string.new_visit_text)
//						+ " " + destinatario + " " + getResources().getString(R.string.city);
//
//				Toast.makeText(EditTravelActivity.this, message,
//						Toast.LENGTH_SHORT).show();
				
				/*
				 * Instantiate Intent 
				 */
				
				Intent intent = new Intent(EditTravelActivity.this, TravelListActivity.class);
				intent.putExtra(NAME, destinatario);
				startActivity(intent);

			}
		});
	}

}
