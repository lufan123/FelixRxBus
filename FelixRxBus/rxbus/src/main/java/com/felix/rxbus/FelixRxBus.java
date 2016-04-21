package com.felix.rxbus;


import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by Felix on 16/4/21.
 */
public class FelixRxBus {

    private static final Subject<Object, Object> _bus = new SerializedSubject<>(PublishSubject.create());

    private static volatile FelixRxBus instance;

    public static synchronized FelixRxBus getDefault(){
        if ( instance == null ){
            instance = new FelixRxBus();
        }
        _bus.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread());
        return instance;
    }

    public void send(RxBusEvent<?> event){
        _bus.onNext(event);
    }

    public <T> Observable<T> toObserverable (Class<T> eventType) {
        return _bus.ofType(eventType);
    }

}
