package me.tsunami.donotjump;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerListener implements Listener {
	
  private JavaPlugin plugin;

  public PlayerListener(JavaPlugin plugin) {
	this.plugin = plugin;
  }

//JUMPEVENT
  @EventHandler
  public void onPlayerMove(PlayerMoveEvent event) {
    Player player = (Player)event.getPlayer();
	boolean enabledworld = plugin.getConfig().getBoolean(player.getLocation().getWorld().getName());
	if(enabledworld) {
	  boolean donotjump = plugin.getConfig().getBoolean("donotjump");
	  if(donotjump) {
        if(event.getTo().getY() > event.getFrom().getY()) {
          event.getTo().setY(event.getFrom().getY() + 50);
        }
	  }
	}
  }
}
