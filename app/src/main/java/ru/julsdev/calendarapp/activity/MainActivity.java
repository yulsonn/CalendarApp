package ru.julsdev.calendarapp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.Calendar;

import ru.julsdev.calendarapp.R;
import ru.julsdev.calendarapp.adapter.MonthSpinnerAdapter;
import ru.julsdev.calendarapp.fragment.MonthFragment;

public class MainActivity extends AppCompatActivity{

    public static final String ARG_MONTH_NUM = "month_number";
    public static String[] months;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int initMonthNum = Calendar.getInstance().get(Calendar.MONTH);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        FragmentStatePagerAdapter adapterViewPager = new MonthPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPager);
        viewPager.setCurrentItem(initMonthNum);

        initSpinner(viewPager, initMonthNum);
    }

    private void initSpinner(final ViewPager viewPager, int initMonthNum) {
        months = getResources().getStringArray(R.array.months);
        final Spinner monthSpinner = (Spinner) findViewById(R.id.spinner);
        MonthSpinnerAdapter spinAdapter = new MonthSpinnerAdapter(getApplicationContext(), months);
        monthSpinner.setAdapter(spinAdapter);
        monthSpinner.setSelection(initMonthNum);

        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                viewPager.setCurrentItem(position, true);
                monthSpinner.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                monthSpinner.setSelection(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private static class MonthPagerAdapter extends FragmentStatePagerAdapter {
        private static int NUM_ITEMS = 12;

        public MonthPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            MonthFragment monthFragment = new MonthFragment();
            Bundle monthArg = new Bundle();
            monthArg.putInt(ARG_MONTH_NUM, position);
            monthFragment.setArguments(monthArg);
            return monthFragment;
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }
    }
}
