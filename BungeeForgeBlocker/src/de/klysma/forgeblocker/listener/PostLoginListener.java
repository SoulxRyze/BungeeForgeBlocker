package de.klysma.forgeblocker.listener;

import de.klysma.forgeblocker.Main;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.concurrent.TimeUnit;

public class PostLoginListener implements Listener {

    @EventHandler
    public void onJoin(PostLoginEvent event){
        ProxiedPlayer proxiedPlayer = event.getPlayer();

        BungeeCord.getInstance().getScheduler().schedule(Main.plugin, new Runnable() {
            @Override
            public void run() {
                proxiedPlayer.sendData("FML|HS", new byte[] { -2, 0 });
                proxiedPlayer.sendData("FML|HS", new byte[] { 0, 2, 0, 0, 0, 0 });
                proxiedPlayer.sendData("FML|HS", new byte[] { 2, 0, 0, 0, 0 });
            }
        }, 2, TimeUnit.SECONDS);
    }
}
