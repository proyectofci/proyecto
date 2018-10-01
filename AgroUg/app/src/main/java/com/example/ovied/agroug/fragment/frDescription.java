package com.example.ovied.agroug.fragment;

    //region Import
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
    import com.example.ovied.agroug.model.crop;
    import org.json.JSONException;
    import org.json.JSONObject;
    import java.util.ArrayList;
    import java.util.List;
    //endregion

public class frDescription extends Fragment
{
    //region Variables
    private static  String urlCropSpe="http://34.237.236.90:8080/agroug/api/cultivos/[X_CROP_ID]";
    private static  String urlCropSpeTemp="";
    JsonObjectRequest jarCropSpe;
    TextView tvNameInf;
    TextView tvNameScientificInf;
    TextView tvDescriptionInf;
    private RequestQueue rqCropSpe;
    //endregion

    //region Events
    public frDescription()
    {

    }

    public static frDescription newInstance(String _cropId)
    {
        frDescription fragment = new frDescription();
        urlCropSpeTemp = urlCropSpe.replace("[X_CROP_ID]", _cropId);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View v= inflater.inflate(R.layout.fragment_fr_information, container, false);
        tvNameInf= (TextView)v.findViewById(R.id.tvNameInf);
        tvNameScientificInf= (TextView)v.findViewById(R.id.tvNameScientificInf);
        tvDescriptionInf= (TextView)v.findViewById(R.id.tvDescriptionInf);
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
        jarCropSpe = new JsonObjectRequest(urlCropSpeTemp,null, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                crop modCorp = new crop();
                try
                {
                    modCorp.setId(response.getInt("id"));
                    modCorp.setName(response.getString("nombre"));
                    modCorp.setNameScientific(response.getString("nombreCientifico"));
                    modCorp.setDescription(response.getString("descripcion"));
                }
                catch (JSONException ex)
                {
                    ex.printStackTrace();
                }
               setData(modCorp);
            }
        }
        , new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {

            }
        });
        rqCropSpe= Volley.newRequestQueue(getActivity().getApplicationContext());
        rqCropSpe.add(jarCropSpe);
    }

    private void setData(crop modCrop)
    {
        tvNameInf.setText(modCrop.getName());
        tvNameScientificInf.setText(modCrop.getNameScientific());
        tvDescriptionInf.setText(modCrop.getDescription());
    }
    //endregion
}
