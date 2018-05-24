package com.github.developframework.regiondata;

import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

/**
 * 中国行政区管理器
 *
 * @author qiuzhenhao
 */
public class ChinaRegionProvider {

    @Getter
    private China china;

    public ChinaRegionProvider(Versions version) {
        try (
                InputStream is = ChinaRegionProvider.class.getResourceAsStream(version.getFilename());
                ObjectInputStream ois = new ObjectInputStream(is)
        ) {
            this.china = (China) ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException("ChinaRegionProvider read dat file failed, " + e.getMessage());
        }
    }

    public ChinaRegionProvider() {
        this(Versions.V20180331);
    }
}
