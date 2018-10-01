package com.example.ovied.agroug.fragment;

    //region Import
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.ovied.agroug.R;
import com.example.ovied.agroug.adapter.adViewAffection;
import com.example.ovied.agroug.model.affection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
//endregion

public class frPests extends Fragment
{
    //region Variables
    private static  String urlCropPest="http://34.237.236.90:8080/agroug/api/afecciones/tipos/[X_AFECCION_ID]/cultivos/[X_CROP_ID]";
    private static  String urlCropPestTemp="";
    private  static  String navegation;
    private JsonArrayRequest jarCropPest;
    private RequestQueue rqCropPest;
    private List<affection> lsPest;
    private RecyclerView rvPesLs;
    //endregion

    //region Events
    public frPests()
    {
        // Required empty public constructor
    }

    public static frPests newInstance(String _cropId,String _affectionId,String _navegation)
    {
        frPests fragment = new frPests();
        urlCropPestTemp = urlCropPest.replace("[X_AFECCION_ID]", _affectionId);
        urlCropPestTemp = urlCropPestTemp.replace("[X_CROP_ID]", _cropId);
        navegation=_navegation;
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
        View v= inflater.inflate(R.layout.fragment_fr_pests, container, false);
        lsPest = new ArrayList<>();
        rvPesLs=v.findViewById(R.id.rvPesLs);
        searchCropPest();
        return v;
    }

    @Override
    public void onActivityCreated( @Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }
    //endregion

    //region Methods
    private void searchCropPest()
    {
        jarCropPest = new JsonArrayRequest(urlCropPestTemp, new Response.Listener<JSONArray>()
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
                        affection modAffection = new affection();
                        modAffection.setId(jsonObject.getInt("id"));
                        modAffection.setName(jsonObject.getString("nombre"));
                        modAffection.setRouteImage(jsonObject.getString("rutaImagen"));
                        modAffection.setDescription(jsonObject.getString("descripcion"));
                        modAffection.setNameScientific(jsonObject.getString("nombreCientifico"));
                        modAffection.setNavegation(navegation+": "+ jsonObject.getString("nombre"));
                        lsPest.add(modAffection);
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
                setCropPest(lsPest);

            }
        }
        , new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
            }
        });
        rqCropPest= Volley.newRequestQueue(getActivity().getApplicationContext());
        rqCropPest.add(jarCropPest);
    }

    private void setCropPest(List<affection> lsPest)
    {
        adViewAffection myAdapter= new adViewAffection(getActivity(),lsPest);
        rvPesLs.setLayoutManager(new GridLayoutManager(getActivity(),2));
        rvPesLs.setAdapter(myAdapter);
    }
    //endregion

}
