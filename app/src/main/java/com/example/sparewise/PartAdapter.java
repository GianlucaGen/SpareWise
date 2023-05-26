package com.example.sparewise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class PartAdapter extends ArrayAdapter<Part> {

    private Context context;
    private List<Part> partList;

    public PartAdapter(Context context, List<Part> partList) {
        super(context, 0, partList);
        this.context = context;
        this.partList = partList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_part, parent, false);
        }

        TextView textViewPartName = convertView.findViewById(R.id.textViewPartName);
        TextView textViewPartDescription = convertView.findViewById(R.id.textViewPartDescription);
        TextView textViewCarMake = convertView.findViewById(R.id.textViewCarMake);
        TextView textViewYear = convertView.findViewById(R.id.textViewYear);
        TextView textViewPrice = convertView.findViewById(R.id.textViewPrice);
        TextView textViewMobileNumber = convertView.findViewById(R.id.textViewMobileNumber);

        Part part = partList.get(position);

        textViewPartName.setText(part.getPartName());
        textViewPartDescription.setText(part.getPartDescription());
        textViewCarMake.setText(part.getCarMake());
        textViewYear.setText(part.getYear());
        textViewPrice.setText(part.getPrice());
        textViewMobileNumber.setText(part.getMobileNumber());

        return convertView;
    }
}
