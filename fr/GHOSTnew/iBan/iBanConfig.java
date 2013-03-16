package fr.GHOSTnew.iBan;

import org.bukkit.configuration.file.FileConfiguration;

public class iBanConfig {
	public iBan plugin;
	public String user;
	public String pass;
	 public iBanConfig(iBan instance) {
		 plugin = instance;
	 }
     
	 public void load(){
		 FileConfiguration config = plugin.getConfig();
		 if(config.get("config") == null){
			 config.set("config.user", "<your user name>");
			 config.set("config.password", "<your password>");
		 }
		 user = config.getString("config.user");
		 pass = config.getString("config.pass");
		 plugin.saveConfig();
	 }

}
