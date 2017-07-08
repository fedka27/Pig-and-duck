package app.meat.view.adapter.base;


import android.view.ViewGroup;

import app.meat.R;
import app.meat.view.base.BaseRecyclerViewHolder;


public class ProgressHolder extends BaseRecyclerViewHolder {
    public static String TYPE = ProgressHolder.class.getSimpleName();

    public ProgressHolder(ViewGroup parent) {
        super(parent, R.layout.cell_progress_bar);
    }
}
