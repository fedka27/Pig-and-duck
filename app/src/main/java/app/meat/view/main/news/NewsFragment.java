package app.meat.view.main.news;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import app.meat.R;
import app.meat.injection.ComponentProvider;
import app.meat.model.dto.News;
import app.meat.view.base.BaseFragment;
import butterknife.BindView;


public class NewsFragment extends BaseFragment implements NewsContract.View {
    @Inject
    protected NewsContract.Presenter presenter;
    @BindView(R.id.news_recycler_view)
    RecyclerView newsRecyclerView;
    private NewsAdapter newsAdapter;
    private ProgressDialog progressDialog;

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ComponentProvider.getInstance().getPresentersComponent().inject(this);
        presenter.bindView(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initProgressDialog();
        initAdapter();
        presenter.onStart();
    }


    private void initProgressDialog() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle(R.string.progress_dialog_title);
        progressDialog.setMessage(getString(R.string.progress_dialog_message));
        progressDialog.setCancelable(false);
    }

    private void initAdapter() {
        newsAdapter = new NewsAdapter();
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        newsRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
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

    @Override
    public void onStop() {
        super.onStop();
        presenter.onStop();
    }
}
