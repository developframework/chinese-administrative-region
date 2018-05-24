package com.github.developframework.regiondata;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

/**
 * 中国行政区提供器
 *
 * @author qiuzhenhao
 */
@Slf4j
public class ChinaRegionProvider {

    @Getter
    private China china;

    public ChinaRegionProvider(Versions version) {
        long startTime = System.currentTimeMillis();
        try (
                InputStream is = ChinaRegionProvider.class.getResourceAsStream(version.getFilename());
                ObjectInputStream ois = new ObjectInputStream(is)
        ) {
            this.china = (China) ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException("ChinaRegionProvider read dat file failed, " + e.getMessage());
        }
        log.debug("read dat file use {}ms", System.currentTimeMillis() - startTime);
    }

    public ChinaRegionProvider() {
        this(Versions.V20180331);
    }
}
