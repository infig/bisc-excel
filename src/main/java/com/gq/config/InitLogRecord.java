/*****************************
 * Created by IntelliJ IDEA. *
 * User: v
 * Date: 2021/3/23
 * Time: 下午2:16
 * Description: 
 *****************************/
package com.gq.config;

import org.apache.log4j.PropertyConfigurator;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Deprecated
public class InitLogRecord {
    public static void initLog() {
        FileInputStream fileInputStream = null;
        try {
            Properties properties = new Properties();
            fileInputStream = new FileInputStream("log4j.properties");
            properties.load(fileInputStream);
            PropertyConfigurator.configure(properties);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
