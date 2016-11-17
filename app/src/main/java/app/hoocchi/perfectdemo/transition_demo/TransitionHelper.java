package app.hoocchi.perfectdemo.transition_demo;

import android.app.Activity;
import android.support.v4.util.Pair;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by st on 2016/11/4.
 */
public class TransitionHelper {
    private TransitionHelper() {

    }

    public static Pair<View, String>[] createSafeTransitionPairs(Activity activity,
                                                                 boolean includeStatusBar,
                                                                 Pair... otherPairs) {
        View decorView = activity.getWindow().getDecorView();
        View statusBar = null;

        if (includeStatusBar) {
            statusBar = decorView.findViewById(android.R.id.statusBarBackground);
        }

        View navbar = decorView.findViewById(android.R.id.navigationBarBackground);

        List<Pair> pairList = new ArrayList<>(3);

        if (statusBar != null) {
            pairList.add(new Pair(statusBar, statusBar.getTransitionName()));
        }

        if (navbar != null) {
            pairList.add(new Pair(navbar, navbar.getTransitionName()));
        }

        //保证至少有一个
        if (otherPairs != null && !(otherPairs.length == 1 && otherPairs[0] == null)) {
            pairList.addAll(Arrays.asList(otherPairs));
        }

        return pairList.toArray(new Pair[pairList.size()]);
    }


}
