package com.example.androidanimationdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import androidx.fragment.app.FragmentActivity;

public class AnimationListActivity extends FragmentActivity implements AnimationListFragment.Callbacks {

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_list);

        if (findViewById(R.id.animation_detail_container) != null) {
            mTwoPane = true;
            ((AnimationListFragment) getSupportFragmentManager().findFragmentById(
                    R.id.animation_list)).setActivateOnItemClick(true);
        }
    }

    @Override
    public void onItemSelected(int id) {
        System.out.println(mTwoPane);
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putInt(AnimationDetailFragment.ARG_ITEM_ID, id);
            AnimationDetailFragment fragment = new AnimationDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.animation_detail_container, fragment)
                    .commit();
        } else {
            Intent detailIntent = new Intent(this, AnimationDetailActivity.class);
            detailIntent.putExtra(AnimationDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
}
