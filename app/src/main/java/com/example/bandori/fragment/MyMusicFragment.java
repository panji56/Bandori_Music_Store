package com.example.bandori.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.bandori.R;
import com.example.bandori.adapter.music_view;
import com.example.bandori.data.MUSICBandori;
import com.example.bandori.data.USERBandori;
import com.example.bandori.database.MusicDB;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyMusicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyMusicFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private USERBandori user;

    public MyMusicFragment() {
        // Required empty public constructor
    }

    public MyMusicFragment(USERBandori user){
           this.user=user;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyMusicFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyMusicFragment newInstance(String param1, String param2) {
        MyMusicFragment fragment = new MyMusicFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MusicDB db = new MusicDB(getContext());
        ArrayList<MUSICBandori> ms = db.transactionReadJoinMusic(""+user.getUserID());
        ListView listView = (ListView) view.findViewById(R.id.music_listview);
        music_view msv=new music_view(ms,getContext());
        listView.setAdapter(msv);
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
        return inflater.inflate(R.layout.fragment_my_music, container, false);
    }
}