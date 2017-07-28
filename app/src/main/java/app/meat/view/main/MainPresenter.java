package app.meat.view.main;

import app.meat.model.data.repository.CategoryRepository;

public class MainPresenter implements MainContract.Presenter {
    private static final String TAG = MainPresenter.class.getSimpleName();
    private MainContract.View view;
    private CategoryRepository categoryRepository;

    public MainPresenter(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void bindView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void onStart() {
        if (!categoryRepository.isChanged()) {
            view.checkSettingsTab();
        } else {
            view.checkNewsTab();
        }
    }

    @Override
    public void onNewsPressed() {
        view.openNewsFragment();
    }

    @Override
    public void onSettingsPressed() {
        view.openSettingsFragment();
    }

    @Override
    public void onStop() {
    }

}
