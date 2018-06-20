package com.aahl.hl_letter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.Toast;

import com.aahl.hl_letter.base.BaseMvpActivitiy;
import com.aahl.hl_letter.helper.BottomNavigationViewHelper;
import com.aahl.hl_letter.mvp.main.RequestPresenter;
import com.aahl.hl_letter.mvp.main.RequestView;
import com.aahl.hl_letter.ui.fragment.my.MyRootFragment;
import com.aahl.hl_letter.ui.fragment.store.StoreRootFragment;
import com.aahl.hl_letter.view.BottomBar;
import com.aahl.hl_letter.view.BottomBarTab;
import com.aahl.sdk_mvp.factory.CreatePresenter;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author : Mr.Hao
 * @date :  2018/6/19
 * @description : 主要承载页面
 */

@CreatePresenter(RequestPresenter.class)
public class MainActivity extends BaseMvpActivitiy<RequestView, RequestPresenter> implements RequestView {

    @BindView(R.id.bviv_bar)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;

    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;
    public static final int FIFTH = 4;

    private SupportFragment[] mFragments = new SupportFragment[5];

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        if (savedInstanceState == null) {
            mFragments[FIRST] = MyRootFragment.newInstance();
            mFragments[SECOND] = StoreRootFragment.newInstance();
            mFragments[THIRD] = MyRootFragment.newInstance();
            mFragments[FOURTH] = MyRootFragment.newInstance();
            mFragments[FIFTH] = MyRootFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_container, FIRST, mFragments[FIRST], mFragments[SECOND], mFragments[THIRD], mFragments[FOURTH], mFragments[FIFTH]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题
            // 这里我们需要拿到mFragments的引用,也可以通过getSupportFragmentManager.getFragments()
            // 自行进行判断查找(效率更高些),用下面的方法查找更方便些
            mFragments[FIRST] = findFragment(MyRootFragment.class);
            mFragments[SECOND] = findFragment(StoreRootFragment.class);
            mFragments[THIRD] = findFragment(MyRootFragment.class);
            mFragments[FOURTH] = findFragment(MyRootFragment.class);
            mFragments[FIFTH] = findFragment(MyRootFragment.class);
        }


        getBottomBar();
        getDynamicBottomBar();


    }

    /**
     * 写死底部菜单Tab方式
     */
    private void getBottomBar() {
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_item_home:
                        showHideFragment(mFragments[FIRST]);
                        break;
                    case R.id.menu_item_gank_io:
                        showHideFragment(mFragments[SECOND]);
                        break;
                    case R.id.menu_item_movie:
                        showHideFragment(mFragments[THIRD]);
                        break;
                    case R.id.menu_item_book:
                        showHideFragment(mFragments[FOURTH]);
                        break;
                    case R.id.menu_item_personal:
                        showHideFragment(mFragments[FIFTH]);
                        break;
                }
                return true;
            }
        });

    }

    /**
     * 可动态配置他不菜单方式（并可以做类似消息统计）
     */
    private void getDynamicBottomBar() {
        mBottomBar
                .addItem(new BottomBarTab(this, R.drawable.ic_vector_bottom_book, getString(R.string.msg)))
                .addItem(new BottomBarTab(this, R.drawable.ic_vector_bottom_gankio, getString(R.string.discover)))
                .addItem(new BottomBarTab(this, R.drawable.ic_vector_bottom_home, getString(R.string.more)))
                .addItem(new BottomBarTab(this, R.drawable.ic_vector_bottom_home, getString(R.string.more)))
                .addItem(new BottomBarTab(this, R.drawable.ic_vector_bottom_home, getString(R.string.more)));

        // 模拟未读消息
        mBottomBar.getItem(FIRST).setUnreadCount(9);

        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(mFragments[position], mFragments[prePosition]);

                BottomBarTab tab = mBottomBar.getItem(FIRST);
                if (position == FIRST) {
                    tab.setUnreadCount(0);
                } else {
                    tab.setUnreadCount(tab.getUnreadCount() + 1);
                }
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
                // 在FirstPagerFragment,FirstHomeFragment中接收, 因为是嵌套的Fragment
                // 主要为了交互: 重选tab 如果列表不在顶部则移动到顶部,如果已经在顶部,则刷新
//                EventBusActivityScope.getDefault(_mActivity).post(new TabSelectedEvent(position));
            }
        });
    }

    @Override
    public void requestLoading() {

    }

    @Override
    public void resultSuccess(String result) {

    }

    @Override
    public void resultFailure(String result) {

    }

    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(this, R.string.press_again_exit, Toast.LENGTH_SHORT).show();

        }
    }




}
