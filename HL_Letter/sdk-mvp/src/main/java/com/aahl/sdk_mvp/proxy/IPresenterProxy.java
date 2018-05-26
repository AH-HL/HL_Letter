package com.aahl.sdk_mvp.proxy;


import com.aahl.sdk_mvp.factory.IPresenterMvpFactory;
import com.aahl.sdk_mvp.presenter.BaseMvpPresenter;
import com.aahl.sdk_mvp.view.BaseMvpView;

/**
 * @author : Mr.Hao
 * @date :  2018/5/26
 * @description : 代理接口 - 为上面的factory设置代理(不能写死这个工厂，那么我们需要使用者可以自定义)
 */

public interface IPresenterProxy<V extends BaseMvpView,P extends BaseMvpPresenter<V>> {


    /**
     * 设置创建Presenter的工厂
     * @param presenterFactory PresenterFactory类型
     */
    void setPresenterFactory(IPresenterMvpFactory<V, P> presenterFactory);

    /**
     * 获取Presenter的工厂类
     * @return 返回PresenterMvpFactory类型
     */
    IPresenterMvpFactory<V,P> getPresenterFactory();


    /**
     * 获取创建的Presenter
     * @return 指定类型的Presenter
     */
    P getMvpPresenter();


}
