package io.namegoeshere.annoyify.config;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigurationHelper {

    /**
     *
     * @param file The file to modify
     * @return The {@link FileConfiguration} object
     */
    public static FileConfiguration createFileConfiguration(File file) {
        return YamlConfiguration.loadConfiguration(file);
    }

    /**
     * Saves the default values to the configuration {@link File}
     * @param fileConfiguration The {@link FileConfiguration} object
     * @param file The file to modify
     */
    public static void saveDefaults(FileConfiguration fileConfiguration, File file) {
        fileConfiguration.options().copyDefaults(true);
        clearConfig(fileConfiguration);

        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a {@link Boolean} to the {@link FileConfiguration}
     * @param fileConfiguration The {@link FileConfiguration} object
     * @param key The name of the field in the config file
     * @param defaultValue The default value to set the field if a key doesn't already exist
     * @return The boolean value
     */
    public static Boolean setBoolean(
        FileConfiguration fileConfiguration, String key, Boolean defaultValue
    ) {
        fileConfiguration.addDefault(key, defaultValue);
        return fileConfiguration.getBoolean(key);
    }

    /**
     * Adds a double to the {@link FileConfiguration}
     * @param fileConfiguration The {@link FileConfiguration} object
     * @param key The name of the field in the config file
     * @param defaultValue The default value to set the field if a key doesn't already exist
     * @return The double value
     */
    public static double setDouble(
        FileConfiguration fileConfiguration, String key, double defaultValue
    ) {
        fileConfiguration.addDefault(key, defaultValue);
        return fileConfiguration.getDouble(key);
    }

    /**
     * Adds an int to the {@link FileConfiguration}
     * @param fileConfiguration The {@link FileConfiguration} object
     * @param key The name of the field in the config file
     * @param defaultValue The default value to set the field if a key doesn't already exist
     * @return The int value
     */
    public static int setInt(
        FileConfiguration fileConfiguration, String key, int defaultValue
    ) {
        fileConfiguration.addDefault(key, defaultValue);
        return fileConfiguration.getInt(key);
    }

    /**
     * Adds an int to the {@link FileConfiguration}
     * @param fileConfiguration The {@link FileConfiguration} object
     * @param key The name of the field in the config file
     * @param defaultValue The default value to set the field if a key doesn't already exist
     * @param maxValue The maximum value an integer can be
     * @return The int value
     */
    public static int setInt(
        FileConfiguration fileConfiguration, String key, int defaultValue, int maxValue
    ) {
        if(defaultValue > maxValue) {
            defaultValue = maxValue;
        }
        fileConfiguration.addDefault(key, defaultValue);
        return fileConfiguration.getInt(key);
    }

    /**
     * Adds a {@link TeleportationType} to the {@link FileConfiguration}
     * @param fileConfiguration The {@link FileConfiguration} object
     * @param key The name of the field in the config file
     * @param defaultValue The default value to set the field if a key doesn't already exist
     * @return The TeleportationType value
     */
    public static TeleportationType setTeleportationType(
        FileConfiguration fileConfiguration, String key, String defaultValue
    ) {
        fileConfiguration.addDefault(key, defaultValue);
        switch(fileConfiguration.getString(key).toUpperCase()) {
            case "RELATIVE":
                return TeleportationType.RELATIVE;
            case "ABSOLUTE":
                return TeleportationType.ABSOLUTE;
            case "OFF":
            default:
                return TeleportationType.OFF;
        }
    }

    /**
     * Clears the {@link FileConfiguration} config of any unused values
     * @param configuration The {@link FileConfiguration} object
     */
    public static void clearConfig(FileConfiguration configuration) {
        AtomicBoolean tidiedConfig = new AtomicBoolean(false);
        configuration.getKeys(false).forEach(value -> {
            if (!Objects.requireNonNull(configuration.getDefaults()).getKeys(true)
                .contains(value)) {
                configuration.set(value, null);
                tidiedConfig.set(true);
            }
        });

        if(tidiedConfig.get()) {
            Bukkit.getLogger().info("[Annoyify] Tidied up config");
        }
    }
}
