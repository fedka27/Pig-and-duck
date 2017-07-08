package app.meat.view.base;

public interface BasePresenter<VIEW> {

    void bindView(VIEW view);

    void onStart();

    void onStop();
}
