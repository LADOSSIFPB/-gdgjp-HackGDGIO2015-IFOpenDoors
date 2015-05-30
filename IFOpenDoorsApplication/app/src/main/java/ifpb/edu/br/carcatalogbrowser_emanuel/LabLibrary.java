package ifpb.edu.br.carcatalogbrowser_emanuel;

import android.app.Fragment;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.DotsPageIndicator;
import android.support.wearable.view.FragmentGridPagerAdapter;
import android.support.wearable.view.GridViewPager;
import android.widget.ImageView;

public class LabLibrary extends FragmentActivity {

    private static final int IDX_NAME = 0;
    private static final int IDX_DESCRIPTION = 1;
    private static final int IDX_BACKGROUND = 2;

    private DotsPageIndicator mPageIndicator;
    private GridViewPager mViewPager;
    private ImageView mImageView;

    private static String[][] mLabName;
    private static Drawable[] mLabBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        TypedArray ta = res.obtainTypedArray(R.array.labs);
        int n = ta.length();
        mLabName = new String[n][];
        mLabBackground = new Drawable[n];
        for (int i = 0; i < n; ++i) {
            mLabBackground[i] = res.obtainTypedArray(ta.getResourceId(i, 0))
                    .getDrawable(IDX_BACKGROUND);
            mLabName[i] = res.getStringArray(ta.getResourceId(i, 0));
        }
        ta.recycle();

        mImageView = (ImageView)findViewById(R.id.lab_imageview);
        mImageView.setScaleType(ImageView.ScaleType.FIT_START);
        mImageView.setBackgroundColor(Color.parseColor("gray"));
        mPageIndicator = (DotsPageIndicator) findViewById(R.id.page_indicator);
        mViewPager = (GridViewPager) findViewById(R.id.pager);

        mViewPager.setAdapter(new LabGridPagerAdapter(this));

        mPageIndicator.setPager(mViewPager);

        mViewPager.setOnPageChangeListener(new GridViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int row, int column, float rowOffset,
                                       float columnOffset, int rowOffsetPixels,
                                       int columnOffsetPixels) {
                // This is called when the scroll state changes
                mPageIndicator.onPageScrolled(row, column, rowOffset,
                        columnOffset, rowOffsetPixels, columnOffsetPixels);
            }

            @Override
            public void onPageSelected(int row, int column) {
                mImageView.setImageDrawable(mLabBackground[row]);
                mPageIndicator.onPageSelected(row, column);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                mPageIndicator.onPageScrollStateChanged(state);
            }
        });

        mImageView.setImageDrawable(mLabBackground[0]);
    }

    private static final class LabGridPagerAdapter extends FragmentGridPagerAdapter {

        private static final int LABORATORY = 0;
        private static final int OPEN = 1;
        private static final int CLOSE = 2;

        private static final int COLUMNS = 3;

        private LabGridPagerAdapter(FragmentActivity activity) {
            super(activity.getFragmentManager());
        }

        @Override
        public Fragment getFragment(int row, int column) {
            switch (column) {
                case LABORATORY:
                    final String title = mLabName[row][IDX_NAME];
                    final String description = mLabName[row][IDX_DESCRIPTION];
                    return CardFragment.create(title, description);
                case OPEN:
                    FragmentOpenDoor fragmentOpenDoor = new FragmentOpenDoor();
                    fragmentOpenDoor.setRow(row);
                    return fragmentOpenDoor;
                case CLOSE:
                    FragmentCloseDoor fragmentCloseDoor = new FragmentCloseDoor();
                    fragmentCloseDoor.setRow(row);
                    return fragmentCloseDoor;
                default:
                    throw new IllegalArgumentException("getFragment(row=" +
                            row + ", column=" + column + ")");
            }
        }

        @Override
        public int getRowCount() {
            return mLabName.length;
        }

        @Override
        public int getColumnCount(int row) {
            return COLUMNS;
        }
    }
}
