package quietw.qchat.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import quietw.qchat.Chat.Chat;
import quietw.qchat.Chat.Chats;
import quietw.qchat.Database.DBEditor;

public class ChatCommand extends AbstractCommand {

    public ChatCommand() {
        super("chat");
    }

    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        if(args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Список чатов: " + Chats.getInstance().getChatNames());
        } else if(args.length == 1) {
            String selectedChat = args[0].toLowerCase();
            for(Chat chat : Chats.getInstance().list) {
                if(chat.getName().equalsIgnoreCase(selectedChat)) {
                    DBEditor.getInstance().selectChat((Player)sender, chat);
                    sender.sendMessage(ChatColor.GREEN + "Вы выбрали чат: " + chat.getName().toUpperCase());
                    return;
                }
            }
            sender.sendMessage(ChatColor.RED + "Чата " + selectedChat.toUpperCase() + " не существует. Доступные чаты: " + Chats.getInstance().getChatNames());
        } else {
            for(Chat chat : Chats.getInstance().list) {
                sender.sendMessage(chat.getName());
            }
        }
    }
}
