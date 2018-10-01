package com.example.ovied.agroug.activities;

    //region Import
    import android.support.design.widget.CollapsingToolbarLayout;
    import android.support.design.widget.TabLayout;
    import android.support.v4.view.ViewPager;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.widget.ImageView;
    import com.android.volley.RequestQueue;
    import com.android.volley.Response;
    import com.android.volley.VolleyError;
    import com.android.volley.toolbox.JsonArrayRequest;
    import com.android.volley.toolbox.Volley;
    import com.bumptech.glide.Glide;
    import com.bumptech.glide.request.RequestOptions;
    import com.example.ovied.agroug.adapter.adViewPager;
    import com.example.ovied.agroug.fragment.frDescription;
    import com.example.ovied.agroug.fragment.frDiseases;
    import com.example.ovied.agroug.fragment.frInformation;
    import com.example.ovied.agroug.fragment.frPests;
    import com.example.ovied.agroug.fragment.frWeeds;
    import com.example.ovied.agroug.R;
    import com.example.ovied.agroug.model.menu;
    import org.json.JSONArray;
    import org.json.JSONException;
    import org.json.JSONObject;
    import java.util.ArrayList;
    import java.util.List;
    //endregion

public class cropActivity extends AppCompatActivity
{
    //region Variables
    private final String urlTypeAffection = "http://34.237.236.90:8080/agroug/api/afecciones/tipos";
    private JsonArrayRequest jarTypeAffection;
    private TabLayout tlCroSpe;
    private ViewPager vpCroSpe;
    private RequestQueue rqCro;
    public List<menu> lsMenu;
    private static String idCrop="";
    //endregion

    //region Events
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop);

        getSupportActionBar().hide();

        idCrop = getIntent().getExtras().getString("parId");
        String name = getIntent().getExtras().getString("parName");
        String routeImage = getIntent().getExtras().getString("parRouteImage");

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.ctlCroSpe);
        collapsingToolbarLayout.setTitleEnabled(true);

        ImageView ivRouteImageCro = findViewById(R.id.ivRouteImageCroSpe);
        collapsingToolbarLayout.setTitle(name);
        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
        Glide.with(this).load(routeImage).into(ivRouteImageCro);

        tlCroSpe = (TabLayout) findViewById(R.id.tlCroSpe);
        vpCroSpe = (ViewPager) findViewById(R.id.vpCroSpe);
        lsMenu = new ArrayList<>();
        searchTypesAffections();
        tlCroSpe.setupWithViewPager(vpCroSpe);
    }
    //endregion

    //region Methods
    private void searchTypesAffections()
    {
        jarTypeAffection = new JsonArrayRequest(urlTypeAffection, new Response.Listener<JSONArray>()
        {
            @Override
            public void onResponse(JSONArray response)
            {
                JSONObject jsonObject = null;
                for (int index = 0; index < response.length(); index++)
                {
                    try
                    {
                        jsonObject = response.getJSONObject(index);
                        menu modMenu = new menu();
                        modMenu.setId(Integer.parseInt(jsonObject.getString("id")));
                        modMenu.setName(jsonObject.getString("nombre"));
                        lsMenu.add(modMenu);
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }

                setCreateMenu();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        rqCro = Volley.newRequestQueue(this);
        rqCro.add(jarTypeAffection);
    }

    private void setCreateMenu()
    {
        adViewPager myAdapter = new adViewPager(getSupportFragmentManager());
        myAdapter.addFragment(new frDescription(), "Información");
        frDescription.newInstance(idCrop);

        myAdapter.addFragment(new frDiseases(), lsMenu.get(0).getName());
        frDiseases.newInstance(idCrop,lsMenu.get(0).getId().toString(), lsMenu.get(0).getName());

        myAdapter.addFragment(new frPests(), lsMenu.get(1).getName());
        frPests.newInstance(idCrop,lsMenu.get(1).getId().toString(), lsMenu.get(1).getName());

        myAdapter.addFragment(new frWeeds(), lsMenu.get(2).getName());
        frWeeds.newInstance(idCrop,lsMenu.get(2).getId().toString(), lsMenu.get(0).getName());

        vpCroSpe.setAdapter(myAdapter);
    }
    //endregion
}