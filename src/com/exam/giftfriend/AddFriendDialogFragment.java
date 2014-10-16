package com.exam.giftfriend;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.exam.giftfriend.sqlite.SQLiteRepo;
import com.exam.giftfriend.sqlite.models.Friend;

public class AddFriendDialogFragment extends DialogFragment{

	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		// Get the layout inflater
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View customView = inflater.inflate(R.layout.dialog_fragment_add_friend,
				null, false);
		final EditText friendName = (EditText) customView
				.findViewById(R.id.friend_name);
		//final ImageView friendImage = (ImageView) customView.findViewById(R.id.imageFriend);

		// Inflate and set the layout for the dialog
		// Pass null as the parent view because its going in the dialog layout
		builder.setView(customView)
				// Add action buttons
				.setPositiveButton(R.string.add_friend,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
								try {

									SQLiteRepo repo = new SQLiteRepo(
											getActivity());
									String name = friendName.getText().toString();
									
									Friend newFriend = new Friend(name);
									long giftId = repo.createFriend(newFriend);
									repo.close();
									Toast.makeText(getActivity(),
											"Added: friendId = " + giftId,
											Toast.LENGTH_LONG).show();
								} catch (IllegalArgumentException e) {
									Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
								} catch (Exception e) {
									Log.e("ErrorGift", e.getMessage(), e);
									e.printStackTrace();
								}
							}
						})
				.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// LoginDialogFragment.this.getDialog().cancel();
							}
						}).setTitle(R.string.createFriend);
		return builder.create();
	}
	
}
