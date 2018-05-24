package com.github.developframework.regiondata;

import lombok.Getter;

/**
 * 版本
 * @author qiuzhenhao
 */
public enum Versions {

    V20180331("/regiondata/gb2260-last-updated-2018-03-31.dat");

    @Getter
    private String filename;

    Versions(String filename) {
        this.filename = filename;
    }
}
