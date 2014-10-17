package com.exam.giftfriend;

import java.security.PublicKey;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.exam.giftfriend.sqlite.SQLiteRepo;
import com.exam.giftfriend.sqlite.models.Gift;

public class AddGiftDialogFragment extends DialogFragment {

	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		// Get the layout inflater
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View customView = inflater.inflate(R.layout.dialog_fragment_add_gift,
				null, false);
		final EditText giftName = (EditText) customView
				.findViewById(R.id.edt_giftName);
		final EditText giftLocation = (EditText) customView
				.findViewById(R.id.edt_giftLocation);

		// Inflate and set the layout for the dialog
		// Pass null as the parent view because its going in the dialog layout
		builder.setView(customView)
				// Add action buttons
				.setPositiveButton(R.string.add_gift,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
								try {

									SQLiteRepo repo = new SQLiteRepo(
											getActivity());
									String name = giftName.getText().toString();
									String location = giftLocation.getText()
											.toString();
									Gift newGift = new Gift(name, location);
									long giftId = repo.createGift(newGift);
									repo.close();
									Toast.makeText(getActivity(),
											"Added; giftId = " + giftId,
											Toast.LENGTH_LONG).show();
								} catch (IllegalArgumentException e){
									ErrorDialogFragment dialogFragment = new ErrorDialogFragment();
									dialogFragment.show(getFragmentManager(), "errorFragment");
									//Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
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
						}).setTitle(R.string.createGift);
		return builder.create();
	}
}
