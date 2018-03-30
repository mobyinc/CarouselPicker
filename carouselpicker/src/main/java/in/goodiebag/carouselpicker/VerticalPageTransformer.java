package in.goodiebag.carouselpicker;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by corey on 3/30/18.
 */

public class VerticalPageTransformer implements ViewPager.PageTransformer {

    private ViewPager viewPager;

    @Override
    public void transformPage(View page, float position) {
        if (viewPager == null) {
            viewPager = (ViewPager) page.getParent();
        }

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            page.setAlpha(0);

        } else if (position <= 1) { // [-1,1]
            page.setAlpha(1);

            // Counteract the default slide transition
            page.setTranslationX(page.getWidth() * -position);

            //set Y position to swipe in from top
            float yPosition = position * page.getHeight();
            page.setTranslationY(yPosition);

            page.setScaleY(1-Math.abs(position));
            page.setScaleX(1-Math.abs(position));

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            page.setAlpha(0);
        }
    }
}
