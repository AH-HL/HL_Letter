package com.aahl.sdk_mvp.factory;

import com.aahl.sdk_mvp.presenter.BaseMvpPresenter;
import com.aahl.sdk_mvp.view.BaseMvpView;


/**
 * @author : Mr.Hao
 * @date :  2018/5/26
 * @description : Presenter工厂接口
 */

public interface IPresenterMvpFactory<V extends BaseMvpView,P extends BaseMvpPresenter<V>> {

    /**
     * 创建Presenter的接口方法
     * @return 需要创建的Presenter
     */
    P createMvpPresenter();
}
