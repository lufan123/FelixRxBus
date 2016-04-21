package com.felix.rxbus.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.felix.rxbus.FelixRxBus;
import com.felix.rxbus.RxBusEvent;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

public class MainActivity extends AppCompatActivity implements Action1<RxBusEvent<String>> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        FelixRxBus.getDefault()
                .toObserverable(RxBusEvent.class)
                .map(new Func1<RxBusEvent, RxBusEvent<String>>() {
                    @Override
                    public RxBusEvent<String> call(RxBusEvent rxBusEvent) {
                        rxBusEvent.setData(rxBusEvent.getData().toString().replace("a", ""));
                        return rxBusEvent;
                    }
                }).subscribe(this);

    }

    private final int SEND_MSG_TAG = 1;

    @OnClick(R.id.button)
    public void sendMsg() {
        FelixRxBus.getDefault().send(new RxBusEvent<>(SEND_MSG_TAG, edit.getText().toString()));
    }

    @Bind(R.id.edit)
    EditText edit;

    @Bind(R.id.show_text)
    TextView show_text;

    @Override
    public void call(RxBusEvent<String> event) {
        show_text.setText(event.getData());
        edit.setText("");
    }
}
