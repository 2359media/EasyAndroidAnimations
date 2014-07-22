package com.siyao.animationlibrarydemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.androidanimator.MyAnimator1;

public class MainActivity extends Activity {
	
	Spinner spinner;
	TextView card;
	Button animateButton;
	String selectedAnimation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		spinner = (Spinner) findViewById(R.id.spinner);
		card = (TextView) findViewById(R.id.card);
		animateButton = (Button) findViewById(R.id.animate_button);
		
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.animations_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				selectedAnimation = parent.getItemAtPosition(position).toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
		});
		
		animateButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				switch (selectedAnimation) {
				case "Blind":
					MyAnimator1.blind(card);
					break;
				case "Bounce":
					MyAnimator1.bounce(card);
					break;
				case "Clip":
					MyAnimator1.clip(card);
					break;
				case "Explode":
					MyAnimator1.explode(card);
					break;
				case "Highlight":
					MyAnimator1.highlight(card);
					break;
				case "Pulsate":
					MyAnimator1.pulsate(card);
					break;
				case "Scale":
					MyAnimator1.scale(card);
					break;
				case "Shake":
					MyAnimator1.shake(card);
					break;
				case "Slide In":
					MyAnimator1.slideIn(card);
					break;
				case "Slide Out":
					MyAnimator1.slideOut(card);
					break;
				case "Transfer":
					//card.invalidate();
					MyAnimator.transfer(card, v, 10000);
					break;
				default:
					break;
				}
			}
		});
	}
}
