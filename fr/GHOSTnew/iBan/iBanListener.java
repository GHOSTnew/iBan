package fr.GHOSTnew.iBan;

import java.io.IOException;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class iBanListener implements Listener {

    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) throws IOException {
    	Player p = e.getPlayer();
    	String result = iBan.get("http://iban.net63.net/GET/isbanned.php?player=" +p.getName());
    	System.out.print(result);
    	if (result.contains("1")) {
        	 p.setBanned(true);
    	     p.kickPlayer("Indésirable");
         }

    }

}
