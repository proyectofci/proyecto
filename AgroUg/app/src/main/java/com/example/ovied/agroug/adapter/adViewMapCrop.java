package com.example.ovied.agroug.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.ovied.agroug.R;
import com.example.ovied.agroug.model.crop;
import java.util.List;

public class adViewMapCrop  extends ArrayAdapter<crop>
{

    //region Events
    public adViewMapCrop(Context context, List<crop> objects)
    {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // Obtener inflater.
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // ¿Existe el view actual?
        if (null == convertView)
        {
            convertView = inflater.inflate( R.layout.mapcrop_row_item, parent,false);
        }
        // Referencias UI.
        TextView name = (TextView) convertView.findViewById(R.id.tvNameCro);

        // Lead actual.
        crop modCrop = getItem(position);

        // Setup.
        name.setText(modCrop.getName());

        return convertView;
    }
    //endregion


}
