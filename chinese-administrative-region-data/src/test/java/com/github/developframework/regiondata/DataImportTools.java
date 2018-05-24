package com.github.developframework.regiondata;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.Charset;

/**
 * @author qiuzhenhao
 */
class DataImportTools {

    private static final String FILE_NAME = "gb2260-last-updated-2018-03-31.txt";

    public static void main(String[] args) {
        String filename = "E:/gb2260-last-updated-2018-03-31.dat";
        String[] lines = readDataFile();
        China china = getChina(lines);
        serializeObject(china, filename);
    }

    public static String[] readDataFile() {
        try(InputStream is = new FileInputStream("./" + FILE_NAME)) {
            return IOUtils.readLines(is, Charset.forName("UTF-8")).stream().toArray(String[]::new);
        } catch (IOException e) {
            e.printStackTrace();
            return new String[0];
        }
    }

    public static China getChina(String[] lines) {
        China china = new China();
        Province tmpProvince = null;
        City tmpCity = null;
        for (String line : lines) {
            String[] parts = line.split(" ");
            String code = parts[0].trim();
            String name = parts[1].trim();
            if(code.endsWith("0000")) {
                tmpProvince = new Province(code, name);
                china.addProvince(tmpProvince);
            } else if(code.endsWith("00")) {
                tmpCity = new City(tmpProvince, code, name);
                tmpProvince.addCity(tmpCity);
            } else {
                County county = new County(tmpCity, code, name);
                tmpCity.addCounty(county);
            }
        }
        return china;
    }

    public static void serializeObject(China china, String filename) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(china);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
