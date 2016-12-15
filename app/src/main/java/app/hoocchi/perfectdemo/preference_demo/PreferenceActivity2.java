package app.hoocchi.perfectdemo.preference_demo;

import android.app.Fragment;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import app.hoocchi.perfectdemo.R;

public class PreferenceActivity2 extends AppCompatActivity {

    private Fragment mCurrentFragment = null ;
    private static ActionBar mAb ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        mAb = getSupportActionBar() ;
        mAb.setDisplayHomeAsUpEnabled(true);

        if(savedInstanceState == null){
            replace(SettingsFragment.newInstance() , "设置");
        }

    }

    public void replace(Fragment fragment , String title){
        if(mCurrentFragment == null){
            getFragmentManager().beginTransaction()
                .replace(R.id.preference_content , fragment).commit();
        }else{
            getFragmentManager().beginTransaction()
                    .replace(R.id.preference_content , fragment)
                    .addToBackStack(null)
                    .commit();
        }
        mAb.setTitle(title);
        mCurrentFragment = fragment;
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(mCurrentFragment instanceof ThemeChangeFragment){
            mAb.setTitle("设置");
        }
    }

    public static class SettingsFragment extends PreferenceFragment {

        public static SettingsFragment newInstance(){
            return new SettingsFragment();
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.preference_fragment_xml);

            Preference clearPref = findPreference("key_clear_cache");
            clearPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    Toast.makeText(getActivity(), "清除缓存", Toast.LENGTH_SHORT).show();

                    preference.setSummary("包括图片、音频缓存");
                    return false;
                }
            });

            Preference changeTheme = findPreference("key_change_theme");
            changeTheme.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    PreferenceActivity2 activity = (PreferenceActivity2) getActivity();
                    activity.replace(ThemeChangeFragment.newInstance() , "设置主题");
                    return false;
                }
            });

        }
    }

    public static class ThemeChangeFragment extends PreferenceFragment{

        public static ThemeChangeFragment newInstance(){
            return new ThemeChangeFragment();
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.preference_change_theme);

        }
    }
}
