package com.github.developframework.regiondata;

import java.util.*;

/**
 * 省级地区
 * @author qiuzhenhao
 */
public class Province extends Region {

    private static final long serialVersionUID = 4292839570238691299L;

    private Map<String, City> citiesByCode = new TreeMap<>();
    private Map<String, City> citiesByName = new TreeMap<>();

    public Province(String code, String name) {
        super(code, name);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj != null && obj instanceof Province) {
            final Province otherProvince = (Province) obj;
            return otherProvince.getCode().equals(code) && otherProvince.getName().equals(name);
        }
        return false;
    }

    protected void addCity(City city) {
        citiesByCode.put(city.getCode(), city);
        citiesByName.put(city.getName(), city);
    }

    /**
     * 根据名称查找城市
     * @param name 市级名称
     * @return 符合条件的地区
     */
    public Optional<City> getCityByName(String name) {
        return Optional.ofNullable(citiesByName.get(name));
    }

    /**
     * 根据代码查找城市
     * @param code 市级代码
     * @return 符合条件的地区
     */
    public Optional<City> getCityByCode(String code) {
        return Optional.ofNullable(citiesByCode.get(code));
    }

    /**
     * 获得所有城市
     * @return 该省所有市级地区
     */
    public List<City> getAllCities() {
        return new ArrayList<>(citiesByCode.values());
    }

    /**
     * 获得所有县级地区
     * @return 该省所有县级地区
     */
    public List<County> getAllCounties() {
        List<County> counties = new LinkedList<>();
        for (City city : getAllCities()) {
            counties.addAll(city.getAllCounties());
        }
        return new ArrayList<>(counties);
    }

    @Override
    public List<? extends Region> childRegions() {
        return getAllCities();
    }
}
