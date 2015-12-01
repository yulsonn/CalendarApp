package ru.julsdev.calendarapp.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;

import ru.julsdev.calendarapp.R;
import ru.julsdev.calendarapp.activity.MainActivity;
import ru.julsdev.calendarapp.adapter.MonthAdapter;

public class MonthFragment extends Fragment{

    private int monthNum;
    private static String[] months;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_month, container, false);
        monthNum = getArguments().getInt(MainActivity.ARG_MONTH_NUM);
        months = getResources().getStringArray(R.array.months);

        initRecyclerView(view);

        TextView monthTxt = (TextView) view.findViewById(R.id.month_txt);
        monthTxt.setText(months[monthNum]);

        return view;
    }

    private void initRecyclerView(View view) {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, monthNum);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int[] days = new int[daysInMonth];
        for (int i = 0; i < daysInMonth; i++) {
            days[i] = i+1;
        }

        int firstDayOfWeek = getFirsDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK));

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle_month);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 7);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);

        MonthAdapter adapter = new MonthAdapter(getContext(), days, firstDayOfWeek);
        recyclerView.setAdapter(adapter);
    }

    private int getFirsDayOfWeek(int dayNumber) {
        return dayNumber == 1 ? 6 : dayNumber - 2;
    }
}
