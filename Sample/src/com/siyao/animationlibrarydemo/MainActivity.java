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

import com.siyao.animationlibrary.BlindAnimation;
import com.siyao.animationlibrary.BounceAnimation;
import com.siyao.animationlibrary.ClipAnimation;
import com.siyao.animationlibrary.DropAnimation;
import com.siyao.animationlibrary.ExplodeAnimation;
import com.siyao.animationlibrary.HighlightAnimation;
import com.siyao.animationlibrary.MyAnimator;
import com.siyao.animationlibrary.PulsateAnimation;
import com.siyao.animationlibrary.ScaleAnimation;
import com.siyao.animationlibrary.TransferAnimation;

public class MainActivity extends Activity {
	
	float x, y;
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
					MyAnimator.animate(card, new BlindAnimation());
					break;
				case "Bounce":
					MyAnimator.animate(card, new BounceAnimation());
					break;
				case "Clip":
					MyAnimator.animate(card, new ClipAnimation());
					break;
				case "Drop":
					MyAnimator.animate(card, new DropAnimation());
					break;
				case "Explode":
					MyAnimator.animate(card, new ExplodeAnimation());
					break;
				case "Highlight":
					MyAnimator.animate(card, new HighlightAnimation());
					break;
				case "Pulsate":
					MyAnimator.animate(card, new PulsateAnimation());
					break;
				case "Scale":
					MyAnimator.animate(card, new ScaleAnimation());
					break;
				case "Transfer":
					MyAnimator.animate(card, new TransferAnimation(v, 5000));
					break;
				default:
					break;
				}
			}
		});
	}
}
