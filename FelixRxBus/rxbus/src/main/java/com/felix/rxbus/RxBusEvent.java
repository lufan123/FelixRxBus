package com.felix.rxbus;

/**
 * Created by Felix on 16/4/21.
 */
public class RxBusEvent<T> {

    /**
     * 消息标签
     */
    private int TAG = 0;

    /**
     * 消息传递的数据
     */
    private T data;

    public RxBusEvent(int tag,T data){
        this.TAG = tag;
        this.data = data;
    }

    public T getData(){
        return data;
    }

    public void setData(T data){
        this.data = data;
    }

    public int getTAG(){return TAG;}

}
