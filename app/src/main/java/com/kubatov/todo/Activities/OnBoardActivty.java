package com.kubatov.todo.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kubatov.todo.R;

public class OnBoardActivty extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    public ViewPager mViewPager;
    private int dotsCount = 3;
    private ImageView[] dots;
    LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board_activty);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        dotsCount = mSectionsPagerAdapter.getCount();

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:

                        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.item_unselected));
                        dots[1].setImageDrawable(getResources().getDrawable(R.drawable.item_unselected));
                        dots[2].setImageDrawable(getResources().getDrawable(R.drawable.item_unselected));
                        dots[position].setImageDrawable(getResources().getDrawable(R.drawable.item_selected));
                        break;

                    case 1:
                        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.item_unselected));
                        dots[1].setImageDrawable(getResources().getDrawable(R.drawable.item_unselected));
                        dots[2].setImageDrawable(getResources().getDrawable(R.drawable.item_unselected));
                        dots[position].setImageDrawable(getResources().getDrawable(R.drawable.item_selected));
                        break;

                    case 2:
                        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.item_unselected));
                        dots[1].setImageDrawable(getResources().getDrawable(R.drawable.item_unselected));
                        dots[2].setImageDrawable(getResources().getDrawable(R.drawable.item_unselected));
                        dots[position].setImageDrawable(getResources().getDrawable(R.drawable.item_selected));
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        linearLayout = (LinearLayout) findViewById(R.id.viewPagerCountDots);
        dots = new ImageView[dotsCount];
        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            if (i == 0)
                dots[i].setImageDrawable(getResources().getDrawable(R.drawable.item_selected));
            else
                dots[i].setImageDrawable(getResources().getDrawable(R.drawable.item_unselected));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(4, 0, 4, 0);
            linearLayout.addView(dots[i], params);

        }

    }

    public void onButtonClicks(View view) {
        switch (view.getId()) {
            case R.id.buttonBack:
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1, true);
                break;
            case R.id.buttonNext:
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
                break;

        }
    }


    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_on_board_activty, container, false);

            int number = getArguments().getInt(ARG_SECTION_NUMBER);
            TextView textView = rootView.findViewById(R.id.textTitle);
            ImageView imageView = rootView.findViewById(R.id.image_view);
            Button button = rootView.findViewById(R.id.buttonClick);
            ImageButton buttonBack = rootView.findViewById(R.id.buttonBack);
            ImageButton buttonNext = rootView.findViewById(R.id.buttonNext);

            switch (number) {
                case 0:
                    buttonBack.setVisibility(View.INVISIBLE);
                    buttonNext.setVisibility(View.VISIBLE);
                    button.setVisibility(View.GONE);
                    imageView.setImageResource(R.drawable.anchor);
                    textView.setText("Hello");
                    rootView.setBackgroundColor(Color.GREEN);
                    break;

                case 1:
                    buttonBack.setVisibility(View.VISIBLE);
                    buttonNext.setVisibility(View.VISIBLE);
                    button.setVisibility(View.GONE);
                    imageView.setImageResource(R.drawable.caesar);
                    textView.setText("How are you?");
                    rootView.setBackgroundColor(Color.LTGRAY);
                    break;

                case 2:
                    buttonNext.setVisibility(View.GONE);
                    buttonBack.setVisibility(View.VISIBLE);
                    button.setVisibility(View.VISIBLE);
                    imageView.setImageResource(R.drawable.anglerfish);
                    textView.setText("Bye");
                    rootView.setBackgroundColor(Color.YELLOW);
                    break;
            }

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getContext(), MainActivity.class));
                    getActivity().finish();
                }
            });
            return rootView;

        }


    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 3;
        }
    }


}