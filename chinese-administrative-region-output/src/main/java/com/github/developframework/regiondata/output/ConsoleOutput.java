package com.github.developframework.regiondata.output;

import com.github.developframework.regiondata.Region;

/**
 * @author qiuzhenhao
 */
public final class ConsoleOutput {

    public static void print(Region region) {
        print(region, true, 0);
    }

    public static void print(Region region, boolean needCode) {
        print(region, needCode, 0);
    }

    private static void print(Region region, boolean needCode, int level) {

        for (int i = 0; i < level; i++) {
            System.out.print("\t");
        }
        System.out.println(" " + region.getName() + (needCode ? (" ("+region.getCode()+ ")") : ""));
        for (Region childRegion : region.childRegions()) {
            print(childRegion, needCode, level + 1);
        }
    }
}
