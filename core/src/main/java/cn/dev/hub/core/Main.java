package cn.dev.hub.core;

import cn.dev.hub.core.api.pack.BaseGenerator;
import cn.dev.hub.core.api.pack.topnews.TopnewsGenerator;

/**
 * Created by suzunshou on 2017/3/31.
 */
public class Main {
    public static void main(String[] args) {
        BaseGenerator generator = new TopnewsGenerator();
        generator.init("alipay.openplatform.edu.campus.job");
    }
}
