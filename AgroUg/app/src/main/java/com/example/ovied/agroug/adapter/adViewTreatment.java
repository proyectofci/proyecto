package com.example.ovied.agroug.adapter;

    //region Import
    import android.content.Context;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ArrayAdapter;
    import android.widget.TextView;
    import com.example.ovied.agroug.R;
    import com.example.ovied.agroug.model.treatment;
    import java.util.List;
    //endregion

public class adViewTreatment extends ArrayAdapter<treatment>
{
    //region Events
    public adViewTreatment(Context context, List<treatment> objects)
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
            convertView = inflater.inflate( R.layout.treatment_row_item, parent,false);
        }
        // Referencias UI.
        TextView name = (TextView) convertView.findViewById(R.id.tvNameTre);

        // Lead actual.
        treatment modTreatment = getItem(position);

        // Setup.
        name.setText(modTreatment.getName());

        return convertView;
    }
    //endregion
}
