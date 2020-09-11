package com.example.androidanimationdemo;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import android.view.MenuItem;

public class AnimationDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putInt(AnimationDetailFragment.ARG_ITEM_ID, getIntent()
                    .getIntExtra(AnimationDetailFragment.ARG_ITEM_ID, 0));
            AnimationDetailFragment fragment = new AnimationDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction().add(R.id.animation_detail_container, fragment).commit();
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
