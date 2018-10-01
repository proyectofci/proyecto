package com.example.ovied.agroug.adapter;

    //region Import
    import android.app.Activity;
    import android.app.Dialog;

    import android.support.design.widget.CollapsingToolbarLayout;
    import android.support.design.widget.TabLayout;
    import android.support.v4.app.FragmentActivity;
    import android.support.v4.view.ViewPager;
    import android.support.v7.app.AppCompatActivity;
    import android.support.v7.widget.RecyclerView;
    import android.text.Html;
    import android.text.SpannableString;
    import android.text.style.UnderlineSpan;
    import android.util.DisplayMetrics;
    import android.view.Display;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.view.Window;
    import android.widget.AdapterView;
    import android.widget.ArrayAdapter;
    import android.widget.ImageView;
    import android.widget.LinearLayout;
    import android.widget.ListView;
    import android.widget.TextView;
    import com.android.volley.RequestQueue;
    import com.android.volley.Response;
    import com.android.volley.VolleyError;
    import com.android.volley.toolbox.JsonArrayRequest;
    import com.android.volley.toolbox.JsonObjectRequest;
    import com.android.volley.toolbox.Volley;
    import com.bumptech.glide.Glide;
    import com.bumptech.glide.request.RequestOptions;
    import com.example.ovied.agroug.R;
    import com.example.ovied.agroug.fragment.frDescription;
    import com.example.ovied.agroug.fragment.frInformation;
    import com.example.ovied.agroug.model.affection;
    import com.example.ovied.agroug.model.treatment;
    import org.json.JSONArray;
    import org.json.JSONException;
    import org.json.JSONObject;
    import java.util.ArrayList;
    import java.util.List;
    //endregion

public class adViewAffection extends  RecyclerView.Adapter<adViewAffection.Myholder>
{
    //region Variables
    private JsonArrayRequest jarTreatment;
    private JsonObjectRequest jarTreatmentSpe;
    private List<treatment> lsTreatment;
    private RequestQueue rqTreatment;
    private RequestQueue rqTreatmentSpe;
    private static String urlTreatment="http://34.237.236.90:8080/agroug/api/tratamientos/afeccion/[X_AFFECTION_ID]";
    private static String urlTreatmentTemp="";
    private static String urlTreatmentSpe="http://34.237.236.90:8080/agroug/api/tratamientos/[X_TREATMENT_ID]";
    private static String urlTreatmentSpeTemp="";
    private ListView lvTreatmentLs;
    private Activity myActivity;
    private FragmentActivity myContext;
    private List<affection> lsAffection;
    TextView tvCloseTre;
    TextView tvDescriptionAffInf;
    TextView tvInformationTre;
    TextView tvNameTre;
    TextView tvNameAffInf;

    ImageView ivAff;
    ArrayAdapter<treatment> aaTreatment;
    RequestOptions ro;
    private ViewPager vpAffSpe;
    private TabLayout tlAffSpe;
    //endregion

    //region Eventos
    public adViewAffection(Activity myActivity, List<affection> lsAffection)
    {
        this.myActivity = myActivity;
        this.lsAffection = lsAffection;
        ro= new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }


