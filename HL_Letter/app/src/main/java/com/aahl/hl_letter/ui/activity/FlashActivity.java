package com.aahl.hl_letter.ui.activity;

import android.Manifest;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aahl.hl_letter.R;
import com.aahl.hl_letter.base.BaseMvpActivitiy;
import com.aahl.hl_letter.mvp.flash.FlashPresenter;
import com.aahl.hl_letter.mvp.flash.FlashView;
import com.aahl.hl_letter.utils.ToastUtil;
import com.aahl.sdk_mvp.factory.CreatePresenter;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 工程名 : HLMVP
 * 包名  :  com.zxvp.hlmvp
 * 作者名 : 好郝先生
 * 日期  :  2017/12/11
 * 邮箱  :  anhuihaoliang@163.com
 * 功能  :
 */
//声明需要创建的Presenter
@CreatePresenter(FlashPresenter.class)
public class FlashActivity extends BaseMvpActivitiy<FlashView, FlashPresenter> implements
        FlashView {


    private static final String TAG = "dsadsa";
    @BindView(R.id.tv_count_down)
    TextView tvCountDown;
    @BindView(R.id.ll_skip)
    LinearLayout llSkip;
    private boolean mIsCancle;
    private boolean mIsOut;
    private int mTime = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置自己的Presenter工厂，如果你想自定义的话
        // setPresenterFactory(xxx);
//        tvCountDown = findViewById(R.id.tv_count_down);

    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_flash;

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        //注：魅族pro6s-7.0-flyme6权限没有像类似6.0以上手机一样正常的提示dialog获取运行时权限，而是直接默认给了权限
        requestPermissions();
    }


    @Override
    public void requestLoading() {
        showRequestDialog();
    }

    @Override
    public void resultSuccess(String result) {
        closeRequestDialog();
    }

    @Override
    public void resultFailure(String result) {
        closeRequestDialog();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        mIsOut = true;
        return super.onKeyDown(keyCode, event);
    }

    private void requestPermissions() {
        RxPermissions rxPermission = new RxPermissions(FlashActivity.this);
        //请求权限全部结果
        rxPermission.request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                Manifest.permission.READ_PHONE_STATE,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_COARSE_LOCATION).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean granted) throws Exception {
                if (!granted) {
                    ToastUtil.show("App未能获取全部需要的相关权限，部分功能可能不能正常使用.");
                }
                //不管是否获取全部权限，进入主页面
                initCountDown();
            }
        });
        //分别请求权限
        //        rxPermission.requestEach(Manifest.permission.ACCESS_FINE_LOCATION,
        //                Manifest.permission.WRITE_EXTERNAL_STORAGE,
        //                Manifest.permission.READ_CALENDAR,
        //                Manifest.permission.READ_CALL_LOG,
        //                Manifest.permission.READ_CONTACTS,
        //                Manifest.permission.READ_PHONE_STATE,
        //                Manifest.permission.READ_SMS,
        //                Manifest.permission.RECORD_AUDIO,
        //                Manifest.permission.CAMERA,
        //                Manifest.permission.CALL_PHONE,
        //                Manifest.permission.SEND_SMS)
        //注：魅族pro6s-7.0-flyme6权限没有像类似6.0以上手机一样正常的提示dialog获取运行时权限，而是直接默认给了权限。魅族pro6s动态获取权限不会回调下面的方法
        //        rxPermission.requestEach(
        //                Manifest.permission.CAMERA,
        //                Manifest.permission.READ_PHONE_STATE,
        //                Manifest.permission.WRITE_EXTERNAL_STORAGE,
        //                Manifest.permission.READ_EXTERNAL_STORAGE,
        //                Manifest.permission.ACCESS_COARSE_LOCATION)
        //                .subscribe(new Consumer<Permission>() {
        //                    @Override
        //                    public void accept(Permission permission) throws Exception {
        //                        if (permission.granted) {
        //                            // 用户已经同意该权限
        //                            Log.d(TAG, permission.name + " is granted.");
        //                        } else if (permission.shouldShowRequestPermissionRationale) {
        //                            // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
        //                            Log.d(TAG, permission.name + " is denied. More info should
        // be provided.");
        //                        } else {
        //                            // 用户拒绝了该权限，并且选中『不再询问』
        //                            Log.d(TAG, permission.name + " is denied.");
        //                        }
        //                    }
        //                });
    }

    private void initCountDown() {
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
                tvCountDown.setText(String.valueOf(value));
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
                if (mIsCancle) {
//                    startActivity(MainActivity.class);
//                    finish();
                }
            }
        });

    }

    @OnClick(R.id.ll_skip)
    public void onViewClicked() {
        getMvpPresenter().clickRequest("101010100", this);
    }
}
