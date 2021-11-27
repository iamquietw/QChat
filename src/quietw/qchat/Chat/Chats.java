package quietw.qchat.Chat;

import java.util.ArrayList;

public class Chats {
    private static Chats instance;
    public ArrayList<Chat> list = new ArrayList<>();

    public Chats() {
        instance = this;
    }

    public static Chats getInstance() {
        return instance;
    }

    public void registerChat(Chat chat) {
        if(!list.contains(chat)) {
            list.add(chat);
        }
        instance = this;
    }

    public ArrayList<Chat> getList() {
        return list;
    }

}
