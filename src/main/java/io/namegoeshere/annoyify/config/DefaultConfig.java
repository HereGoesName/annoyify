package io.namegoeshere.annoyify.config;

import io.namegoeshere.annoyify.util.FileManager;
import java.io.IOException;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import java.io.File;

public class DefaultConfig {
    @Getter
    private static double portalTravelFromNetherXPosition;

    @Getter
    private static double portalTravelFromNetherYPosition;

    @Getter
    private static double portalTravelFromNetherZPosition;

    @Getter
    private static TeleportationType portalTravelFromNetherType;

    @Getter
    private static int portalTravelFromNetherRate;

    @Getter
    private static int ignoreDoorInteractRate;

    @Getter
    private static Boolean ignoreDoorInteractEnabled;

    private static File file = null;
    private static FileConfiguration fileConfiguration = null;

    /**
     * The main configuration class for Annoyify
     */
    private DefaultConfig(){}

    /**
     * Sets the configuration values to values stored in the configuration file
     * @throws IOException Thrown in the event a file couldn't be created or read
     */
    public static void initialiseConfig() throws IOException {
        file = FileManager.createFile("config.yml");
        fileConfiguration = ConfigurationHelper.createFileConfiguration(file);

        // Nether Teleport
        portalTravelFromNetherType = ConfigurationHelper.setTeleportationType(
            fileConfiguration, "portalTravelFromNetherType", TeleportationType.OFF.toString());
        portalTravelFromNetherRate = ConfigurationHelper.setInt(
            fileConfiguration, "portalTravelFromNetherRate", 50, 100);
        portalTravelFromNetherXPosition = ConfigurationHelper.setDouble(
            fileConfiguration, "portalTravelFromNetherXPosition", 0);
        portalTravelFromNetherYPosition = ConfigurationHelper.setDouble(
            fileConfiguration, "portalTravelFromNetherYPosition", 0);
        portalTravelFromNetherZPosition = ConfigurationHelper.setDouble(
            fileConfiguration, "portalTravelFromNetherZPosition", 0);

        // Door Ignore
        ignoreDoorInteractEnabled = ConfigurationHelper.setBoolean(
            fileConfiguration, "ignoreDoorInteractEnabled", false);
        ignoreDoorInteractRate = ConfigurationHelper.setInt(
            fileConfiguration, "ignoreDoorInteractRate", 50, 100);

        // Save Defaults to the file
        ConfigurationHelper.saveDefaults(fileConfiguration, file);
    }
}
