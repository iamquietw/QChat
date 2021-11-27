package quietw.qchat.Commands;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import quietw.qchat.Chat.AsyncLocalTask;
import quietw.qchat.Chat.Chat;
import quietw.qchat.QChat;

import java.util.Objects;


public class ChatLocal extends Chat {

    private static ChatLocal instance;

    public ChatLocal() {
        instance = this;
    }

    @Override
    public String getName() {
        return "local";
    }

    @Override
    public void sendMessage(Player player, String message) {
        if(message.startsWith("!")) {
            for(Player receiver : Bukkit.getServer().getOnlinePlayers()) {
                receiver.sendMessage(Objects.requireNonNull(QChat.getInstance().getConfig().getString("globalChatFormat"))
                        .replace("%player", player.getDisplayName())
                        .replace("%message", message.replaceFirst("!", ""))
                        .replace("&", "§"));
            }
        } else {
            new AsyncLocalTask(player, message).runTask(QChat.getInstance());
        }
    }

    public static ChatLocal getInstance() {
        return instance;
    }
}
