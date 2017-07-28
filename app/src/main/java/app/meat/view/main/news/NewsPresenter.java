package app.meat.view.main.news;

import java.util.Collections;

import app.meat.model.data.db.NewsFirebaseDB;
import app.meat.util.L;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

public class NewsPresenter implements NewsContract.Presenter {
    private static final String TAG = NewsPresenter.class.getSimpleName();
    private NewsContract.View view;
    private NewsFirebaseDB newsFirebaseDB;
    private CompositeDisposable compositeDisposable;

    public NewsPresenter(NewsFirebaseDB newsFirebaseDB) {
        this.newsFirebaseDB = newsFirebaseDB;

        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void bindView(NewsContract.View view) {
        this.view = view;
    }

    @Override
    public void onStart() {
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
    public void onStop() {

    }
}
