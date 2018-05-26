package com.aahl.sdk_mvp.factory;

import com.aahl.sdk_mvp.presenter.BaseMvpPresenter;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/**
 * @author : Mr.Hao
 * @date :  2018/5/26
 * @description :创建Presenter的注解
 * @Inherited  :注解所作用的类，在继承时默认无法继承父类的注解。除非注解声明了 @Inherited。同时Inherited声明出来的注解，只对类有效，对方法／属性无效。
 */


@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface CreatePresenter {
    Class<? extends BaseMvpPresenter> value();
}