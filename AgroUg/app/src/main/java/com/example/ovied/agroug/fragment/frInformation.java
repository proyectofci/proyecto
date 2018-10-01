package com.example.ovied.agroug.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ovied.agroug.R;
import com.example.ovied.agroug.model.affection;


import org.json.JSONException;
import org.json.JSONObject;

public class frInformation extends Fragment
{


    //region Variables
    private static String urlAffectionSpe="http://34.237.236.90:8080/agroug/api/cultivos/[X_AFFECTION_ID]";
    private static  String urlAffectionSpeTemp="";
    JsonObjectRequest jarUrlAffectionSpe;
    TextView tvNameAffSpe;
    TextView tvDescriptionAffSpe;
    private RequestQueue rqAffSpe;
    //endregion

    //region Events
    public frInformation()
    {

    }

    public static frInformation newInstance(String _AffectionId)
    {
        frInformation fragment = new frInformation();
        urlAffectionSpeTemp = urlAffectionSpe.replace("[X_AFFECTION_ID]", _AffectionId);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v= inflater.inflate(R.layout.fragment_fr_information_aff, container, true);
        tvNameAffSpe= (TextView)v.findViewById(R.id.tvNameInfAff);
        tvDescriptionAffSpe= (TextView)v.findViewById(R.id.tvDescriptionInfAff);
        searchCropSpecific();
        return v;
    }

    @Override
    public void onActivityCreated( @Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        // here you can use mParam1 and mParam2
    }
    //endregion

    //region Methods
    private void searchCropSpecific()
    {
        jarUrlAffectionSpe = new JsonObjectRequest(urlAffectionSpeTemp,null, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                affection modAffection = new affection();
                try
                {
                    modAffection.setId(response.getInt("id"));
                    modAffection.setName(response.getString("nombre"));
                    modAffection.setDescription(response.getString("nombre"));
                }
                catch (JSONException ex)
                {
                    ex.printStackTrace();
                }
                setData(modAffection);
            }
        }
                , new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {

            }
        });
        rqAffSpe= Volley.newRequestQueue(getActivity().getApplicationContext());
        rqAffSpe.add(jarUrlAffectionSpe);
    }

    private void setData(affection modAffection)
    {
        tvNameAffSpe.setText(modAffection.getName());
        tvDescriptionAffSpe.setText(modAffection.getDescription());
    }
    //endregion


}
