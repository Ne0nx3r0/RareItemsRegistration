package ne0nx3r0.rareitemsreg;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

class RareItemsRegistrationPlayerListener implements Listener
{
    public RareItemsRegistrationPlayerListener(){}
   /*
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPlayerInteract(PlayerInteractEvent e){
        e.setCancelled(true);
    }    
    
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPlayerMove(PlayerMoveEvent e){
        Location lStart = Bukkit.getWorld("world").getSpawnLocation();
        
        if(e.getTo().getBlockX() != lStart.getBlockX()
        || e.getTo().getBlockZ() != lStart.getBlockZ()){
            e.getPlayer().teleport(lStart.add(0, 3, 0));         
        e.getPlayer().sendMessage("To register (or reset your password):");       
        e.getPlayer().sendMessage("/register <password> <password>");
        }
    }  
    
    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerChat(AsyncPlayerChatEvent e)
    {
        e.setCancelled(true);   
    }*/

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPlayerJoin(PlayerJoinEvent e){        
        e.getPlayer().sendMessage("To register (or reset your password):");       
        e.getPlayer().sendMessage("/register <password> <password>");
    }
    
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e){   
        if(e.getMessage().length() > 10 && e.getMessage().substring(0,9).equalsIgnoreCase("/register")){
            String[] args = e.getMessage().substring(10).split(" ");
            
            if(args.length > 1 && args[0].equals(args[1])){

                if(RareItemsRegistration.m.registerUser(e.getPlayer().getName(),args[0],e.getPlayer().getAddress().getAddress().getAddress().toString()))
                {  
                    System.out.println(e.getPlayer().getName() + " registered.");
                    
                    e.getPlayer().sendMessage("You have registered! You can now login:");
                    e.getPlayer().sendMessage("http://rareitemsplugin.tk/");
                }
                else
                {
                    e.getPlayer().sendMessage("An error occurred");
                }
            }else{
                e.getPlayer().sendMessage(ChatColor.RED+"Passwords did not match!");
            }
        }/*else{
            e.getPlayer().sendMessage("To register (or reset your password):");       
            e.getPlayer().sendMessage("/register <password> <password>");
        
            e.setCancelled(true);
        }*/
    }
}
