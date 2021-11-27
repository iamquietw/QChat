package quietw.qchat.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import quietw.qchat.QChat;

import javax.annotation.Nonnull;

public abstract class AbstractCommand implements CommandExecutor {

    public AbstractCommand(String command) {
        PluginCommand pluginCommand = QChat.getInstance().getCommand(command);
        assert pluginCommand != null;
        pluginCommand.setExecutor(this);
    }

    public abstract void execute(CommandSender sender, String label, String[] args);

    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String label, @Nonnull String[] args) {
        execute(sender, label, args);
        return true;
    }
}
