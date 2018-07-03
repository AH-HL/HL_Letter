package com.aahl.hl_letter.ui.activity;

import android.Manifest;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aahl.hl_letter.R;
import com.aahl.hl_letter.base.BaseMvpActivitiy;
import com.aahl.hl_letter.mvp.flash.FlashPresenter;
import com.aahl.hl_letter.mvp.flash.FlashView;
import com.aahl.hl_letter.utils.ToastUtil;
import com.aahl.sdk_mvp.factory.CreatePresenter;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * @author : Mr.Hao
 * @date :  2018/7/3
 * @description :
 */


//声明需要创建的Presenter
@CreatePresenter(FlashPresenter.class)
public class FlashActivity extends BaseMvpActivitiy<FlashView, FlashPresenter> implements
        FlashView {

    @BindView(R.id.tv_count_down)
    TextView tvCountDown;
    @BindView(R.id.ll_skip)
    LinearLayout llSkip;
    @BindView(R.id.test)
    ImageView mTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置自己的Presenter工厂，如果你想自定义的话
        // setPresenterFactory(xxx);

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
    public void showJumpTime(String time) {
        tvCountDown.setText(time);
    }

    @Override
    public void jumpActivity(Class<?> clz) {
        startActivity(clz);
        finish();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        getMvpPresenter().onKeyDownOut();
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 运行时权限申请
     */
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
                //模拟 不管是否获取全部权限，进入主页面
                /** 模拟网络请求 */
                getMvpPresenter().initCountDown();
            }
        });

    }


    /**
     * 模拟点击网络请求接口
     */
    @OnClick(R.id.ll_skip)
    public void onViewClicked() {

        /** 模拟网络请求 */
        getMvpPresenter().clickRequest();



        /*

        *//** 获取当前日期 PATTERN_TIME 部分 "HH:mm:ss" *//*
        String shortTime = DateUtils.getDayTime();

        *//** 获取当前日期 PATTERN_DATE 部分 "yyyy-MM-dd HH:mm:ss" *//*
        String Time = DateUtils.getCurDate();

        String shortTime1 = DateUtils.getShortTime(DateUtils.getAllDate());

        *//** 获取当前时间起,累计XX天的日期（yyyy-MM-dd）数组 （ 已经转换为我们需要的格式：yyyyMMdd  ）*//*
        List<String> listData = DateUtils.getListData(17);

        *//** 获取一天之内的任意整数时段   注意：开始时间要小于结束时间，且在0~23范围内 *//*
        List<String> hourList = DateUtils.getHourList(9, 18);
        List<String> hourList1 = DateUtils.getHourList(11, 20);

        *//** Toast唯一工具类 不要乱加别的Toast工具 *//*
        ToastUtil.show(shortTime1);

        *//** 显示资源虚化图片 *//*
        DisplayUtils.displayBlurImg(this, R.mipmap.ic_launcher,mTest);



        DatePickerDialogFragment datePickerDialogFragment = new DatePickerDialogFragment();
        datePickerDialogFragment.setOnDateChooseListener(new DatePickerDialogFragment.OnDateChooseListener() {
            @Override
            public void onDateChoose(String one, String two, String three) {
                Toast.makeText(getApplicationContext(), one + "-" + two + "-" + three, Toast.LENGTH_SHORT).show();
            }
        });
        datePickerDialogFragment.show(getSupportFragmentManager(), "DatePickerDialogFragment");

        */

    }


}
