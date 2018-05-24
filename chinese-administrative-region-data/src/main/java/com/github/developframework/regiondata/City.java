package com.github.developframework.regiondata;

import lombok.Getter;

import java.util.*;

/**
 * 市级地区
 * @author qiuzhenhao
 */
public class City extends Region{

    private static final long serialVersionUID = -465680538220481146L;

    private Map<String, County> countiesByCode = new TreeMap<>();
    private Map<String, County> countiesByName = new TreeMap<>();

    @Getter
    private Province province;

    protected City(Province province, String code, String name) {
        super(code, name);
        this.province = province;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj != null && obj instanceof City) {
            final City otherCity = (City) obj;
            return otherCity.getCode().equals(code) && otherCity.getName().equals(name);
        }
        return false;
    }

    protected void addCounty(County county) {
        countiesByCode.put(county.getCode(), county);
        countiesByName.put(county.getName(), county);
    }

    /**
     * 根据名称查找县区
     * @param name 县级名称
     * @return 符合条件的地区
     */
    public Optional<County> getCountyByName(String name) {
        return Optional.ofNullable(countiesByName.get(name));
    }

    /**
     * 根据代码查找县区
     * @param code 县级代码
     * @return 符合条件的地区
     */
    public Optional<County> getCountyByCode(String code) {
        return Optional.ofNullable(countiesByCode.get(code));
    }

    /**
     * 获得所有县区
     * @return 该城市所有县区
     */
    public List<County> getAllCounties() {
        return new ArrayList<>(countiesByCode.values());
    }

    @Override
    public List<? extends Region> childRegions() {
        return getAllCounties();
    }
}
