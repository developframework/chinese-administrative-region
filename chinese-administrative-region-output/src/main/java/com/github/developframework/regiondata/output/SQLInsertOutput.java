package com.github.developframework.regiondata.output;

import com.github.developframework.regiondata.Region;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * 输出SQL语句
 * @author qiuzhenhao
 */
public final class SQLInsertOutput {

    /**
     * 输出SQL语句
     * @param region 地区
     * @param sqlInsertOption insert语句配置
     * @return
     */
    public static List<String> insertSql(final Region region, SQLInsertOption sqlInsertOption) {
        List<String> list = new LinkedList<>();
        recursive(region, sqlInsertOption, list);
        return list;
    }

    private static void recursive(Region region, SQLInsertOption sqlInsertOption, List<String> list) {
        list.add(buildSQL(region, sqlInsertOption));
        List<? extends Region> childRegions = region.childRegions();
        for (Region childRegion : childRegions) {
            recursive(childRegion, sqlInsertOption, list);
        }
    }

    private static String buildSQL(Region region, SQLInsertOption sqlInsertOption) {
        return String.format("INSERT INTO `%s`.`%s`(`%s`, `%s`) VALUES('%s', '%s');",
                sqlInsertOption.getDatabase(),
                sqlInsertOption.getTable(),
                sqlInsertOption.getNameField(),
                sqlInsertOption.getCodeField(),
                region.getName(),
                region.getCode()
        );
    }

    @Data
    public static class SQLInsertOption {

        private String database;

        private String table;

        private String codeField;

        private String nameField;
    }

}
