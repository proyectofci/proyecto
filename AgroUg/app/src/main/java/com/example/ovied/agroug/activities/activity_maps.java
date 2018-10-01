package com.example.ovied.agroug.activities;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ovied.agroug.R;
import com.example.ovied.agroug.adapter.adViewCrop;
import com.example.ovied.agroug.adapter.adViewMapCrop;
import com.example.ovied.agroug.adapter.adViewTreatment;
import com.example.ovied.agroug.model.crop;
import com.example.ovied.agroug.model.map;
import com.example.ovied.agroug.model.treatment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class activity_maps extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;


    private JsonArrayRequest jarMap;
    private JsonArrayRequest jarCrop;
    private List<map> lsMap;
    private List<crop> lsCrop;
    private RequestQueue rqMap;
    private RequestQueue rqCrop;
    private static String urlCrop="http://34.237.236.90:8080/agroug/api/cultivos/phone";
    private static String urlMap="";
    ArrayAdapter<crop> aaCrop;
    private ListView lvCropLs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        lsCrop = new ArrayList<>();
        lvCropLs = (ListView) findViewById(R.id.lvCropLs);
        lvCropLs.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                crop modCrop = aaCrop.getItem(position);
                //searchUbication(modCrop.getId());
            }
        });
        searchCrop();
   }

    private void searchCrop()
    {
            jarCrop = new JsonArrayRequest(urlCrop, new Response.Listener<JSONArray>()
            {
                @Override
                public void onResponse(JSONArray response)
                {
                    JSONObject jsonObject=null;
                    for ( int i=0; i< response.length();i++)
                    {
                        try
                        {
                            jsonObject= response.getJSONObject(i);
                            crop modCrop = new crop();
                            modCrop.setId(jsonObject.getInt("id"));
                            modCrop.setName(jsonObject.getString("nombre"));
                            lsCrop.add(modCrop);
                        }
                        catch (JSONException ex)
                        {
                            ex.printStackTrace();
                        }
                    }
                    setTreatment(lsCrop);

                }
            }
                    , new Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError error)
                {

                }
            });
            rqCrop= Volley.newRequestQueue(getApplicationContext());
            rqCrop.add(jarCrop);


    }

    /* private void setRecyclerView(List<crop> lsCrop)
    {
        adViewCrop myAdapter= new adViewCrop(this,lsCrop);
        rvCropLis.setLayoutManager(new LinearLayoutManager(this));
        rvCropLis.setAdapter(myAdapter);
    }*/

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-2.1961601, -79.8862076);
        LatLng sydney1 = new LatLng(-0.22985, -78.5249481);
        LatLng sydney2 = new LatLng(	-0.96212, -80.7127075);
        LatLng sydney3 = new LatLng(	-1.05458, -80.4544525);
        LatLng sydney4 = new LatLng(-2.1340401, -79.5941467);
        LatLng sydney5 = new LatLng(	-0.96212, -80.7127075);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Posiciòn 1").icon(BitmapDescriptorFactory.fromResource(R.drawable.tree_32)));
        mMap.addMarker(new MarkerOptions().position(sydney1).title("Posiciòn 2").icon(BitmapDescriptorFactory.fromResource(R.drawable.tree_32)));
        mMap.addMarker(new MarkerOptions().position(sydney2).title("Posiciòn 3").icon(BitmapDescriptorFactory.fromResource(R.drawable.tree_32)));
        mMap.addMarker(new MarkerOptions().position(sydney3).title("Posiciòn 4").icon(BitmapDescriptorFactory.fromResource(R.drawable.tree_32)));
        mMap.addMarker(new MarkerOptions().position(sydney4).title("Posiciòn 5").icon(BitmapDescriptorFactory.fromResource(R.drawable.tree_32)));
        mMap.addMarker(new MarkerOptions().position(sydney5).title("Posiciòn 6").icon(BitmapDescriptorFactory.fromResource(R.drawable.tree_32)));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,8));
    }



    private void setTreatment(List<crop> lsCrop)
    {
        aaCrop = new adViewMapCrop(getApplicationContext(), lsCrop);
        lvCropLs.setAdapter(aaCrop);
    }

    private void searchUbication(List<crop> lsCrop)
    {
       /* aaCrop = new adViewMapCrop(getApplicationContext(), lsCrop);
        lvCropLs.setAdapter(aaCrop);
        onMapReady(GoogleMap googleMap);*/
    }
}