    @Override
    public Myholder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.afecction_row_item, parent, false);
        return new adViewAffection.Myholder(view);
    }

    @Override
    public void onBindViewHolder(final Myholder holder, int position)
    {
        holder.tvNameAff.setText(lsAffection.get(position).getName());
        Glide.with(myActivity).load(lsAffection.get(position).getRouteImage()).apply(ro).into(holder.ivRouteImageAff);

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                lsTreatment = new ArrayList<>();
                int currentPosition = getClickedPosition(v);
                int idAffection = lsAffection.get(currentPosition).getId();
                String ImageAff = lsAffection.get(currentPosition).getRouteImage();
                searchTreatment(idAffection);

                DisplayMetrics metrics = myActivity.getResources().getDisplayMetrics();
                int DeviceTotalWidth = metrics.widthPixels;
                int DeviceTotalHeight = metrics.heightPixels;

                Dialog dialog = new Dialog(myActivity);

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.popup_treatment);
                dialog.getWindow().setLayout(DeviceTotalWidth, (DeviceTotalHeight * 95) / 100);

                CollapsingToolbarLayout collapsingToolbarLayout = dialog.findViewById(R.id.ctlAffSpe);
                collapsingToolbarLayout.setTitleEnabled(true);

                ivAff = (ImageView) dialog.findViewById(R.id.ivRouteImageAffSpe);
                Glide.with(myActivity).load(ImageAff).into(ivAff);
                tvInformationTre=(TextView) dialog.findViewById(R.id.tvInformationTre);
                tvNameTre=(TextView)dialog.findViewById(R.id.tvNameTre);
                tvNameAffInf = (TextView) dialog.findViewById(R.id.tvNameAffInf);
                tvNameAffInf.setText(lsAffection.get(currentPosition).getNavegation());
                tvDescriptionAffInf= (TextView) dialog.findViewById(R.id.tvDescriptionAffInf);
                tvDescriptionAffInf.setText(Html.fromHtml( generateDescription(lsAffection.get(currentPosition).getNameScientific() , lsAffection.get(currentPosition).getDescription())));

                lvTreatmentLs = (ListView) dialog.findViewById(R.id.lvTreatmentLs);

                lvTreatmentLs.setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
                    {
                        treatment modTreatment = aaTreatment.getItem(position);
                        searchTreatmentSpecific(modTreatment.getId());
                    }
                });




                dialog.show();
            }
        });


    }



    private void setCreateMenu(String idAffection,Dialog dialog)
    {


        adViewPageTreatment myAdapter1 = new adViewPageTreatment(((AppCompatActivity)myActivity).getSupportFragmentManager());
        myAdapter1.addFragment(new frInformation(), "Información");
        frInformation.newInstance(idAffection);
        vpAffSpe.setAdapter(myAdapter1);
    }



    @Override
    public int getItemCount()
    {
        return  lsAffection.size();
    }

    private int getClickedPosition(View clickedView)
    {
        RecyclerView recyclerView = (RecyclerView) clickedView.getParent();
        adViewAffection.Myholder currentViewHolder = (adViewAffection.Myholder) recyclerView.getChildViewHolder(clickedView);
        return currentViewHolder.getAdapterPosition();
    }

    public class Myholder extends RecyclerView.ViewHolder
    {
        TextView tvNameAff;
        ImageView ivRouteImageAff;
        LinearLayout llAffLis;

        public  Myholder(View itemView)
        {
            super(itemView);

            llAffLis=itemView.findViewById(R.id.llAffLis);
            tvNameAff= itemView.findViewById(R.id.tvNameAff);
            ivRouteImageAff = itemView.findViewById(R.id.ivRouteImageAff);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {

                }
            });
        }
    }
    //endregion

    //region Methods
    private void searchTreatment(int idAffection)
    {
        urlTreatmentTemp= urlTreatment.replace("[X_AFFECTION_ID]",String.valueOf(idAffection));

        jarTreatment = new JsonArrayRequest(urlTreatmentTemp, new Response.Listener<JSONArray>()
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
                        treatment modTreatment = new treatment();
                        modTreatment.setId(jsonObject.getInt("id"));
                        modTreatment.setName(jsonObject.getString("nombreProducto"));
                        lsTreatment.add(modTreatment);
                    }
                    catch (JSONException ex)
                    {
                        ex.printStackTrace();
                    }
                }
                setTreatment(lsTreatment);

            }
        }
        , new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {

            }
        });
        rqTreatment= Volley.newRequestQueue(myActivity);
        rqTreatment.add(jarTreatment);
    }

    private void searchTreatmentSpecific(int idTreatment)
    {
        urlTreatmentSpeTemp= urlTreatmentSpe.replace("[X_TREATMENT_ID]",String.valueOf(idTreatment));

        jarTreatmentSpe = new JsonObjectRequest(urlTreatmentSpeTemp,null, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                treatment modTreatment = new treatment();
                try
                {
                    modTreatment.setId(response.getInt("id"));
                    modTreatment.setName("Tratamiento: "+response.getString("nombreProducto"));
                    modTreatment.setPhytosanitaryAction(response.getString("acccionFitosanitaria"));
                    modTreatment.setApplicationDose(response.getString("aplicacionDosis"));
                    modTreatment.setApplicationMode (response.getString("aplicacionModo"));
                    modTreatment.setAmountDose(response.getString("cantidadDosis"));

                }
                catch (JSONException ex)
                {
                    ex.printStackTrace();
                }
                setTreatmentId(modTreatment);
            }
        }
        , new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {

            }
        });
        rqTreatmentSpe= Volley.newRequestQueue(myActivity);
        rqTreatmentSpe.add(jarTreatmentSpe);
    }

    private void setTreatment(List<treatment> lsTreatment)
    {

        aaTreatment = new adViewTreatment(myActivity,lsTreatment);
        lvTreatmentLs.setAdapter(aaTreatment);
       if(aaTreatment.getCount()==1)
        {
            treatment modTreatment = aaTreatment.getItem(0);
            searchTreatmentSpecific(modTreatment.getId());
        }
    }

    private void setTreatmentId(treatment modTreatment)
    {
         /*   SpannableString myText = new SpannableString(modTreatment.getName());
            myText.setSpan(new UnderlineSpan(), 0, myText.length(), 0);
           // tvTitleTre.setText(myText);*/
            tvInformationTre.setText(Html.fromHtml(generateTemplate(modTreatment)));
            tvNameTre.setText(modTreatment.getName());
    }

    public String generateTemplate(treatment item)
    {
        String template;
        template="<html><div align=\"justify\">\n" +
                "\n" +
                " <b><u>Descripción</u></b> <br>\n" +
                "  [X_DESCRIPCION]\n" +
                "  <br><br><b><u>Dosis:</u></b> <br>\n" +
                "  [X_APLICACION_DOSIS]\n" +
                " <br><br><b><u>Modo de aplicación:</u></b> <br>\n" +
                " [X_APLICACION_MODO]\n" +
                " <br><br><b><u>Cantidad de dosis:</u></b> <br>\n" +
                "  [X_CANTIDAD_DOSIS]\n" +
                "</div></html>";
        template=  template.replace("[X_DESCRIPCION]",item.getPhytosanitaryAction());
        template=  template.replace("[X_APLICACION_DOSIS]",item.getApplicationDose());
        template=  template.replace("[X_APLICACION_MODO]",item.getApplicationMode());
        template=  template.replace("[X_CANTIDAD_DOSIS]",item.getAmountDose());

        return template;
    }

    public String generateDescription(String nameScientific, String description )
    {
        String template;
        template="<html>\n" +
                "\n" +
                " <b>Nombre Cientifico:</b> [X_NAME_SCIENTIFIC]\n" +
                "  <br><br> [X_DESCRIPCION]" +
                "</html>";
        template=  template.replace("[X_NAME_SCIENTIFIC]",nameScientific);
        template=  template.replace("[X_DESCRIPCION]",description);


        return template;
    }
    //endregion
}

