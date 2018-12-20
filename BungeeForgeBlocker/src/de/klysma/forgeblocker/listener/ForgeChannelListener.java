package de.klysma.forgeblocker.listener;

import de.klysma.forgeblocker.Main;
import de.klysma.forgeblocker.utils.ModData;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.Map;

public class ForgeChannelListener implements Listener {

    @EventHandler
    public void onMessageRecieve(PluginMessageEvent event){
        if(event.getTag().equals("FML|HS")){
            ModData modData = ModData.getModData(event.getData());

            Map<String, String> mods = modData.getMods();

            if(!mods.isEmpty()){
                event.getSender().disconnect(Main.kickMessage);
            }
        }
    }
}
