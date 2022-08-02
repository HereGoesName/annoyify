package io.namegoeshere.annoyify.models.helpers;

import com.google.common.base.Charsets;
import com.google.common.io.CharSink;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import io.namegoeshere.annoyify.util.FileManager;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;
import org.bukkit.Bukkit;


public final class EnabledPlayerListFile {

    @Getter
    private File playerListFile;

    /**
     *
     * @return List of UUIDs sored in the enabledPlayers file
     * @throws IOException Thrown in the event a file couldn't be created or read
     */
    public Set<String> loadFile() throws IOException {
        playerListFile = FileManager.createFile("enabledPlayers");
        Set<String> players = new HashSet<>(
            Files.readLines(playerListFile, Charset.defaultCharset()));
        Bukkit.getLogger().info("[Annoyify] File [enabledPlayers] was successfully loaded.");
        return players.stream().filter(
            player -> !player.trim().isEmpty()).collect(Collectors.toSet());
    }

    /**
     * Removes a player UUID from the file
     * @param playerUUID The UUID for the player
     * @throws IOException Thrown in the event a file couldn't be created or read
     */
    public void removePlayerFromFile(String playerUUID) throws IOException {
        List<String> playersToSave = Files.readLines(
            playerListFile,
            Charset.defaultCharset()).stream()
                .filter(line -> !line.contains(playerUUID) && !line.isEmpty())
                .collect(Collectors.toList());

        String formattedFileContents = playersToSave.stream().map(Object::toString)
            .collect(Collectors.joining("\n"));

        Files.write(formattedFileContents.getBytes(Charset.defaultCharset()), playerListFile);
    }

    /**
     * Adds a player UUID to the file
     * @param playerUUID The UUID for the player
     * @throws IOException Thrown in the event a file couldn't be created or read
     */
    public void addPlayerToFile(String playerUUID) throws IOException {
        CharSink chs = Files.asCharSink(playerListFile, Charsets.UTF_8, FileWriteMode.APPEND);
        // Add newlines either side of the UUID as otherwise it will append it onto the last line of populated text
        chs.write("\n" + playerUUID + "\n");
    }
}
