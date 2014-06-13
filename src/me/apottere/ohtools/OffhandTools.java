package me.apottere.ohtools;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Andrew Potter
 */
public class OffhandTools extends JavaPlugin {

    private ToolListener listener;

    public OffhandTools() {
        doConfig();
        listener = new ToolListener(this);
    }

    private void doConfig() {
        FileConfiguration config = getConfig();
        this.saveConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        return false;
    }

    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(listener, this);
    }

    public void onDisable() {

    }
}
