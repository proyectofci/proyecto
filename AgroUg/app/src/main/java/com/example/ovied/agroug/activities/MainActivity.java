package com.example.ovied.agroug.activities;

    //region Import
    import android.content.Intent;
    import android.os.Bundle;
    import android.support.v7.widget.LinearLayoutManager;
    import android.support.v7.widget.RecyclerView;
    import android.support.design.widget.NavigationView;
    import android.support.v4.view.GravityCompat;
    import android.support.v4.widget.DrawerLayout;
    import android.support.v7.app.ActionBarDrawerToggle;
    import android.support.v7.app.AppCompatActivity;
    import android.support.v7.widget.Toolbar;
    import android.view.Menu;
    import android.view.MenuItem;
    import com.android.volley.RequestQueue;
    import com.android.volley.Response;
    import com.android.volley.VolleyError;
    import com.android.volley.toolbox.JsonArrayRequest;
    import com.android.volley.toolbox.Volley;
    import com.example.ovied.agroug.adapter.adViewCrop;
    import com.example.ovied.agroug.model.crop;
    import com.example.ovied.agroug.R;
    import org.json.JSONArray;
    import org.json.JSONException;
    import org.json.JSONObject;
    import java.util.ArrayList;
    import java.util.List;
    //endregion

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    //region Variables
    private final static String urlCrop="http://34.237.236.90:8080/agroug/api/cultivos/phone";
    private JsonArrayRequest jarCrop;
    private RequestQueue rqCrop;
    private List<crop> lsCrop;
    private RecyclerView rvCropLis;
    //endregion

    //region Events
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        lsCrop = new ArrayList<>();
        rvCropLis= findViewById(R.id.rvCropLis);
        searchCrop();
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent i =new Intent(this,MainActivity.class);

            this.startActivity(i);

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

            Intent i =new Intent(this, activity_maps.class);

            this.startActivity(i);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    //endregion

    //region Methods
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
                        crop modCorp = new crop();
                        modCorp.setId(jsonObject.getInt("id"));
                        modCorp.setName(jsonObject.getString("nombre"));
                        modCorp.setNameScientific(jsonObject.getString("nombreCientifico"));
                        modCorp.setReview(jsonObject.getString("resena"));
                        modCorp.setRouteImage(jsonObject.getString("imagen"));
                        lsCrop.add(modCorp);
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }

                setRecyclerView(lsCrop);

            }
        }
        , new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {

            }
        });
        rqCrop= Volley.newRequestQueue(MainActivity.this);
        rqCrop.add(jarCrop);
    }

    private void setRecyclerView(List<crop> lsCrop)
    {
        adViewCrop myAdapter= new adViewCrop(this,lsCrop);
        rvCropLis.setLayoutManager(new LinearLayoutManager(this));
        rvCropLis.setAdapter(myAdapter);
    }
    //endregion
}
