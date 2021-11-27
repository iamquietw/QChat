package quietw.qchat.Chat;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import quietw.qchat.QChat;

import java.util.Objects;

public class ChatGlobal extends Chat{

    private static ChatGlobal instance;

    public ChatGlobal() {

    }

    @Override
    public String getName() {
        return "global";
    }

    @Override
    public void sendMessage(Player sender, String message) {
        for(Player receiver : Bukkit.getServer().getOnlinePlayers()) {
            receiver.sendMessage(Objects.requireNonNull(QChat.getInstance().getConfig().getString("globalChatFormat"))
                    .replace("%player", sender.getDisplayName())
                    .replace("%message", message)
                    .replace("&", "§"));
        }
    }
}
