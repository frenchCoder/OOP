package com.game.businesssim;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class BankFragment extends Fragment {

	private int loanAmt;
	private int interestAmt;
	private Loan[] loansArray;
	final private int MAX_LOANS = 3;
	private float rate;
	Business biz;
	private static final String TAG = "MyActivity";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		biz = ((GameControllerActivity) this.getActivity()).getBusiness();
		final View rootView = inflater.inflate(R.layout.fragment_bank,
				container, false);
		final RadioGroup loans = (RadioGroup) rootView.findViewById(R.id.radioGroup);
		final Button takeOutLoan = (Button) rootView.findViewById(R.id.getLoanBtn);
		final Button repayBtn = (Button) rootView.findViewById(R.id.payLoanBtn);
		final EditText repayAmount = (EditText) rootView.findViewById(R.id.input_PayLoan);
		final TextView owedAmt = (TextView) rootView.findViewById(R.id.text_owed);
		//final TextView owedIntAmt = (TextView) rootView
		//		.findViewById(R.id.text_owedWithInterest);
		loanAmt = 0;
		rate = (float) 0.05;

		takeOutLoan.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				int selected = loans.getCheckedRadioButtonId();
				RadioButton loanSelected = (RadioButton) rootView.findViewById(selected);
				int amt = Integer.parseInt(((String) loanSelected.getText()).substring(1));
				if (applyLoan(amt)) {
					loanConfirmed(amt);
					owedAmt.setText("$"+Integer.toString(loanAmt + interestAmt));
					//owedIntAmt.setText(Integer.toString(loanAmt + interestAmt));
				} else {
					loanDenied(amt);
				}
			}

		});

		repayBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				int repay = Integer.parseInt(repayAmount.getText().toString());
				repayLoan(repay);
				repayAmount.setText("0");
				repayConfirmed(repay);
				owedAmt.setText("$"+Integer.toString(loanAmt + interestAmt));
				//owedIntAmt.setText()  ;
			}

		});

		return rootView;
	}

	private boolean applyLoan(int amt) {
		if (biz.getNumLoans() == MAX_LOANS)
			return false;
		biz.setProfit((float) (biz.getProfit() + amt));
		biz.setLoans(biz.getNumLoans() + 1);
		loanAmt += ((amt * rate) + amt);
		return true;
	}

	private void repayLoan(int amt) {
		if(amt>loanAmt){
			amt=amt-loanAmt;
		}
		biz.setProfit((float) (biz.getProfit() - amt));
		loanAmt -= amt;
		if(loanAmt<=0){
			biz.setLoans(0);
			loanAmt=0;
		}
	}

	private void loanConfirmed(int amt) {
		AlertDialog.Builder loanConfirmedDialog = new AlertDialog.Builder(
				getActivity());

		loanConfirmedDialog.setMessage("The loan of $" + amt
				+ " has been confirmed and has been applied to your account");
		loanConfirmedDialog.setTitle("Loan Confirmed");

		// continue game if user presses 'cancel'
		loanConfirmedDialog.setPositiveButton(R.string.ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {

					}
				});

		loanConfirmedDialog.create();
		loanConfirmedDialog.show();
	}

	private void loanDenied(int amt) {
		AlertDialog.Builder loanDeniedDialog = new AlertDialog.Builder(
				getActivity());

		loanDeniedDialog
				.setMessage("The loan of $"
						+ amt
						+ " has been denied because you have reached the maximum amount of loans. "
						+ "In order to take out more loans you must repay all previous loans");
		loanDeniedDialog.setTitle("Loan Denied");

		// continue game if user presses 'cancel'
		loanDeniedDialog.setPositiveButton(R.string.ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {

					}
				});

		loanDeniedDialog.create();
		loanDeniedDialog.show();

	}

	private void repayConfirmed(int amt) {
		AlertDialog.Builder repayConfirmedDialog = new AlertDialog.Builder(
				getActivity());

		repayConfirmedDialog.setMessage("The repay amount of $" + amt
				+ " has been confirmed and has been applied to your account");
		repayConfirmedDialog.setTitle("Repay Confirmed");

		// continue game if user presses 'cancel'
		repayConfirmedDialog.setPositiveButton(R.string.ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {

					}
				});

		repayConfirmedDialog.create();
		repayConfirmedDialog.show();
	}

	public int getLoanAmt() {
		return loanAmt;
	}

	public void setLoanAmt(int loanAmt) {
		this.loanAmt = loanAmt;
	}

}