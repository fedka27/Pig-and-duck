package app.meat.view.main;

import java.util.Collections;

import app.meat.model.data.db.NewsFirebaseDB;
import app.meat.util.L;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

public class MainPresenter implements MainContract.Presenter {
    private static final String TAG = MainPresenter.class.getSimpleName();
    private MainContract.View view;
    private NewsFirebaseDB newsFirebaseDB;
    private CompositeDisposable compositeDisposable;

    public MainPresenter(NewsFirebaseDB newsFirebaseDB) {
        this.newsFirebaseDB = newsFirebaseDB;

        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void bindView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void onStart() {
        view.showProgressDialog();
        initFirebaseDB();
    }

    private void initFirebaseDB() {
        compositeDisposable.add(newsFirebaseDB.getNews()
                .doOnSubscribe(disposable -> view.showProgressDialog())
                .doFinally(() -> view.hideProgressDialog())
                .flatMap(Observable::fromIterable)
                .doOnNext(news -> L.e(TAG, "List - " + news.toString()))
                .toList()
                .map(newses -> {
                    Collections.reverse(newses);
                    return newses;
                })
                .subscribe(newses -> view.setNews(newses),
                        Throwable::printStackTrace));
    }

    @Override
    public void settingsPressed() {
        view.openSettingsActivity();
    }

    @Override
    public void onStop() {
        compositeDisposable.clear();
    }

}
