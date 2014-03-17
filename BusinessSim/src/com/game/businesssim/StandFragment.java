package com.game.businesssim;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
 
public class StandFragment extends Fragment {
    private Business businessInfo;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_stand, container, false);
         
        return rootView;
    }

    public void createNewBusiness(){
        businessInfo = new Business();
    }

    public void recreateBusiness(Business business){
        businessInfo = new Business(business);
    }

    public Business getBusinessInfo(){
        return businessInfo;
    }
}
