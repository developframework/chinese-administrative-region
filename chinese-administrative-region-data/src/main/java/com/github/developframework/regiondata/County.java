package com.github.developframework.regiondata;

import lombok.Getter;

import java.util.Collections;
import java.util.List;

/**
 * 县级地区
 * @author qiuzhenhao
 */
public class County extends Region{

    private static final long serialVersionUID = -7455951052900276216L;

    @Getter
    private City city;

    public County(City city, String code, String name) {
        super(code, name);
        this.city = city;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj != null && obj instanceof County) {
            if(obj == this) {
                return true;
            }
            final County otherCounty = (County) obj;
            return otherCounty.getCode().equals(code) && otherCounty.getName().equals(name);
        }
        return false;
    }

    @Override
    public List<? extends Region> childRegions() {
        return Collections.emptyList();
    }
}
