package com.yztc.lovetv.fragment.other;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yztc.lovetv.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ManyFragment extends Fragment {


    public ManyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_many, container, false);
    }

}
