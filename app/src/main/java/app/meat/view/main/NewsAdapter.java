package app.meat.view.main;

import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import app.meat.R;
import app.meat.model.dto.News;
import app.meat.view.base.BaseRecyclerAdapter;
import app.meat.view.base.BaseRecyclerViewHolder;
import app.meat.view.base.RecyclerRow;
import butterknife.BindView;

public class NewsAdapter extends BaseRecyclerAdapter {
    public NewsAdapter() {
        recyclerRow.addRow(new RecyclerRow.Row() {
            @Override
            public boolean is(Object item) {
                return item instanceof News;
            }

            @Override
            public int typeLayout() {
                return 0;
            }

            @Override
            public BaseRecyclerViewHolder viewHolder(ViewGroup parent) {
                return new NewsHolder(parent);
            }

            @Override
            public void bind(BaseRecyclerViewHolder holder, Object item) {
                NewsHolder newsHolder = (NewsHolder) holder;
                newsHolder.bind((News) item);
            }
        });
    }

    void setNews(List<News> list){
        itemList.clear();
        itemList.addAll(list);
        notifyDataSetChanged();
    }

    class NewsHolder extends BaseRecyclerViewHolder {
        @BindView(R.id.title_text_view)
        TextView titleTextView;
        @BindView(R.id.category_text_view)
        TextView categoryTextView;
        @BindView(R.id.message_text_view)
        TextView messageTextView;

        NewsHolder(ViewGroup viewGroup) {
            super(viewGroup, R.layout.cell_news);
        }

        void bind(News news) {
            titleTextView.setText(news.getTitle());
            categoryTextView.setText(news.getCategory());
            messageTextView.setText(news.getMessage());
        }
    }
}
