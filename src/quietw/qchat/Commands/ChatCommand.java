package quietw.qchat.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import quietw.qchat.Chat.Chat;
import quietw.qchat.Chat.Chats;
import quietw.qchat.Database.DBEditor;
import quietw.qchat.QChat;

import javax.annotation.Nonnull;

public class ChatCommand extends AbstractCommand {

    public ChatCommand() {
        super("chat");
    }

    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        if(args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Command usage: /" + label + " <chatName>");
        } else if(args.length == 1) {
            String selectedChat = args[0].toLowerCase();
            boolean isExists = false;
            for(Chat chat : Chats.getInstance().list) {
                if(chat.getName().equalsIgnoreCase(selectedChat)) {
                    isExists = true;
                    DBEditor.getInstance().selectChat((Player)sender, chat);
                    sender.sendMessage(ChatColor.GREEN + "Вы выбрали чат: " + chat.getName().toUpperCase());
                }
            }
        } else {
            for(Chat chat : Chats.getInstance().list) {
                sender.sendMessage(chat.getName());
            }
        }
    }
}
