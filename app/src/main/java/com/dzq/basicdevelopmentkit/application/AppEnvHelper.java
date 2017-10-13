package com.dzq.basicdevelopmentkit.application;


import com.dzq.basicdevelopmentkit.BuildConfig;

/**
 * Created by dingzuoqiang on 2017/07/10.
 * Email: 530858106@qq.com
 */

public class AppEnvHelper {

    public static AppEnvEnum currentEnv() {

        if (BuildConfig.BUILD_TYPE.equals("release")) {
            return AppEnvEnum.ONLINE;
        }

        return AppEnvEnum.DEBUG;
    }

}
