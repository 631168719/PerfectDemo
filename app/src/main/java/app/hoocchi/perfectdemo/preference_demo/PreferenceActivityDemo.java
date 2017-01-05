package app.hoocchi.perfectdemo.preference_demo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.MultiSelectListPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;

import app.hoocchi.perfectdemo.R;

public class PreferenceActivityDemo extends PreferenceActivity {

    public static final String TYPE = "type";
    public static final int TYPE_XML = 0 ;
    public static final int TYPE_CODE = 1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int type = getIntent().getIntExtra(TYPE , TYPE_XML);

        if(type == TYPE_XML){
            addPreferencesFromResource(R.xml.preference_demo_xml);
        }else if(type == TYPE_CODE){
            addPreferencesFromCode();
        }
    }

    private void addPreferencesFromCode() {
        PreferenceScreen screenRoot = getPreferenceManager().createPreferenceScreen(this);
        setPreferenceScreen(screenRoot);
        addChildPreferences(screenRoot);
    }

    private void addChildPreferences(PreferenceScreen screenRoot) {

        //TwoStatePreference
        PreferenceCategory twoStateCategory = new PreferenceCategory(this);
        twoStateCategory.setTitle("Two State Preference");
        screenRoot.addPreference(twoStateCategory);

        CheckBoxPreference cbPreference = new CheckBoxPreference(this);
        cbPreference.setKey(getString(R.string.checkbox_key));
        cbPreference.setTitle("CheckBox Title");
        cbPreference.setSummary("CheckBox Summary");
        twoStateCategory.addPreference(cbPreference);

        SwitchPreference switchPreference = new SwitchPreference(this);
        switchPreference.setKey(getString(R.string.switch_key));
        switchPreference.setTitle("Switch Title");
        switchPreference.setSummary("Switch Summay");
        twoStateCategory.addPreference(switchPreference);


        //DialogPreference
        PreferenceCategory dialogCategory = new PreferenceCategory(this);
        dialogCategory.setTitle("Dialog Preference");
        screenRoot.addPreference(dialogCategory);

        EditTextPreference editPreference = new EditTextPreference(this);
        editPreference.setKey(getString(R.string.edittext_key));
        editPreference.setTitle("EdiText Title");
        editPreference.setSummary("EdiText Summary");
        editPreference.setDialogTitle("Please Input Your Number Password");
        dialogCategory.addPreference(editPreference);

        ListPreference listPreference = new ListPreference(this);
        listPreference.setKey(getString(R.string.list_key));
        listPreference.setTitle("List Title");
        listPreference.setSummary("List Summary");
        listPreference.setEntries(getResources().getStringArray(R.array.list_entry));
        listPreference.setEntryValues(getResources().getStringArray(R.array.list_entry_values));
        dialogCategory.addPreference(listPreference);

        MultiSelectListPreference multiListPreference = new MultiSelectListPreference(this);
        multiListPreference.setKey(getString(R.string.multi_list_key));
        multiListPreference.setTitle("Multi List Title");
        multiListPreference.setSummary("Multi List Summay");
        multiListPreference.setEntries(getResources().getStringArray(R.array.list_entry));
        multiListPreference.setEntryValues(getResources().getStringArray(R.array.list_entry_values));
        dialogCategory.addPreference(multiListPreference);


        //IntentPreference
        PreferenceCategory intentCategory = new PreferenceCategory(this);
        intentCategory.setTitle("Intent Preference");
        screenRoot.addPreference(intentCategory);

        PreferenceScreen nextScreen = getPreferenceManager().createPreferenceScreen(this);
        nextScreen.setTitle("Next Screen Preference");
        nextScreen.setSummary("Click it to Another Screen");
        intentCategory.addPreference(nextScreen);

        PreferenceCategory nextScreenCategory = new PreferenceCategory(this);
        nextScreenCategory.setTitle("Next Screen CheckBox");
        nextScreen.addPreference(nextScreenCategory);

        CheckBoxPreference nextCheckBox = new CheckBoxPreference(this);
        nextCheckBox.setTitle("Next Screen CheckBox");
        nextCheckBox.setSummary("This is a Next Screen CheckBox Summary");
        nextCheckBox.setChecked(true);
        nextScreenCategory.addPreference(nextCheckBox);


        PreferenceScreen jumpIntent = getPreferenceManager().createPreferenceScreen(this);
        jumpIntent.setTitle("Jump Intent Preference");
        jumpIntent.setSummary("Click it to Jump Activity");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.baidu.com"));
        jumpIntent.setIntent(intent);
        intentCategory.addPreference(jumpIntent);


        //DepencyPreference
        PreferenceCategory depencyCategory = new PreferenceCategory(this);
        depencyCategory.setTitle("Depency Preference");
        screenRoot.addPreference(depencyCategory);

        CheckBoxPreference parentCheckBox = new CheckBoxPreference(this);
        parentCheckBox.setKey(getString(R.string.parent_key));
        parentCheckBox.setTitle("CheckBox Parent Title");
        parentCheckBox.setSummary("CheckBox Parent Summary");
        parentCheckBox.setChecked(false);
        depencyCategory.addPreference(parentCheckBox);

        CheckBoxPreference childCheckBox = new CheckBoxPreference(this);
        childCheckBox.setKey(getString(R.string.child_key));
        childCheckBox.setTitle("CheckBox Child Title");
        childCheckBox.setSummary("CheckBox Child Summary");
        childCheckBox.setChecked(true);
        depencyCategory.addPreference(childCheckBox);
        //该方法必须在addPreference()之后调用
        childCheckBox.setDependency(getString(R.string.parent_key));

    }
}
