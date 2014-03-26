package com.game.businesssim;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class StandFragment extends Fragment {

    private Handler customHandler = new Handler();
    private TextView lemonCntText ;
    private TextView cupCntText ;
    private TextView sugarCntText ;
    private TextView iceCntText;
    private Business businessInfo;
    private TextView lemonadeQtyText;

    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        // Thread handler to constantly update the lemonade quantity (and supplies)
        customHandler.postDelayed(updateBusinessInfo, 0);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_stand, container, false);

        final Button plusButton = (Button) rootView.findViewById(R.id.plusBtn);
        final Button negButton = (Button) rootView.findViewById(R.id.minusBtn);
        final Button makeButton = (Button) rootView.findViewById(R.id.makeBtn);

        businessInfo = ((GameControllerActivity)this.getActivity()).getBusiness();

        lemonCntText = (TextView) rootView.findViewById(R.id.text_lemonCnt);
        cupCntText = (TextView) rootView.findViewById(R.id.text_cupCnt);
        sugarCntText = (TextView) rootView.findViewById(R.id.text_sugarCnt);
        iceCntText = (TextView) rootView.findViewById(R.id.text_iceCnt);

       lemonadeQtyText = (TextView) rootView.findViewById(R.id.text_lemonadeCnt);

        final TextView priceText = (TextView) rootView.findViewById(R.id.priceText);

        lemonCntText.setText(String.format("%d", businessInfo.getLemonCount()));
        cupCntText.setText(String.format("%d", businessInfo.getCupCount()));
        iceCntText.setText(String.format("%d", businessInfo.getIceCount()));
        sugarCntText.setText(String.format("%d", businessInfo.getSugarCount()));
        String percent = "%";
        lemonadeQtyText.setText(String.format("%d%s", businessInfo.getLemonQuantity(), percent));

        priceText.setText(String.format("$%.2f",((GameControllerActivity)this.getActivity()).getBusiness().getPricePerCup()));

        plusButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // increase price per cup on lemonade
                businessInfo.increasePricePerCup();
                priceText.setText(String.format("$%.2f",businessInfo.getPricePerCup()));
            }

        });

        negButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // increase price per cup on lemonade
                businessInfo.decreasePricePerCup();
                priceText.setText(String.format("$%.2f",businessInfo.getPricePerCup()));
            }

        });

        makeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // increase lemonade quantity
                businessInfo.makeLemonade();
                String percent = "%";
                lemonadeQtyText.setText(String.format("%d%s", businessInfo.getLemonQuantity(), percent));
                lemonCntText.setText(String.format("%d", businessInfo.getLemonCount()));
                cupCntText.setText(String.format("%d", businessInfo.getCupCount()));
                iceCntText.setText(String.format("%d", businessInfo.getIceCount()));
                sugarCntText.setText(String.format("%d", businessInfo.getSugarCount()));
            }

        });

        return rootView;
    }

    private Runnable updateBusinessInfo = new Runnable() {

        public void run() {

            lemonCntText.setText(String.format("%d", businessInfo.getLemonCount()));
            cupCntText.setText(String.format("%d", businessInfo.getCupCount()));
            iceCntText.setText(String.format("%d", businessInfo.getIceCount()));
            sugarCntText.setText(String.format("%d", businessInfo.getSugarCount()));
            String percent = "%";
            lemonadeQtyText.setText(String.format("%d%s", businessInfo.getLemonQuantity(), percent));

            customHandler.postDelayed(this, 0);
        }

    };

}
