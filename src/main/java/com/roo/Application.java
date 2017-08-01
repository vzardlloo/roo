package com.roo;

import com.blade.Blade;

/**
 * Roo启动类
 *
 * @author biezhi
 * @date 2017/7/31
 */
public class Application {

    public static void main(String[] args) {
        Blade.me().start(Application.class, args);
    }

}
