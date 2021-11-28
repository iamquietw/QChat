package quietw.qchat.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import quietw.qchat.Chat.Chat;
import quietw.qchat.Chat.Chats;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class TabComplete implements TabCompleter {
    @Override
    public List<String> onTabComplete(@Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String alias, @Nonnull String[] args) {
        List<String> commands = new ArrayList<>();
        if(args.length == 1) {
            List<String> completions = new ArrayList<>();
            for(Chat chat : Chats.getInstance().list) {
                completions.add(chat.getName());
            }
            for(String s : completions) {
                if(s.toLowerCase().startsWith(args[0].toLowerCase())) {
                    commands.add(s);
                }
            }
            return commands;
        }
        return null;
    }
}
