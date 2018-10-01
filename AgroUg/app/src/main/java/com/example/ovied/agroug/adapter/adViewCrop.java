
package com.example.ovied.agroug.adapter;

    //region Import
    import android.content.Context;
    import android.content.Intent;
    import android.support.annotation.NonNull;
    import android.support.v7.widget.RecyclerView;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ImageView;
    import android.widget.LinearLayout;
    import android.widget.TextView;
    import com.bumptech.glide.Glide;
    import com.bumptech.glide.request.RequestOptions;
    import com.example.ovied.agroug.model.crop;
    import com.example.ovied.agroug.activities.cropActivity;
    import java.util.List;
    import com.example.ovied.agroug.R;
    //endregion

public class adViewCrop extends RecyclerView.Adapter<adViewCrop.MyViewHolder>
{
    //region Variables
    private Context myContext;
    private List<crop> lsCrop;
    RequestOptions ro;
    //endregion

    //region Eventos
    public adViewCrop(Context myContext, List<crop> lisCrop)
    {
        this.myContext = myContext;
        this.lsCrop = lisCrop;
        ro= new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view;
        LayoutInflater inflater = LayoutInflater.from(myContext);
        view= inflater.inflate(R.layout.crop_row_item,parent,false);
        final MyViewHolder viewHolder = new MyViewHolder(view);

       viewHolder.llCrop.setOnClickListener(new View.OnClickListener()
       {
           @Override
           public void onClick(View view)
           {
               Intent i =new Intent(myContext, cropActivity.class);
               i.putExtra("parId", lsCrop.get(viewHolder.getAdapterPosition()).getId().toString());
               i.putExtra("parName", lsCrop.get(viewHolder.getAdapterPosition()).getName());
               i.putExtra("parRouteImage", lsCrop.get(viewHolder.getAdapterPosition()).getRouteImage());
               myContext.startActivity(i);
           }
       });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        holder.tvNameCro.setText(lsCrop.get(position).getName());
        holder.tvNameScientificCro.setText(lsCrop.get(position).getNameScientific());
        holder.tvReviewCro.setText(lsCrop.get(position).getReview());
        Glide.with(myContext).load(lsCrop.get(position).getRouteImage()).apply(ro).into(holder.ivRouteImageCro);
    }

    @Override
    public int getItemCount()
    {
        return lsCrop.size();
    }

    public static  class MyViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout llCrop;
        TextView tvNameCro;
        TextView tvNameScientificCro;
        TextView tvReviewCro;
        ImageView ivRouteImageCro;

        public  MyViewHolder(View itemView )
        {
            super(itemView);

            llCrop=itemView.findViewById(R.id.llCrop);
            tvNameCro= itemView.findViewById(R.id.tvNameCro);
            tvNameScientificCro=itemView.findViewById(R.id.tvNameScientificCro);
            tvReviewCro=itemView.findViewById(R.id.tvReviewCro);
            ivRouteImageCro = itemView.findViewById(R.id.ivRouteImageCro);
        }
    }
    //endregion
}


