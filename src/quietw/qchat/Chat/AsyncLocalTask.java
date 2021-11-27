package quietw.qchat.Chat;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import quietw.qchat.QChat;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AsyncLocalTask extends BukkitRunnable {

    public Player player;
    public String message;

    public AsyncLocalTask(Player player, String message) {
        this.player = player;
        this.message = message;
    }

    @Override
    public void run() {
        List<String> receivers = new ArrayList<>();
        for(Entity entity : Objects.requireNonNull(player.getLocation().getWorld()).getNearbyEntities(player.getLocation(), QChat.getInstance().getConfig().getInt("localChatRadius"), 255, QChat.getInstance().getConfig().getInt("localChatRadius"))) {
            if(entity.getType() == EntityType.PLAYER) {
                Player receiver = (Player)entity;
                QChat.getInstance().getLogger().info(receiver.toString());
                if(!receivers.contains(receiver.getName())) {
                    receivers.add(receiver.getName());
                    receiver.sendMessage(Objects.requireNonNull(QChat.getInstance().getConfig().getString("localChatFormat"))
                            .replace("%player", player.getDisplayName())
                            .replace("%message", message)
                            .replace("&", "§"));
                }
            }
        }
    }
}
