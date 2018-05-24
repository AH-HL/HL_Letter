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
