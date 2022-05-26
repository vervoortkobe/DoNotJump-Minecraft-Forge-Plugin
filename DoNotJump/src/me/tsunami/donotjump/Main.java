package me.tsunami.donotjump;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	
  public void onEnable() {
    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
    console.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "DoNotJump was enabled!");
    getCommand("donotjump").setExecutor((CommandExecutor)this);
    getConfig().options().copyDefaults(true);
    saveConfig();
    Bukkit.getServer().getPluginManager().registerEvents(new PlayerListener(this), (Plugin)this);
  }
  
  public void onDisable() {
    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
    console.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "DoNotJump was disabled!");
  }
  
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
	  
//DONOTJUMP & DNJHELP
    if(command.getName().equalsIgnoreCase("donotjump") || command.getName().equalsIgnoreCase("dnjhelp")) {
        if(sender instanceof Player) {
          Player player = (Player)sender;
          
          if(player.hasPermission("donotjump.*")) {
            player.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "=====[DoNotJump Help]=====\n" + 
                ChatColor.GREEN + "" + ChatColor.BOLD + "/donotjump or /dnjhelp\n" + 
                ChatColor.BLUE + "The help command of DoNotJump.\n" + 
                ChatColor.GREEN + "" + ChatColor.BOLD + "/dnjconfig\n" + 
                ChatColor.BLUE + "Change the config of DoNotJump.\n" + 
                ChatColor.GREEN + "" + ChatColor.BOLD + "/dnjreload\n" + 
                ChatColor.BLUE + "Reload DoNotJump.");
          } else {
            player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "ERROR: You do not have permissions to use this command!");
          } 
          return true;
        } 
        sender.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "=====[DoNotJump Help]=====\n" + 
            ChatColor.GREEN + "" + ChatColor.BOLD + "/donotjump or dnjhelp\n" + 
            ChatColor.BLUE + "The help command of DoNotJump.\n" + 
            ChatColor.GREEN + "" + ChatColor.BOLD + "/dnjconfig\n" + 
            ChatColor.BLUE + "Change the config of DoNotJump.\n" + 
            ChatColor.GREEN + "" + ChatColor.BOLD + "/dnjreload\n" + 
            ChatColor.BLUE + "Reload DoNotJump.");
        return true;
      } 
    
//DNJCONFIG
    if(command.getName().equalsIgnoreCase("dnjconfig")) {
  	  if(sender instanceof Player) {
  		  Player player = (Player)sender;
  		  
              if(player.hasPermission("donotjump.*")) {
  			  if (args.length != 2) {
  				  player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "ERROR: Usage: /dnjconfig donotjump <true/false>");
                } else {
  				  if(this.getConfig().contains(args[0])) {
  					  if(args[1].equalsIgnoreCase("true") || args[1].equalsIgnoreCase("false")) {
  						  if(args[1].equalsIgnoreCase("true")) {
  							  this.getConfig().set(args[0], true);
  							  player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "DoNotJump" + args[0] + " set to " + ChatColor.DARK_GREEN + "" + ChatColor.BOLD + args[1] + "!");
  						  } else {
  							  this.getConfig().set(args[0], false);
  							  player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "DoNotJump: " + args[0] + " set to " + ChatColor.DARK_RED + "" + ChatColor.BOLD + args[1] + "!");
  						  }
  						  this.saveConfig();
  					  } else {
  					      player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD +  "ERROR: " + args[1] + " is not a valid boolean! Choose between true/false!!");
  					  }
  				  } else {
  					  player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "ERROR: " + args[0] + " is not a valid function!");
  				  }
                }	  
              } else {
              	player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "ERROR: You do not have permissions to use this command!");
  		  }
  		  return true;
  	  }
  	  sender.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "ERROR: You can't use this command in console!");
  	  return true;
    }
      
//DNJRELOAD
      if(command.getName().equalsIgnoreCase("dnjreload")) {
        if (sender instanceof Player) {
          Player player = (Player)sender;
          if (player.hasPermission("donotjump.*")) {
            reloadConfig();
            saveConfig();
            player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "DoNotJump was reloaded!");
          } else {
            player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "ERROR: You do not have permissions to use this command!");
          } 
          return true;
        } 
        sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "DoNotJump was reloaded!");
        return true;
      }
    return false;
  }
}
