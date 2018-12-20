package de.klysma.forgeblocker;

import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin {

    public static Main plugin;
    public static boolean teamMessage = false;
    public static String bypassPerm = "de.klysma.bypassForge";
    public static String getMessagePerm = "de.klysma.messageForgeJoin";
    public static String prefix = "§6ForgeBlocker §8§l| §a";
    public static String kickMessage = "§cDu wurdest gekickt!\n§4§lForge ist auf diesem Server nicht erlaubt!\n§aBitte deaktiviere Forge und komme mit einem anderen Client wieder Vanilla/Optifine/Labymod!";

    public void onEnable() {
        plugin = this;
        Manager.loadAll();
    }

    public void onDisable() {
        Manager.saveConfig();
    }
}
