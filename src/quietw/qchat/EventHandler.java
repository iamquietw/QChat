package quietw.qchat;

import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import quietw.qchat.Chat.Chat;
import quietw.qchat.Chat.Chats;
import quietw.qchat.Commands.ChatLocal;
import quietw.qchat.Database.DBEditor;

public class EventHandler implements Listener {

    @org.bukkit.event.EventHandler
    public void onJoin(PlayerJoinEvent event) {
        DBEditor.getInstance().selectChat(event.getPlayer(), ChatLocal.getInstance());
    }

    @org.bukkit.event.EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        String chatName = DBEditor.getInstance().getSelectedChat(event.getPlayer());
        for(Chat chat : Chats.getInstance().list) {
            if(chat.getName().equalsIgnoreCase(chatName)) {
                chat.sendMessage(event.getPlayer(), event.getMessage());
            }
        }
        event.setCancelled(true);
    }

}
