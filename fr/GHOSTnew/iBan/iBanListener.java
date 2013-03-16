package fr.GHOSTnew.iBan;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class iBanListener implements Listener {
	@SuppressWarnings("unused")
	private iBan plugin;
    
    public iBanListener(iBan instance) {
            plugin = instance;
    }
    
    public void onPlayerJoin(PlayerJoinEvent e) {
    	Player p = e.getPlayer();
    	String parameters = "player=" +p;
    	 String result = Util.excutePost("http://iban.net63.net/POST/isbanned.php", parameters);
         if (result.contains("1")) {
        	 p.setBanned(true);
    	     p.kickPlayer("Indésirable");
         }
    }

}
