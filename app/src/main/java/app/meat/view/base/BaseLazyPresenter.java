package app.meat.view.base;

public abstract class BaseLazyPresenter implements LazyLoadPresenter {
    protected int offset = 0;
    protected int limit = 15;
    protected boolean isLoading = false;
    protected boolean isEnd = false;

    @Override
    public void loadNext() {
        if (isLoading || isEnd) {
            return;
        }
        isLoading = true;
        startLoadNext();
    }

    protected void onLoading() {
        isLoading = true;
    }

    protected void onLoaded(int countOffset) {
        if (countOffset < limit) {
            isEnd = true;
        }
        offset = offset + countOffset;
        isLoading = false;
    }

    protected void onErrorLoaded() {
        isLoading = false;
    }

    protected void resetLoads() {
        offset = 0;
        isEnd = false;
    }

    protected boolean isFirstLoad() {
        return offset == 0 || offset < limit;
    }

    protected abstract void startLoadNext();

}
