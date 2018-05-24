## 中国行政区数据

数据来源： GB/T2260

### 数据提供器

```xml
<dependency>
    <groupId>com.github.developframework</groupId>
    <artifactId>chinese-administrative-region-data</artifactId>
    <version>${project.version}</version>
</dependency>
```

```java
ChinaRegionProvider chinaRegionProvider = new ChinaRegionProvider();

// 获得中国地区信息
China china = chinaRegionProvider.getChina();
// 获得所有省份地区信息
List<Province> allProvinces = china.getAllProvinces();

// 获得所有市级地区信息
List<City> allCities = china.getAllCities();

// 获得所有县级地区信息
List<County> allCounties = china.getAllCounties();

// 获得某个省级地区信息
Optional<Province> provinceOptional = china.getProvinceByName("浙江省");
if (provinceOptional.isPresent()) {
    Province zheJiangProvince = provinceOptional.get();

    // 获得地区代码
    String code = zheJiangProvince.getCode();

    // 获得地区名称
    String name = zheJiangProvince.getName();

    // 获得某个市级地区信息
    Optional<City> cityOptional = zheJiangProvince.getCityByName("杭州市");

    // 可以一直往下取县级城市
}
```

### 数据输出工具

```xml
<dependency>
    <groupId>com.github.developframework</groupId>
    <artifactId>chinese-administrative-region-output</artifactId>
    <version>${project.version}</version>
</dependency>
```

#### 控制台打印

```java
// 地区
Region region = provinceOptional.get();

// 是否需要打印地区代码
boolean needCode = true;
ConsoleOutput.print(region, needCode);

// 可以使用lambda缩写，默认为打印地区代码
provinceOptional.ifPresent(ConsoleOutput::print);
```

结果：

```
浙江省 (330000)
	 杭州市 (330100)
		 市辖区 (330101)
		 上城区 (330102)
		 下城区 (330103)
		 江干区 (330104)
		 拱墅区 (330105)
		 西湖区 (330106)
		 滨江区 (330108)
		 萧山区 (330109)
		 余杭区 (330110)
		 富阳区 (330111)
		 临安区 (330112)
		 桐庐县 (330122)
		 淳安县 (330127)
		 建德市 (330182)
（忽略余下内容）
```

#### 生成SQL INSERT语句

```java
Optional<Province> provinceOptional = china.getProvinceByName("浙江省");
final SQLInsertOutput.SQLInsertOption option = new SQLInsertOutput.SQLInsertOption();
option.setDatabase("test");
option.setTable("chinese_region");
option.setNameField("region_name");
option.setCodeField("region_code");
provinceOptional
        .flatMap(province -> Optional.of(SQLInsertOutput.insertSql(province, option)))
        .ifPresent(list -> list.forEach(System.out::println));
```

结果：

```sql
INSERT INTO `test`.`chinese_region`(`region_name`, `region_code`) VALUES('浙江省', '330000');
INSERT INTO `test`.`chinese_region`(`region_name`, `region_code`) VALUES('杭州市', '330100');
INSERT INTO `test`.`chinese_region`(`region_name`, `region_code`) VALUES('市辖区', '330101');
INSERT INTO `test`.`chinese_region`(`region_name`, `region_code`) VALUES('上城区', '330102');
INSERT INTO `test`.`chinese_region`(`region_name`, `region_code`) VALUES('下城区', '330103');
INSERT INTO `test`.`chinese_region`(`region_name`, `region_code`) VALUES('江干区', '330104');
INSERT INTO `test`.`chinese_region`(`region_name`, `region_code`) VALUES('拱墅区', '330105');
INSERT INTO `test`.`chinese_region`(`region_name`, `region_code`) VALUES('西湖区', '330106');
INSERT INTO `test`.`chinese_region`(`region_name`, `region_code`) VALUES('滨江区', '330108');
INSERT INTO `test`.`chinese_region`(`region_name`, `region_code`) VALUES('萧山区', '330109');
INSERT INTO `test`.`chinese_region`(`region_name`, `region_code`) VALUES('余杭区', '330110');
INSERT INTO `test`.`chinese_region`(`region_name`, `region_code`) VALUES('富阳区', '330111');
INSERT INTO `test`.`chinese_region`(`region_name`, `region_code`) VALUES('临安区', '330112');
INSERT INTO `test`.`chinese_region`(`region_name`, `region_code`) VALUES('桐庐县', '330122');
INSERT INTO `test`.`chinese_region`(`region_name`, `region_code`) VALUES('淳安县', '330127');
INSERT INTO `test`.`chinese_region`(`region_name`, `region_code`) VALUES('建德市', '330182');

-- 忽略余下内容
```

