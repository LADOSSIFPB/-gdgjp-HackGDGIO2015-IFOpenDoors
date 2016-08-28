package ifpb.edu.br.carcatalogbrowser_emanuel;

import android.app.Fragment;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.DotsPageIndicator;
import android.support.wearable.view.FragmentGridPagerAdapter;
import android.support.wearable.view.GridViewPager;
import android.widget.ImageView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class LabLibrary extends FragmentActivity {

    private static final int IDX_NAME = 0;
    private static final int IDX_DESCRIPTION = 1;
    private static final int IDX_BACKGROUND = 2;

    private DotsPageIndicator mPageIndicator;
    private GridViewPager mViewPager;
    private ImageView mImageView;

    private static String[][] mLabName;
    private static Drawable[] mLabBackground;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        TypedArray ta = res.obtainTypedArray(R.array.labs);
        int n = ta.length();
        mLabName = new String[n][];
        mLabBackground = new Drawable[n];

        //essa parte que ta dando erro professor...
        /*for (int i = 0; i < n; ++i) {
            int N=2;
            mLabBackground[i] = res.obtainTypedArray(ta.getResourceId(i, 0))
                    .getDrawable(2);
            mLabName[i] = res.getStringArray(ta.getResourceId(i, 0));
        }*/
        ta.recycle();

        mImageView = (ImageView) findViewById(R.id.lab_imageview);
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
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "LabLibrary Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://ifpb.edu.br.carcatalogbrowser_emanuel/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "LabLibrary Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://ifpb.edu.br.carcatalogbrowser_emanuel/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
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