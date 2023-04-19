package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    public static Properties getPropertyObject() throws IOException {
        FileInputStream fi = new FileInputStream("Config/config.properties");

        Properties prop = new Properties();

        prop.load(fi);

        return prop;
    }

    public static String getProduct() throws IOException {
        return getPropertyObject().getProperty("product");
    }

    public static String getProductname() throws IOException {
        return getPropertyObject().getProperty("productname");
    }

    public static String getUrl() throws IOException {
        return getPropertyObject().getProperty("url");
    }

    public static String getUsername() throws IOException {
        return getPropertyObject().getProperty("username");
    }

    public static String getPassword() throws IOException {
        return getPropertyObject().getProperty("password");
    }

    public static String getCvv() throws IOException {
        return getPropertyObject().getProperty("cvv");
    }
}
