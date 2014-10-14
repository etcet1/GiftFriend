package com.exam.giftfriend.fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.exam.giftfriend.EventsData;
import com.exam.giftfriend.R;

public class MainPageFragment extends ListFragment {
    OnEventSelectedListener mCallback;

    public interface OnEventSelectedListener {
        /** Called by OnEventSelectedListener when a list item is selected */
        public void onEventSelected(int position);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,
                EventsData.Events));
    }

    @Override
    public void onStart() {
        super.onStart();

        //TODO fix the selector after adding Event fragment
        if (getFragmentManager().findFragmentById(R.id.mainpage_fragment_container) != null) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This ensures that the container activity has implemented
        // the callback interface. If not, it throws an exception.
        try {
            mCallback = (OnEventSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnEventSelectedListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Notify the parent activity of selected item
        mCallback.onEventSelected(position);

        // Set the item as checked to be highlighted when in two-pane layout
        getListView().setItemChecked(position, true);
    }
}
