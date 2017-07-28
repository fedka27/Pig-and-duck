package app.meat.view.main.setting;

import com.google.firebase.messaging.FirebaseMessaging;

import app.meat.model.data.repository.Category;
import app.meat.model.data.repository.CategoryRepository;

public class SettingsPresenter implements SettingsContract.Presenter {
    private SettingsContract.View view;
    private CategoryRepository categoryRepository;

    public SettingsPresenter(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void bindView(SettingsContract.View view) {
        this.view = view;
    }

    @Override
    public void onStart() {
        loadCategories();
    }

    private void loadCategories() {
        view.setList(categoryRepository.getCategories());
    }

    @Override
    public void onStop() {
        categoryRepository.changedCategories(true);
    }

    @Override
    public void actionTopicPressed(Category category, boolean checked) {
        if (checked){
            subscribeTopic(category.getTopic());
        } else {
            unsubscribeTopic(category.getTopic());
        }
        categoryRepository.changeCategory(category, checked);
    }

    private void subscribeTopic(String topic) {
        FirebaseMessaging.getInstance().subscribeToTopic(topic);
    }

    private void unsubscribeTopic(String topic) {
        FirebaseMessaging.getInstance().unsubscribeFromTopic(topic);
    }
}
