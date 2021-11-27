package quietw.qchat.Chat;

import org.bukkit.entity.Player;

public abstract class Chat {

    public Chat() {
        Chats.getInstance().registerChat(this);
    }
    public abstract String getName();
    public abstract void sendMessage(Player player, String message);
}
