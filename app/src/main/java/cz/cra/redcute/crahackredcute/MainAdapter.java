package cz.cra.redcute.crahackredcute;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;

import cz.cra.redcute.crahackredcute.model.Device;

/**
 * Created by obrusvit on 25.11.16.
 */

public class MainAdapter extends ArrayAdapter<Device> {

    private static class ViewHolder{
        private TextView projectId;
        private TextView description;
    }

    public MainAdapter(Context context, ArrayList<Device> projects) {
        super(context, 0, projects);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Device device = getItem(position);

        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_layout, parent, false);
            viewHolder.projectId = (TextView) convertView.findViewById(R.id.listItemProjectId);
            viewHolder.description = (TextView) convertView.findViewById(R.id.listItemPlaceOfTheDevice);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.projectId.setText(device.getDevEUI());
        viewHolder.description.setText(device.getName());


        return convertView;


    }
}
