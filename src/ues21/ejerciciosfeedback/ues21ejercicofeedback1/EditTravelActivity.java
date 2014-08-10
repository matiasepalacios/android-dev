package ues21.ejerciciosfeedback.ues21ejercicofeedback1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditTravelActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linear_layout_matt);
		this.clickMatt();
	}

	public void clickMatt() {
		Button button = (Button) findViewById(R.id.button_send);
		button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				EditText mDestinatario = (EditText) findViewById(R.id.editText_city); 
				String destinatario = mDestinatario.getText().toString();
//				mMensaje = (EditText) findViewById(R.id.mensaje);

				String message = getResources().getString(
						R.string.new_visit_text)
						+ " " + destinatario + " " + getResources().getString(R.string.city);

				Toast.makeText(EditTravelActivity.this, message,
						Toast.LENGTH_SHORT).show();

			}
		});
	}
	
	

	// This is gonna be called when the user clicks the button
	// public void captureTexts(View view) {
	// Button button = (Button) findViewById(R.id.button_send);
	// String message = getResources().getString(R.string.new_visit_text) +
	// " " +
	// getResources().getString(R.string.city);
	//
	// Toast.makeText(EditTravelActivity.this,
	// message, Toast.LENGTH_SHORT).show();
	//
	// }

}
