package com.cws.mvvm;

import android.text.Editable;
import android.text.TextWatcher;

import com.cws.mvvm.bean.User;

/**
 * Created by cws on 2016/8/26.
 */
public class UserEvent {
    private User user;

    public UserEvent(User user) {
        this.user = user;
    }

    public TextWatcher usernameWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            user.username = s.toString();
        }
    };

    public TextWatcher passwordWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            user.password = s.toString();
        }
    };
}
