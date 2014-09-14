package com.easyandroidanimations.demo;

import com.two359media.animationsample.R;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

/**
 * An activity representing a single Animation detail screen. This activity is
 * only used on phones. On tablets, item details are presented side-by-side with
 * a list of items in a {@link AnimationListActivity}.
 * <p>
 * This activity is mostly just a 'shell' activity containing nothing more than
 * a {@link AnimationDetailFragment}.
 */
public class AnimationDetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animation_detail);

		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);

		if (savedInstanceState == null) {
			// Create the detail fragment and add it to the activity
			// using a fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putInt(AnimationDetailFragment.ARG_ITEM_ID, getIntent()
					.getIntExtra(AnimationDetailFragment.ARG_ITEM_ID, 0));
			AnimationDetailFragment fragment = new AnimationDetailFragment();
			fragment.setArguments(arguments);
			getFragmentManager().beginTransaction()
					.add(R.id.animation_detail_container, fragment).commit();
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == android.R.id.home) {
			NavUtils.navigateUpTo(this, new Intent(this,
					AnimationListActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
