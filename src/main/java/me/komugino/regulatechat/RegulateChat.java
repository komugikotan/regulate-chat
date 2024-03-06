package me.komugino.regulatechat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class RegulateChat extends JavaPlugin implements Listener {
    public boolean isInteger(String s){
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("RegulateChat has been enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("RegulateChat has been disabled!" );
    }

    // Add chat event listeners here
    @EventHandler
    public void onPlayerChat(PlayerChatEvent event) {
        String message = event.getMessage();
        int maxCharacters = getConfig().getInt("general.max_characters");

        if(message.length() > maxCharacters) {
            event.getPlayer().sendMessage(getConfig().getString("general.warning_message_max").replace("%max_characters", String.valueOf(maxCharacters)));
            event.setCancelled(true);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase(("regulatechat"))){
            if(args[0].equals("reload")){
                if(sender.isOp() || sender.hasPermission("regulatechat.reload")){
                    reloadConfig();
                    sender.sendMessage(getConfig().getString("general.reloads_message"));
                }
                else{
                    sender.sendMessage(getConfig().getString("general.warning_message_no_permission"));
                }
            }
            else if(args[0].equals("max")){
                if(sender.isOp() || sender.hasPermission("regulatechat.max")){
                    if(args.length == 2){
                        if(isInteger(args[1])){
                            getConfig().set("general.max_characters", Integer.parseInt(args[1]));
                            saveConfig();
                            sender.sendMessage(getConfig().getString("general.success_message"));
                        }
                    }
                    else{
                        sender.sendMessage(getConfig().getString("general.warning_message_invalid_command"));
                    }
                }
                else{
                    sender.sendMessage(getConfig().getString("general.warning_message_no_permission"));
                }
            }
            else if(args[0].equals("help")){
                sender.sendMessage(getConfig().getString("general.help_message"));
            }
            else{
                sender.sendMessage(getConfig().getString("general.warning_message_invalid_command"));
            }
        }
        return false;
    }
}


