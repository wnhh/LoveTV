package com.yztc.lovetv.fragment.contntfragment;


import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yztc.lovetv.R;
import com.yztc.lovetv.fragment.show.FairyFragment;
import com.yztc.lovetv.fragment.show.PeopleFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class GuardFragment extends Fragment implements View.OnClickListener{

    private FairyFragment fairyFragment;
    private PeopleFragment peopleFragment;
    private ImageView fairy;
    private ImageView people;

    public GuardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_guard, container, false);
        fairy= (ImageView) v.findViewById(R.id.fairy);
        people= (ImageView) v.findViewById(R.id.people);
        fairy.setOnClickListener(this);
        people.setOnClickListener(this);

        initFragment1( );
        initFragment2();
        return v;
    }

    private void initFragment1( ) {
        FragmentTransaction fragmentTransaction= getChildFragmentManager().beginTransaction();
        if (fairyFragment==null){
            fragmentTransaction.add(R.id.content_frame_layout,fairyFragment);
        }
        //隐藏所有fragment
        hideFragment(fragmentTransaction);
        fragmentTransaction.show(fairyFragment);
        fragmentTransaction.commit();
    }

    private void initFragment2(){
        FragmentTransaction fragmentTransaction= getChildFragmentManager().beginTransaction();
        if (peopleFragment==null){
            fragmentTransaction.add(R.id.content_frame_layout,peopleFragment);
        }
        //隐藏所有fragment
        hideFragment(fragmentTransaction);
        fragmentTransaction.show(peopleFragment);
        fragmentTransaction.commit();
    }

    private void hideFragment(FragmentTransaction fragmentTransaction){
        if (fairyFragment!=null){
            fragmentTransaction.hide(fairyFragment);
        }
        if (peopleFragment!=null){
            fragmentTransaction.hide(peopleFragment);
        }
    }


    @Override
    public void onClick(View v) {
        if (v==fairy){
            initFragment1();
        }else if(v==people){
            initFragment2();
        }
    }
}
