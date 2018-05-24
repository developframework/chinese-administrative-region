package com.github.developframework.regiondata;

import java.util.*;

/**
 * @author qiuzhenhao
 */
public class China extends Region{

    private Map<String, Province> provincesByName = new TreeMap<>();
    private Map<String, Province> provincesByCode = new TreeMap<>();

    protected China() {
        super("000000", "中华人民共和国");
    }


    protected void addProvince(Province province) {
        provincesByCode.put(province.getCode(), province);
        provincesByName.put(province.getName(), province);
    }

    /**
     * 根据名称查找省份
     * @param name 省级名称
     * @return 符合条件的地区
     */
    public Optional<Province> getProvinceByName(String name) {
        return Optional.ofNullable(provincesByName.get(name));
    }

    /**
     * 根据代码查找省份
     * @param code 省级代码
     * @return 符合条件的地区
     */
    public Optional<Province> getProvinceByCode(String code) {
        return Optional.ofNullable(provincesByCode.get(code));
    }

    /**
     * 获得所有省份
     * @return 所有省份
     */
    public List<Province> getAllProvinces() {
        return new ArrayList<>(provincesByCode.values());
    }

    /**
     * 获得所有市级地区
     * @return 所有市级地区
     */
    public List<City> getAllCities() {
        List<City> cities = new LinkedList<>();
        for (Province province : getAllProvinces()) {
            cities.addAll(province.getAllCities());
        }
        return new ArrayList<>(cities);
    }

    /**
     * 获得所有县级地区
     * @return 所有县级地区
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
        return getAllProvinces();
    }
}
