package com.game.businesssim;
 
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
 
public class BankFragment extends Fragment {
	
	private RadioGroup loans;
	private RadioButton loan1;
	private RadioButton loan2;
	private RadioButton loan3;
	private Button takeOutLoan;
	private Button repayBtn;
	private EditText repayAmount;
	private int loanAmt;
	Business biz;
	

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_bank, container, false);
        loans = (RadioGroup) rootView.findViewById(R.id.radioGroup);
        loan1 = (RadioButton) rootView.findViewById(R.id.radioButton1);
        loan2 = (RadioButton) rootView.findViewById(R.id.radioButton2);
        loan3 = (RadioButton) rootView.findViewById(R.id.radioButton3);
        takeOutLoan = (Button) rootView.findViewById(R.id.getLoanBtn);
        repayBtn = (Button) rootView.findViewById(R.id.payLoanBtn);
        repayAmount = (EditText) rootView.findViewById(R.id.input_PayLoan);
        
        return rootView;
    }
    
    private void applyLoan(int amt){
    	biz.setProfit((float) (biz.getProfit()+amt));
    	biz.setLoans(biz.getNumLoans()+1);
    	loanAmt+=amt;
    }
    
    private void repayLoan(int amt){
    	biz.setProfit((float) (biz.getProfit()-amt));
    	biz.setLoans(biz.getNumLoans()-1);
    	loanAmt-=amt;
    }
    
}