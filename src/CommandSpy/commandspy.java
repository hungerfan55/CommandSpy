package CommandSpy;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerCommandPreprocessEvent;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;

import java.util.ArrayList;

public class commandspy extends PluginBase implements Listener {

    public ArrayList<String> CmdSpy = new ArrayList<String>();

    @Override
    public void onEnable(){

        getLogger().info(TextFormat.GREEN + "has been enabled");
        getServer().getPluginManager().registerEvents(this, this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("CmdSpy")) {
            if (sender.hasPermission("CmdSpy.*"))
                if (!this.CmdSpy.contains(sender.getName())) {
                    sender.sendMessage("§4[CmdSpy] " +  "§7Your now using CmdSpy!");
                    this.CmdSpy.add(sender.getName());
                } else {
                    sender.sendMessage( "§4[CmdSpy] " +"§7Your no longer using CmdSpy!");
                    this.CmdSpy.remove(sender.getName());
                }
            if (!sender.hasPermission("CmdSpy.*"))
                sender.sendMessage("§4[CmdSpy] " + "§7You do not have permission to do this command!");
        }
        return true;
    }

    @EventHandler
    public void PlayerCommand(PlayerCommandPreprocessEvent e) {
        Player player = e.getPlayer();
        for (Player p : getServer().getOnlinePlayers().values()) {
            if (this.CmdSpy.contains(p.getName()))
                p.sendMessage("§c" + player.getName() +  "§4 : §7"  + e.getMessage());
        }
    }
}
