package com.game.businesssim;
 
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
	
	private RadioGroup loans;
	private RadioButton loanSelected;
	private Button takeOutLoan;
	private Button repayBtn;
	private EditText repayAmount;
	private TextView owedAmt;
	private TextView owedIntAmt;
	private int loanAmt;
	private int interestAmt;
	private float rate;
	Business biz;
	

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
    	biz = ((GameControllerActivity)this.getActivity()).getBusiness();
        final View rootView = inflater.inflate(R.layout.fragment_bank, container, false);
        loans = (RadioGroup) rootView.findViewById(R.id.radioGroup);
        takeOutLoan = (Button) rootView.findViewById(R.id.getLoanBtn);
        repayBtn = (Button) rootView.findViewById(R.id.payLoanBtn);
        repayAmount = (EditText) rootView.findViewById(R.id.input_PayLoan);
        owedAmt = (TextView) rootView.findViewById(R.id.text_owed);
        owedIntAmt = (TextView) rootView.findViewById(R.id.text_owedWithInterest);
        loanAmt=0;
        rate=(float) 0.05;
        
        takeOutLoan.setOnClickListener(new OnClickListener (){

			@Override
			public void onClick(View arg0) {
				int selected = loans.getCheckedRadioButtonId();
				loanSelected = (RadioButton) rootView.findViewById(selected);
				int amt=Integer.parseInt((String) loanSelected.getText());
				applyLoan(amt);
				loanConfirmed(amt);
				owedAmt.setText(loanAmt);
				owedIntAmt.setText(loanAmt+interestAmt);
			}
        	
        });
        
        repayAmount.setOnClickListener(new OnClickListener (){

			@Override
			public void onClick(View arg0) {
				int repay = Integer.parseInt(repayAmount.getText().toString());
				repayLoan(repay);
				repayAmount.setText('0');
				repayConfirmed(repay);
				owedAmt.setText(loanAmt);
				owedIntAmt.setText(loanAmt+interestAmt);
			}
        	
        });
        
        return rootView;
    }
    
    private void applyLoan(int amt){
    	biz.setProfit((float) (biz.getProfit()+amt));
    	biz.setLoans(biz.getNumLoans()+1);
    	interestAmt+=(amt*rate);
    	loanAmt+=((amt*rate)+amt);
    }
    
    private void repayLoan(int amt){
    	biz.setProfit((float) (biz.getProfit()-amt));
    	biz.setLoans(biz.getNumLoans()-1);
    	loanAmt-=amt;
    }
    
    private void loanConfirmed(int amt){
    	AlertDialog.Builder loanConfirmedDialog = new AlertDialog.Builder(getActivity());	
    	
    	loanConfirmedDialog.setMessage("The loan of " + amt + "has been confirmed and has been applied to your account");
    	loanConfirmedDialog.setTitle("Loan Confirmed");

        // continue game if user presses 'cancel'
    	loanConfirmedDialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
               
            }
        });

    	loanConfirmedDialog.create();
    	loanConfirmedDialog.show();
    }
    
    private void repayConfirmed(int amt){
    	AlertDialog.Builder repayConfirmedDialog = new AlertDialog.Builder(getActivity());	
    	
    	repayConfirmedDialog.setMessage("The repay amount of " + amt + "has been confirmed and has been applied to your account");
    	repayConfirmedDialog.setTitle("Repay Confirmed");

        // continue game if user presses 'cancel'
    	repayConfirmedDialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
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