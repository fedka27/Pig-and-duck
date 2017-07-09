package app.meat.model.data.repository;

import java.util.ArrayList;
import java.util.List;

import app.meat.R;
import io.paperdb.Paper;

public class CategoryRepository {
    private static final String TAG = CategoryRepository.class.getSimpleName();
    private static final String KEY_LIST = TAG + "_LIST";
    private static final String KEY_VERSION_LIST = TAG + "_VERSION_LIST";
    private static final String KEY_CHANGED = TAG + "_CHANGED";
    int versionList = 1;
    private List<Category> categories;

    public CategoryRepository() {
        categories = Paper.book().read(KEY_LIST);
        int version = Paper.book().read(KEY_VERSION_LIST, -1);
        if (categories == null || versionList != version) {
            categories = new ArrayList<>();
            categories.add(new Category(Category.TOPIC_ALL, R.string.category_all, false));
            categories.add(new Category(Category.TOPIC_BEEF, R.string.category_beef, false));
            categories.add(new Category(Category.TOPIC_PORK, R.string.category_pork, false));
            categories.add(new Category(Category.TOPIC_BIRD, R.string.category_bird, false));
            Paper.book().write(KEY_VERSION_LIST, versionList);
        }
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
        Paper.book().write(KEY_LIST, this.categories);
    }

    public void changeCategory(Category category, boolean checked) {
        Category newCategory = new Category(category.getTopic(), category.getTitle(), checked);
        int index = categories.indexOf(category);

        categories.set(index, newCategory);
        setCategories(categories);
    }

    public void changedCategories(boolean b) {
        Paper.book().write(KEY_CHANGED, b);
    }

    public boolean isChanged(){
        return Paper.book().read(KEY_CHANGED, false);
    }
}
