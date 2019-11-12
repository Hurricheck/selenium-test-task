package com.selenium.taf.util;

import org.testcontainers.shaded.org.apache.commons.lang.SystemUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestUtil {	
	private static final String TEST_WIN_PROPERTY_PATH = "src/main/resources/properties/testWin.properties";
    private static final String TEST_UBUNTU_PROPERTY_PATH = "src/main/resources/properties/testUbuntu.properties";

	public static String getPropertyValue (String key) {
		FileInputStream fis;
        Properties property = new Properties();
        try {
            if(SystemUtils.IS_OS_LINUX) {
                fis = new FileInputStream(TEST_UBUNTU_PROPERTY_PATH);
            } else {
                fis = new FileInputStream(TEST_WIN_PROPERTY_PATH);
            }
			property.load(fis);
		} catch (FileNotFoundException e) {
			System.out.println("Error! File wasn't found");
		} catch (IOException e) {
			System.out.println("Error! File parsing issue occured");
		}
        return property.getProperty(key);
	}
}

