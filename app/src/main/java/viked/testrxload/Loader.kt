package viked.testrxload

import android.database.Cursor
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action
import rx.functions.Action1
import rx.schedulers.Schedulers
import rx.subjects.PublishSubject
import rx.subjects.ReplaySubject

/**
 * Created by 1 on 24.02.2016.
 */
class Loader (val cursor: Cursor){

    val cursorObservable : Observable<Cursor> = Observable.create {subscriber ->
        try {
            while (cursor.moveToNext()) {
                subscriber.onNext(cursor)
            }
        } catch(e:Exception){
            subscriber.onError(e)
        } finally{
            cursor.close()
            subscriber.onCompleted()
        }
    }

    val cursorSubject : PublishSubject<Cursor> = PublishSubject.create()

    fun startLoading(s: Action1<List<Data>>){
       cursorSubject.map { cursor ->
            Data(cursor.getString(0), cursor.getString(1))
        }.toList().subscribe(s)
        cursorObservable.subscribe(cursorSubject)
    }

    fun startProgress(p1 : Action1<Int>){
        cursorSubject.map {cursor -> (((cursor.position).toDouble()/((cursor.count.toDouble())))*100).toInt() }
                .subscribe(p1)
    }

}