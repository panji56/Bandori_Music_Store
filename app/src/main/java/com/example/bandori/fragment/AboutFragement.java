package com.example.bandori.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.bandori.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AboutFragement#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AboutFragement extends Fragment implements OnMapReadyCallback {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Context context;

    private GoogleMap mMap;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public AboutFragement() {
        // Required empty public constructor
    }

    public AboutFragement(Context context){
        this.context=context;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AboutFragement.
     */
    // TODO: Rename and change types and number of parameters
    public static AboutFragement newInstance(String param1, String param2) {
        AboutFragement fragment = new AboutFragement();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button visit=(Button)view.findViewById(R.id.visit_button);
        MapView mv =(MapView)view.findViewById(R.id.mapView);
//        SupportMapFragment mapFragment =
//                (SupportMapFragment) getChildFragmentManager().findFragmentById();
        if (mv != null) {
//            Log.i("Map : ",""+mv);
            mv.getMapAsync(this);
        }
        visit.setOnClickListener(
                v->{
                    String url="https://en.bang-dream.com/";
                    Intent Bintent=new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(Bintent);
                }
        );
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
        return inflater.inflate(R.layout.fragment_about_fragement, container, false);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng bandori = new LatLng(35.686960, 139.749460);
        mMap.addMarker(new MarkerOptions().position(bandori).title("Marker in Bandori"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bandori));
    }
}