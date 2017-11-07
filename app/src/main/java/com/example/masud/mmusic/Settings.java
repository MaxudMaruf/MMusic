package com.example.masud.mmusic;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Settings#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Settings extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public Settings() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Settings.
     */
    // TODO: Rename and change types and number of parameters
    public static Settings newInstance(String param1, String param2) {
        Settings fragment = new Settings();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_settings, container, false);
        final ArrayList <SettingsLists>settings=new ArrayList<>();
        SettingsLists s1=new SettingsLists("maruf");
        SettingsLists s2=new SettingsLists("Haruf");
        SettingsLists s3=new SettingsLists("Karuf");
        SettingsLists s4=new SettingsLists("saruf");
        SettingsLists s5=new SettingsLists("paruf");
        settings.add(s1);
        settings.add(s2);
        settings.add(s3);
        settings.add(s4);
        settings.add(s5);


        BindDictionary<SettingsLists>dictionary=new BindDictionary<>();
        dictionary.addStringField(R.id.textName, new StringExtractor<SettingsLists>() {
            @Override
            public String getStringValue(SettingsLists settings1, int position) {
                return settings1.getName();
            }
        });

        FunDapter dapter=new FunDapter(Settings.this.getActivity(),settings,R.layout.settings_layout,dictionary);
        ListView listView=(ListView) view.findViewById(R.id.listviewSettings);
        listView.setAdapter(dapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SettingsLists selecteditems=settings.get(position);
                Toast.makeText(Settings.this.getActivity(),selecteditems.getName(),Toast.LENGTH_SHORT);
            }
        });


        return view;
    }

}
