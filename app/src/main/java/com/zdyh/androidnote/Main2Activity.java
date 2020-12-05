package com.zdyh.androidnote;

import android.arch.lifecycle.Observer;
import android.database.Observable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {



        TextView tv;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            tv=(TextView) findViewById(R.id.tv);
        }

        public void bt1_onClick(View view) {
            tv.setText("");
            //创建一个快餐店。Observable(被观察者)
            Observable<String> kuaicandian=Observable.create(new ObservableOnSubscribe<String>() {
                @Override
                public void subscribe(ObservableEmitter<String> e) throws Exception {
                    Thread.sleep(6000);
                    e.onNext("快餐（牛肉面）");
                    e.onComplete();
                }
            });

            Observer<String> wo=new Observer<String>() {
                @Override
                public void onSubscribe(Disposable d) {
                    tv.setText(tv.getText()+"RxJava-开始送快餐"+"\n");
                }

                @Override
                public void onNext(String s) {
                    tv.setText(tv.getText()+"RxJava-快餐送达:"+s+"\n");
                }

                @Override
                public void onError(Throwable e) {
                    tv.setText(tv.getText()+"RxJava-送快餐出错"+"\n");
                }

                @Override
                public void onComplete() {
                    tv.setText(tv.getText()+"RxJava-本次快餐送达完毕"+"\n");
                }
            };
            //把你的地址留给快餐店。subscribe(订阅)
            kuaicandian.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(wo);

        }
        public void bt3_onClick(View view) {
            tv.setText("点了按键3");
        }
        public void bt2_onClick(View view) {
            tv.setText("点了按键2");
        }
        public void bt4_onClick(View view) {
            tv.setText("点了按键4");
        }

    //创建一个快餐店。Observable(被观察者)
    Observable<String> kuaicandian=Observable.create(new ObservableOnSubscribe<String>() {
        @Override
        public void subscribe(ObservableEmitter<String> e) throws Exception {
            Thread.sleep(6000);
            e.onNext("快餐（牛肉面）");//发送数据
            e.onComplete();//关闭发送数据，以后不能再使用e.onNext
        }
    });

    //创建一个我（wo）。Observer(观察者)
    Observer<String> wo=new Observer<String>() {
        @Override
        public void onSubscribe(Disposable d) {
            tv.setText(tv.getText()+"RxJava-开始送快餐"+"\n");
        }

        @Override
        public void onNext(String s) {
            tv.setText(tv.getText()+"RxJava-快餐送达:"+s+"\n");
        }

        @Override
        public void onError(Throwable e) {
            tv.setText(tv.getText()+"RxJava-送快餐出错"+"\n");
        }

        @Override
        public void onComplete() {
            tv.setText(tv.getText()+"RxJava-本次快餐送达完毕"+"\n");
        }
    };
    }

