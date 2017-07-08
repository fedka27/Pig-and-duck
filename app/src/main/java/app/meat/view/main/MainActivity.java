package app.meat.view.main;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import app.meat.R;
import app.meat.injection.ComponentProvider;
import app.meat.model.dto.News;
import app.meat.view.base.BaseActivity;
import butterknife.BindView;

public class MainActivity extends BaseActivity
        implements MainContract.View {
    @Inject
    protected MainContract.Presenter presenter;
    @BindView(R.id.news_recycler_view)
    RecyclerView newsRecyclerView;
    private NewsAdapter newsAdapter;
    private ProgressDialog progressDialog;

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
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initPresenter();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initProgressDialog();
        initAdapter();
    }

    private void initProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(R.string.progress_dialog_title);
        progressDialog.setMessage(getString(R.string.progress_dialog_message));
        progressDialog.setCancelable(false);
    }

    private void initAdapter() {
        newsAdapter = new NewsAdapter();
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        newsRecyclerView.setAdapter(newsAdapter);
    }

    @Override
    public void showProgressDialog() {
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void hideProgressDialog() {
        if (progressDialog.isShowing()) {
            progressDialog.hide();
        }
    }

    @Override
    public void setNews(List<News> list) {
        newsAdapter.setNews(list);
    }
}
