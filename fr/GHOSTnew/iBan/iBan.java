package fr.GHOSTnew.iBan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.material.Command;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/*
 *By GHOSTnew 
 */

public class iBan extends JavaPlugin {
	
    private ConsoleCommandSender console;
	
	public void onEnable() {
		console = Bukkit.getServer().getConsoleSender();
		console.sendMessage("[" + ChatColor.DARK_AQUA + "iBan" + ChatColor.RESET + "]" + ChatColor.GREEN + "Enable.");
		PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new iBanListener(this), this);
	}
	
	public void onDisable() {
		console = Bukkit.getServer().getConsoleSender();
		console.sendMessage("[" + ChatColor.DARK_AQUA + "iBan" + ChatColor.RESET + "]" + ChatColor.RED + "Disable.");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) throws IOException {
		if(label.equalsIgnoreCase("iBan")){
			Player player2 = getServer().getPlayer(args[0]);
			player2.setBanned(true);
			@SuppressWarnings("unused")
			String setbanned = get("http://iban.infos.st/setbanned.php?user=<user>&pass=<password>&player="+ player2 +"&raison="+ args);
			/*
			 * Connexion avec le site externe 
			 * Requete GET
			 * //?user=<user>&pass=<password>&player=player2&raison=<Raison>
			 * sav le ban dans la BDD
			 * 
			 */
			if (!(sender instanceof Player)) {
				console = Bukkit.getServer().getConsoleSender();
				console.sendMessage(ChatColor.RED + args[0] + ChatColor.GREEN + "a été banie");
			} else {
				Player player = (Player) sender;
				player.sendMessage(ChatColor.RED + args[0] + ChatColor.GREEN + "a été banie");
			}
			
			
		}
		return false;
	}
	
	public static String get(String url) throws IOException{
	 
	String source =""; 
	URL oracle = new URL(url); 
	URLConnection yc = oracle.openConnection(); 
	BufferedReader in = new BufferedReader( 
	new InputStreamReader( 
	yc.getInputStream())); 
	String inputLine; 
	 
	while ((inputLine = in.readLine()) != null) 
	source +=inputLine; 
	in.close(); 
	return source; 
	}

}
