package com.aahl.hl_letter.mvp.flash;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.aahl.hl_letter.MainActivity;
import com.aahl.hl_letter.model.UserQueryBean;
import com.aahl.hl_letter.remote.HttpResultBean;
import com.aahl.hl_letter.remote.XSubscriber;
import com.aahl.sdk_mvp.presenter.BaseMvpPresenter;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * @author : Mr.Hao
 * @date :  2017/12/13
 * @description : P层  特点:需要持有M层和V层
 */

public class FlashPresenter extends BaseMvpPresenter<FlashView> {

    private final FlashMode mFlashMode;
    private boolean mIsCancle;
    private boolean mIsOut;
    private int mTime = 3;

    public FlashPresenter() {
        this.mFlashMode = new FlashMode();
    }


    /**
     * 模拟点击请求
     */
    public void clickRequest() {

        mFlashMode.request(new XSubscriber<HttpResultBean<UserQueryBean>>() {
            @Override
            public void onNext(HttpResultBean<UserQueryBean> userQueryBeanHttpResultBean) {
                super.onNext(userQueryBeanHttpResultBean);
                mIsOut = true;
                getMvpView().jumpActivity(MainActivity.class);
            }


            @Override
            public void onError(@NonNull Throwable e) {
                super.onError(e);
            }

        });


    }

    /**
     * 按系统返回键时，相当于取消倒计时
     */
    public void onKeyDownOut() {
        mIsOut = true;
    }

    /**
     * 启动页倒计时跳转
     */
    public void initCountDown() {
        Observable<Long> map = Observable.interval(1, TimeUnit.SECONDS).take(4)//计时次数
                .map(new Function<Long, Long>() {

                    @Override
                    public Long apply(Long aLong) throws Exception {
                        return mTime - aLong;// 3-0 3-2 3-1
                    }
                });

        map.compose(new ObservableTransformer<Long, Long>() {
            @Override
            public ObservableSource<Long> apply(Observable<Long> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers
                        .mainThread());
            }
        }).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Long value) {
                if (value == 0 && !mIsOut) {
                    mIsCancle = true;
                }
                if (!mIsOut){
                    getMvpView().showJumpTime(String.valueOf(value));
                }

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                if (mIsCancle) {
                    getMvpView().jumpActivity(MainActivity.class);
                    interruptHttp();
                }
            }
        });


    }


    @Override
    public void onCreatePersenter(@Nullable Bundle savedState) {
        super.onCreatePersenter(savedState);
        if (savedState != null) {
            Log.e("sdk-mvp", "onCreatePersenter 测试  = " + savedState.getString("test2"));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("sdk-mvp", "onSaveInstanceState 测试 ");
        outState.putString("test2", "test_save2");
    }

    @Override
    public void onDestroyPersenter() {
        super.onDestroyPersenter();
    }


    /**
     * 取消网络请求
     */
    private void interruptHttp() {
        mFlashMode.interruptHttp();
    }


}
