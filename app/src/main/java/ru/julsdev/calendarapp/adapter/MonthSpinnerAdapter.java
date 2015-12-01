package ru.julsdev.calendarapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import ru.julsdev.calendarapp.R;


public class MonthSpinnerAdapter extends ArrayAdapter<String> {

    private String[] months;
    private LayoutInflater inflater;

    public MonthSpinnerAdapter(Context context, String[] months) {
        super(context, R.layout.spinner_row, months);

        this.months = months;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, parent);
    }

    public View getCustomView(int position, ViewGroup parent) {
        View row = inflater.inflate(R.layout.spinner_row, parent, false);
        TextView tvMonth = (TextView) row.findViewById(R.id.tvCategory);
        tvMonth.setText(months[position]);

        return row;
    }

}
