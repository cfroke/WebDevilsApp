package webdevils.webdevilsapp.Employee.ManageConcepts.RejectedTab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import webdevils.webdevilsapp.R;

/**
 * Created by Kevin on 3/28/2017.
 */
public class RejectedTab extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.emp_rejected_tab,null);
    }
}
