package app.meat.view.setting;

import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import app.meat.R;
import app.meat.model.data.repository.Category;
import app.meat.view.base.BaseRecyclerAdapter;
import app.meat.view.base.BaseRecyclerViewHolder;
import app.meat.view.base.RecyclerRow;
import butterknife.BindView;


public class SettingAdapter extends BaseRecyclerAdapter {
    @NonNull
    private SettingsContract.AdapterPresenter adapterPresenter;
    private List<Category> categories;

    public SettingAdapter(SettingsContract.AdapterPresenter adapterPresenter) {
        this.adapterPresenter = adapterPresenter;

        recyclerRow.addRow(new RecyclerRow.Row() {
            @Override
            public boolean is(Object item) {
                return item instanceof Category;
            }

            @Override
            public int typeLayout() {
                return 0;
            }

            @Override
            public BaseRecyclerViewHolder viewHolder(ViewGroup parent) {
                return new CategoryHolder(parent);
            }

            @Override
            public void bind(BaseRecyclerViewHolder holder, Object item) {
                CategoryHolder categoryHolder = (CategoryHolder) holder;
                categoryHolder.bind((Category) item);
            }
        });
    }

    public void setCategories(List<Category> categories) {
        itemList.clear();
        itemList.addAll(categories);
        notifyDataSetChanged();
    }

    class CategoryHolder extends BaseRecyclerViewHolder{
        @BindView(R.id.category_title_text_view)
        TextView title;
        @BindView(R.id.category_checkbox)
        CheckBox checkBox;

        public CategoryHolder(ViewGroup viewGroup) {
            super(viewGroup, R.layout.cell_category);
        }

        public void bind(Category category){
            title.setText(category.getTitle());
            checkBox.setChecked(category.isCheck());

            getView().setOnClickListener(v -> {
                checkBox.setChecked(!checkBox.isChecked());
                adapterPresenter.actionTopicPressed(category, checkBox.isChecked());
            });
        }
    }
}
