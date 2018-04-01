package com.reid.cocoon.ui.activity;

import android.os.Bundle;

import com.reid.cocoon.R;
import com.reid.cocoon.ui.fragment.SettingsFragment;

public class SettingsActivity extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initToolbar(R.string.settings);

        getFragmentManager().beginTransaction()
                .replace(R.id.content_layout, new SettingsFragment(), "SettingsFragment")
                .commitAllowingStateLoss();
    }
}
