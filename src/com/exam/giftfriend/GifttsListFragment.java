package com.exam.giftfriend;

import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.exam.giftfriend.sqlite.SQLiteRepo;
import com.exam.giftfriend.sqlite.models.Gift;

public class GifttsListFragment extends Fragment {
	
	//protected ArrayList<Gift> products = new ArrayList<Gift>();

	protected void initializeList(View view) {
		SQLiteRepo repo = new SQLiteRepo(getActivity());
		List<Gift> products = repo.getAllGifts();
		
		ListView listView = (ListView) view.findViewById(R.id.listViewGifts);
		GiftsAdapter adapter = new GiftsAdapter(getActivity(),
				R.layout.fragment_gifts_list_row, products);
		listView.setAdapter(adapter);
		// listView.setOnClickListener(this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_gifts_list, container,
				false);
		initializeList(view);
		return view;
	}
}
