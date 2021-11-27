package quietw.qchat.Database;

import org.bukkit.entity.Player;
import quietw.qchat.Chat.Chat;
import quietw.qchat.QChat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBEditor {

    Connection connection;
    public static DBEditor instance;

    public DBEditor(Connection con) {
        connection = con;
        QChat.getInstance().getLogger().info("DB Connected");
        String query = "CREATE TABLE IF NOT EXISTS 'users' (" +
                "'username' TEXT NOT NULL UNIQUE, " +
                "'chat' TEXT NOT NULL " +
                ")";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            String query_delete = "DELETE FROM users";
            statement.executeUpdate(query_delete);
            statement.close();
            QChat.getInstance().getLogger().info("DB Table created.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        instance = this;
    }

    public void selectChat(Player player, Chat chat) {
        String name = player.getName();
        String query = String.format("INSERT INTO users VALUES('%s', '%s')", name, chat.getName());
        String query_delete = String.format("DELETE FROM 'users' WHERE username='%s'", name);
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query_delete);
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getSelectedChat(Player player) {
        String name = player.getName();
        String query = "SELECT chat FROM users WHERE username = '" + name + "'";
        String result = "none";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                result = rs.getString("chat");
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static DBEditor getInstance() {
        return instance;
    }

}
