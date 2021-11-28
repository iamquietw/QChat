package quietw.qchat;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import quietw.qchat.Chat.ChatGlobal;
import quietw.qchat.Chat.Chats;
import quietw.qchat.Commands.ChatCommand;
import quietw.qchat.Chat.ChatLocal;
import quietw.qchat.Database.DBEditor;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class QChat extends JavaPlugin {
    private static QChat instance;

    File config = new File(getDataFolder(), "config.yml");
    Connection connection;

    @Override
    public void onEnable() {
        instance = this;
        new Chats();
        new ChatCommand();
        new ChatGlobal();
        new ChatLocal();
        Bukkit.getServer().getPluginManager().registerEvents(new EventHandler(), this);
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + (getDataFolder() + File.separator + "data.db"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        new DBEditor(connection);
        if(!config.exists()) {
            getConfig().options().copyDefaults(true);
            saveDefaultConfig();
        }
    }

    @Override
    public void onDisable() {
        try {
            connection.close();
            getLogger().info("DB Connection closed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static QChat getInstance() {
        return instance;
    }
}
