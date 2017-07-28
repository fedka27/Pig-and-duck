package app.meat.view.main.setting;

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
import app.meat.model.data.repository.Category;
import app.meat.view.base.BaseFragment;
import butterknife.BindView;

public class SettingsFragment extends BaseFragment implements SettingsContract.View {
    @Inject
    protected SettingsContract.Presenter presenter;
    @BindView(R.id.category_recycler_view)
    RecyclerView recyclerView;
    private SettingAdapter settingAdapter;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onStop();
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
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
    }

    private void initAdapter() {
        settingAdapter = new SettingAdapter(presenter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(settingAdapter);
    }

    @Override
    public void setList(List<Category> categoriesEnumList) {
        settingAdapter.setCategories(categoriesEnumList);
    }
}
