package com.siyao.animationlibrarydemo;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.androidanimator.MyAnimator;

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
					MyAnimator.blind(card);
					break;
				case "Bounce":
					MyAnimator.bounce(card);
					break;
				case "Clip":
					MyAnimator.clip(card);
					break;
				case "Explode":
					MyAnimator.explode(card);
					break;
				case "Highlight":
					MyAnimator.highlight(card);
					break;
				case "Path":
					ArrayList<Point> points = new ArrayList<>();
					points.add(new Point(0, 0));
					//points.add(new Point(100, 0));
					MyAnimator.path(card, points, 1000);
					break;
				case "Pulsate":
					MyAnimator.pulsate(card);
					break;
				case "Scale":
					MyAnimator.scaleIn(card);
					break;
				case "Shake":
					MyAnimator.shake(card);
					break;
				case "Slide In":
					MyAnimator.slideIn(card);
					break;
				case "Slide Out":
					MyAnimator.slideOut(card);
					break;
				case "Transfer":
					MyAnimator.transfer(card, v, 500);
					break;
				default:
					break;
				}
			}
		});
	}
}
