/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.kanci.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.kanci.ui.base.BaseActivity;
import com.mindorks.framework.mvvm.BR;
import com.mindorks.framework.mvvm.R;
import com.mindorks.framework.mvvm.databinding.ActivityGuideRoleBinding;

/**
 * Created by amitshekhar on 08/07/17.
 */

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements MainNavigator {

    MainViewModel mViewModel;
    private ActivityGuideRoleBinding mBinding;

    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    public void handleError(Throwable throwable) {
        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mViewModel = new MainViewModel(getApplication());
        super.onCreate(savedInstanceState);
        mBinding = getViewDataBinding();
        mViewModel.setNavigator(this);
    }
}
