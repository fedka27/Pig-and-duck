package app.meat.view.base;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;


public abstract class BaseRecyclerAdapter extends RecyclerView.Adapter<BaseRecyclerViewHolder> {
    protected List<Object> itemList = new ArrayList<>();
    protected RecyclerRow recyclerRow = new RecyclerRow();
    private PublishSubject<Boolean> lazyPublishSubjectTop;
    private PublishSubject<Boolean> lazyPublishSubjectBottom;

    @Override
    final public int getItemViewType(int position) {
        return recyclerRow.getRow(itemList.get(position)).typeLayout();
    }

    @Override
    final public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return recyclerRow.getRow(viewType).viewHolder(parent);
    }

    @Override
    final public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        Object item = itemList.get(position);
        recyclerRow.getRow(item).bind(holder, item);
    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public Observable<Boolean> asLazyLoadingBottom(RecyclerView recyclerView) {
        lazyPublishSubjectBottom = PublishSubject.create();
        LinearLayoutManager lm = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (lm.findLastVisibleItemPosition() == getItemCount() - 1) {
                    lazyPublishSubjectBottom.onNext(true);
                }
            }
        });
        return lazyPublishSubjectBottom;
    }
}
