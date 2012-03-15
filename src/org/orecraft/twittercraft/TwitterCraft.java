package org.orecraft.twittercraft;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class TwitterCraft extends JavaPlugin
{
    protected static final Logger logger = Logger.getLogger("Minecraft");
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
    	String command = cmd.getName().toLowerCase();
    	Player player = (Player)sender;
    	if (sender instanceof Player) {
    		if (command.equals("tw") || command.equals("twit") || command.equals("tweet") || command.equals("twitter") || command.equals("twittercraft")) {
    			if (player.hasPermission("twitter.tweet")) {
    				if (args.length >= 1) {
    					String status;
    					status = combine(args, " ");
    					sender.sendMessage(ChatColor.GRAY + "Message '" + ChatColor.BLUE + status + ChatColor.GRAY + "' tweeted.");
    					String[] tweet = { status };
    					UpdateStatus.main(tweet);
    				}
    				else {
    					sender.sendMessage(ChatColor.GRAY + "TwitterCraft version 1.0.0 loaded.");
    					sender.sendMessage(ChatColor.GRAY + "Type " + ChatColor.BLUE + "/tweet [message]" + ChatColor.GRAY +  " to tweet.");
    				}
    			}
    			else {
    				sender.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
    			}
    		}
    	}
    	else {
    		System.out.println("The console does not have access to use TwitterCraft.");
    	}
		return false;
    }
    String combine(String[] s, String glue)
    {
      int k = s.length;
      if (k == 0)
        return null;
      StringBuilder out = new StringBuilder();
      out.append(s[0]);
      for (int x = 1; x < k; x++)
        out.append(glue).append(s[x]);
      return out.toString();
    }

    public void onDisable()
    {
      PluginDescriptionFile pdfFile = getDescription();
      System.out.println("[" + pdfFile.getName() + "]" + " version " + pdfFile.getVersion() + " is disabled!");
    }

    public void onEnable()
    {
        PluginDescriptionFile pdfFile = getDescription();

        System.out.println("[" + pdfFile.getName() + "]" + " version " + pdfFile.getVersion() + " is enabled!");
    }
}