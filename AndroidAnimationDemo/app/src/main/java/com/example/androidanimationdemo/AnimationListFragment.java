package com.example.androidanimationdemo;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;
import androidx.fragment.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.androidanimationdemo.model.DemoItem;

public class AnimationListFragment extends ListFragment {

    private static final String STATE_ACTIVATED_POSITION = "activated_position";

    private int mActivatedPosition = ListView.INVALID_POSITION;

    public interface Callbacks {

        public void onItemSelected(int id);

    }

    private static Callbacks sDummyCallbacks = new Callbacks() {

        @Override
        public void onItemSelected(int id) {
            System.out.println(id + " here");
        }
    };

    private Callbacks mCallbacks = sDummyCallbacks;

    public AnimationListFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO
        setListAdapter(new ArrayAdapter<DemoItem.DemoAnimation>(getActivity(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1, DemoItem.ITEMS));
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        System.out.println("context: ");
        System.out.println(context);
        if (!(context instanceof Callbacks)) {
            throw new IllegalStateException(
                    "Activity must implement fragment's callbacks.");
        }

        mCallbacks = (Callbacks) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = sDummyCallbacks;
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);
        Toast.makeText(this.getActivity(), DemoItem.ITEMS.get(position).toString(), Toast.LENGTH_SHORT).show();
        mCallbacks.onItemSelected(DemoItem.ITEMS.get(position).id);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mActivatedPosition != ListView.INVALID_POSITION) {
            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
        }
    }

    public void setActivateOnItemClick(boolean activateOnItemClick) {
        getListView().setChoiceMode(
                activateOnItemClick ? ListView.CHOICE_MODE_SINGLE
                        : ListView.CHOICE_MODE_NONE);
    }

    private void setActivatedPosition(int position) {
        if (position == ListView.INVALID_POSITION) {
            getListView().setItemChecked(mActivatedPosition, false);
        } else {
            getListView().setItemChecked(position, true);
        }
        mActivatedPosition = position;
    }

}
