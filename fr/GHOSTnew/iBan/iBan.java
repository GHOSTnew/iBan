package fr.GHOSTnew.iBan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.material.Command;
import org.bukkit.plugin.java.JavaPlugin;


/*
 *By GHOSTnew 
 */

public class iBan extends JavaPlugin {
	
    private ConsoleCommandSender console;
    public iBanConfig conf;
    public static Permission perms = null;
    public final iBanListener event = new iBanListener();
	
	public void onEnable() {
		console = Bukkit.getServer().getConsoleSender();
		getServer().getPluginManager().registerEvents(this.event, this);
        conf = new iBanConfig(this);
        conf.load();
        console.sendMessage("[" + ChatColor.DARK_AQUA + "iBan" + ChatColor.RESET + "]" + ChatColor.GREEN + "Enable.");
	}
	
	public void onDisable() {
		console = Bukkit.getServer().getConsoleSender();
		console.sendMessage("[" + ChatColor.DARK_AQUA + "iBan" + ChatColor.RESET + "]" + ChatColor.RED + "Disable.");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) throws IOException {
		if(label.equalsIgnoreCase("iBan")){
			if (!(sender instanceof Player)) {
				console = Bukkit.getServer().getConsoleSender();
				Player player2 = getServer().getPlayer(args[0]);
				player2.setBanned(true);
				@SuppressWarnings("unused")
				String setbanned = get("http://iban.infos.st/GET/setbanned.php?user="+ conf.user +"&pass="+ conf.pass +"&player="+ player2 +"&raison="+ args);
				console.sendMessage(ChatColor.RED + args[0] + ChatColor.GREEN + "a été banie");
				return true;
			} else {
				Player player = (Player) sender;
				if(perms.has(player, "iBan.canBan") || player.isOp()) {
					Player player2 = getServer().getPlayer(args[0]);
					player2.setBanned(true);
					@SuppressWarnings("unused")
					String setbanned = get("http://iban.net63.net/GET/setbanned.php?user="+ conf.user +"&pass="+ conf.pass +"&player="+ player2.getName() +"&raison="+ args);
					player.sendMessage(ChatColor.RED + args[0] + ChatColor.GREEN + "a été banie");
				}else {
					player.sendMessage(ChatColor.RED + "Vous n'avez pas les permissions");
				}return true;
				
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
