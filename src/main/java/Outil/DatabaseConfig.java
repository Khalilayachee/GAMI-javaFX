package Outil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig {
    private static final Properties properties = new Properties();
    
    static {
        try (InputStream input = DatabaseConfig.class.getClassLoader().getResourceAsStream("database.properties")) {
            if (input == null) {
                throw new RuntimeException("Unable to find database.properties");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load database configuration", e);
        }
    }

    public static String getUrl() {
        return getProperty("db.url");
    }

    public static String getUser() {
        return getProperty("db.user");
    }

    public static String getPassword() {
        return getProperty("db.password");
    }

    public static int getPoolSize() {
        return Integer.parseInt(getProperty("db.poolSize", "10"));
    }

    public static int getConnectionTimeout() {
        return Integer.parseInt(getProperty("db.connectionTimeout", "30000"));
    }

    public static int getMinimumIdle() {
        return Integer.parseInt(getProperty("db.minimumIdle", "5"));
    }

    public static int getMaximumPoolSize() {
        return Integer.parseInt(getProperty("db.maximumPoolSize", "20"));
    }

    private static String getProperty(String key) {
        String envKey = key.toUpperCase().replace('.', '_');
        return System.getenv(envKey) != null ? System.getenv(envKey) : properties.getProperty(key);
    }

    private static String getProperty(String key, String defaultValue) {
        String value = getProperty(key);
        return value != null ? value : defaultValue;
    }
}