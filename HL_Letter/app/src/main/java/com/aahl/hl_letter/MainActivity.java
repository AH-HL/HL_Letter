package com.aahl.hl_letter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author : Mr.Hao
 * @project : ${PROJECT_NAME}
 * @date :  ${DATE}
 * @description :
 */


public class MainActivity extends Activity {

    private static final String APATCH_PATH = "/out.apatch"; // 下载下来的apatch的路径
    private static final String DIR = "apatch";//补丁文件夹
    @BindView(R.id.text)
    TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mText.setText(String.format("%s是天才", "朱喆"));


    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}
