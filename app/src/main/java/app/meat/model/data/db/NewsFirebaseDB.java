package app.meat.model.data.db;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import app.meat.model.dto.News;
import app.meat.util.L;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class NewsFirebaseDB {
    DatabaseReference databaseReference;
    private boolean isLockNotification = false;

    public NewsFirebaseDB() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }


    public Observable<List<News>> getNews() {
        isLockNotification = true;
        return Observable.create(observableEmitter -> {
            databaseReference.child("list").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    List<News> newsList = new ArrayList<News>();
                    for (DataSnapshot d : dataSnapshot.getChildren()) {
                        News news = d.getValue(News.class);
                        newsList.add(news);
                    }
                    observableEmitter.onNext(newsList);
                    observableEmitter.onComplete();
                    isLockNotification = false;
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    observableEmitter.onError(databaseError.toException());
                }
            });
        });
    }

    public PublishSubject<News> listenNews() {
        PublishSubject<News> publishSubject = PublishSubject.create();
        databaseReference.child("list").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        if (!isLockNotification) {
                            try {
                                News news = dataSnapshot.getValue(News.class);
                                L.e(getClass().getSimpleName(), "onChildAdded - " + news.toString());
                                publishSubject.onNext(news);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        publishSubject.onError(databaseError.toException());
                    }
                });
        return publishSubject;
    }
}
