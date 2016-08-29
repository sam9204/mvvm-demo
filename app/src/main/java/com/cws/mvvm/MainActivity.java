package com.cws.mvvm;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.cws.mvvm.bean.User;
import com.cws.mvvm.databinding.ActivityMainBinding;
import com.cws.mvvm.net.UserLoginNet;

/**
 * MVVM
 * Created by cws on 2016/8/25.
 */

public class MainActivity extends AppCompatActivity {

    ProgressDialog pd;
    User user;
    UserEvent event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        user= new User();
        binding.setUser(user);
        event = new UserEvent(user);
        binding.setEvent(event);
        pd = new ProgressDialog(this);
    }

    /**
     * 按钮点击
     * @param v
     */
    public void login(View v){
        if (checkUserInfo(user)) {
            // 登陆
            pd.show();
            new Thread() {
                @Override
                public void run() {
                    UserLoginNet net = new UserLoginNet();
                    if (net.sendUserInfo(user)) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // 登陆成功
                                pd.dismiss();
                                showToast("登陆成功");
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // 登陆失败
                                pd.dismiss();
                                showToast("用户名或密码不正确");
                            }
                        });
                    }
                }
            }.start();
        } else {
            showToast("请输入完整信息");
        }
    }

    /**
     * 判断用户输入
     * @return
     * @param user
     */
    private boolean checkUserInfo(User user) {
        if (TextUtils.isEmpty(user.username) || TextUtils.isEmpty(user.password)) {
            return false;
        }
        return true;
    }

    /**
     * 显示toast
     * @param msg
     */
    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
