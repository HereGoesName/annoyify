package io.namegoeshere.annoyify.config;

/**
 * The type of teleportation that can be set; off for no teleportation, relative for teleporting a
 * player xyz blocks in given directions from their current location, and absolute for teleporting a
 * player to a set of co-ordinates
 */
public enum TeleportationType {
    OFF,
    RELATIVE,
    ABSOLUTE
}
