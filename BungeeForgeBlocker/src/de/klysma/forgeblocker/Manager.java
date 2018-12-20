package de.klysma.forgeblocker;

import de.klysma.forgeblocker.listener.ForgeChannelListener;
import de.klysma.forgeblocker.listener.PostLoginListener;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.NavigableMap;
import java.util.concurrent.TimeUnit;

public class Manager {

    public static Configuration configuration;

    public static void loadAll(){
        BungeeCord.getInstance().getScheduler().schedule(Main.plugin, ()-> {
            loadConfig();
        }, 10, 10, TimeUnit.MILLISECONDS);
        registerListener();
        registerChannel();
    }

    public static void saveConfig(){
        try {
            File file = new File("plugins/BungeeForgeBlocker", "config.yml");
            file.mkdir();
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, file);
        }catch (Exception exe){}
    }

    private static void registerChannel(){
        BungeeCord.getInstance().registerChannel("FML|HS");
    }

    private static void registerListener(){
        BungeeCord.getInstance().getPluginManager().registerListener(Main.plugin, new PostLoginListener());
        BungeeCord.getInstance().getPluginManager().registerListener(Main.plugin, new ForgeChannelListener());
    }

    private static void loadConfig(){
        File pluginfile = new File("plugins/BungeeForgeBlocker");
        pluginfile.mkdir();
        try {
            File file = new File("plugins/BungeeForgeBlocker", "config.yml");
            boolean created = file.createNewFile();
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);

            if(created){
                configuration.set("bypass-permission", "de.klysma.bypassForge");
                configuration.set("get-forgejoin-message", "de.klysma.messageForgeJoin");
                configuration.set("sendTeamMessage", true);
                configuration.set("prefix", "§6ForgeBlocker §8§l| §a");
                configuration.set("kickMessage", "§cDu wurdest gekickt!\n§4§lForge ist auf diesem Server nicht erlaubt!\n§aBitte deaktiviere Forge und komme mit einem anderen Client wieder Vanilla/Optifine/Labymod!");
                saveConfig();
            }
        }catch (Exception exe){}

        if(configuration != null) {
            Main.bypassPerm = configuration.getString("bypass-permission");
            Main.getMessagePerm = configuration.getString("get-forgejoin-message");
            Main.teamMessage = configuration.getBoolean("sendTeamMessage");
            Main.prefix = configuration.getString("prefix");
            Main.kickMessage = configuration.getString("kickMessage");
        }
    }
}
