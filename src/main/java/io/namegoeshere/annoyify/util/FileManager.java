package io.namegoeshere.annoyify.util;

import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;

public class FileManager {

    /**
     *
     * @param fileName The name for the file
     * @return Constructed {@link File}
     */
    public static File createFile(String fileName) throws IOException {
        return createFile(getFile(fileName));
    }

    /**
     *
     * @param file The constructed {@link File}
     * @return A {@link File} which is guaranteed to exist
     * is null
     * @throws IOException Thrown if there's an issue creating the file
     */
    public static File createFile(File file) throws IOException {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }

        return file;
    }

    /**
     *
     * @param fileName The name for the file
     * @return The constructed {@link File}
     */
    public static File getFile(String fileName) {
        File file = new File(
            Bukkit.getPluginManager().getPlugin("Annoyify").getDataFolder().getPath(),
            fileName);
        return file;
    }
}
