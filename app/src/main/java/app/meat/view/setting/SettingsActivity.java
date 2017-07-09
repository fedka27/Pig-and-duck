package app.meat.view.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;

import java.util.List;

import javax.inject.Inject;

import app.meat.R;
import app.meat.injection.ComponentProvider;
import app.meat.model.data.repository.Category;
import app.meat.view.base.BaseActivity;
import butterknife.BindView;

public class SettingsActivity extends BaseActivity
        implements SettingsContract.View {
    @Inject
    protected SettingsContract.Presenter presenter;
    @BindView(R.id.category_recycler_view)
    RecyclerView recyclerView;
    private SettingAdapter settingAdapter;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, SettingsActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }

    private void initPresenter() {
        ComponentProvider.getInstance().getPresentersComponent().inject(this);
        presenter.bindView(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();

        setContentView(R.layout.activity_settings);

        initAdapter();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            presenter.onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initAdapter() {
        settingAdapter = new SettingAdapter(presenter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(settingAdapter);
    }

    @Override
    public void returnResult() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void setList(List<Category> categoriesEnumList) {
         settingAdapter.setCategories(categoriesEnumList);
    }
}
