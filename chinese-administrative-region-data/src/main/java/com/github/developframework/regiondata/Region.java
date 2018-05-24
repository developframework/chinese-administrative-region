package com.github.developframework.regiondata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * 地区基类
 * @author qiuzhenhao
 */
@Getter
@Setter
@AllArgsConstructor
public abstract class Region implements Serializable {

    private static final long serialVersionUID = 7148567071861908273L;

    protected String code;

    protected String name;

    @Override
    public int hashCode() {
        Objects.requireNonNull(code, "region code is null.");
        Objects.requireNonNull(name, "region name is null.");
        int hash = 7;
        hash += code.hashCode() * 31;
        hash += name.hashCode() * 31;
        return hash;
    }

    @Override
    public String toString() {
        return name;
    }

    public abstract List<? extends Region> childRegions();
}
