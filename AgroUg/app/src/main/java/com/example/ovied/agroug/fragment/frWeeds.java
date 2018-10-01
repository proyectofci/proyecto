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


public class frWeeds extends Fragment
{
    //region Variables
    private static  String urlCropWeed="http://34.237.236.90:8080/agroug/api/afecciones/tipos/[X_AFECCION_ID]/cultivos/[X_CROP_ID]";
    private static  String urlCropWeedTemp="";
    private  static String navegation="";
    private JsonArrayRequest jarCropWeed;
    private RequestQueue rqCropWeed;
    private List<affection> lsWeed;
    private RecyclerView rvWeedLs;
    //endregion

    //region Events
    public frWeeds()
    {
        // Required empty public constructor
    }

    public static frWeeds newInstance(String _cropId,String _affetionId, String _navegation)
    {
        frWeeds fragment = new frWeeds();
        urlCropWeedTemp = urlCropWeed.replace("[X_AFECCION_ID]", _affetionId);
        urlCropWeedTemp = urlCropWeedTemp.replace("[X_CROP_ID]", _cropId);
        navegation= _navegation;
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
        View v= inflater.inflate(R.layout.fragment_fr_weeds, container, false);
        lsWeed = new ArrayList<>();
        rvWeedLs=v.findViewById(R.id.rvWeeLs);
        searchCropWeed();
        return v;
    }

    @Override
    public void onActivityCreated( @Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }
    //endregion

    //region Methods
    private void searchCropWeed()
    {
        jarCropWeed = new JsonArrayRequest(urlCropWeedTemp, new Response.Listener<JSONArray>()
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
                        lsWeed.add(modAffection);
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
                setCropWeed(lsWeed);
            }
        }
        , new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
            }
        });
        rqCropWeed= Volley.newRequestQueue(getActivity().getApplicationContext());
        rqCropWeed.add(jarCropWeed);
    }

    private void setCropWeed(List<affection> lsWeed)
    {
        adViewAffection myAdapter= new adViewAffection(getActivity(),lsWeed);
        rvWeedLs.setLayoutManager(new GridLayoutManager(getActivity(),2));
        rvWeedLs.setAdapter(myAdapter);
    }
    //endregion
}
