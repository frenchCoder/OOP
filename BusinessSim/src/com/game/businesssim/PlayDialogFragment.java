package com.game.businesssim;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class PlayDialogFragment extends DialogFragment{
	
	PlayOptionListener dListener;

	public Dialog onCreateDialog(Bundle SavedInstanceState){
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		builder.setTitle(R.string.dialog_title)
				.setItems(R.array.dialog_options, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// pass the users option back to the activity
						dListener.onOptionClick(which);
						
					}
				});
		
		return builder.create();
	}
	
	public interface PlayOptionListener{
		public void onOptionClick(int which);
	}
	
	@Override
	public void onAttach(Activity a){
		super.onAttach(a);
		//make sure the activity implements the interface
		try{
			dListener = (PlayOptionListener) a;
		}
		catch(ClassCastException e){
			throw new ClassCastException(a.toString()+" must implement PlayOptionListener");
		}
	}
}
