package me.jonakls.kaleaapi;

import org.bukkit.entity.Player;

public interface PacketModel {

    void sendTitle(Player player, TitleModel titleModel);
    void sendActionBar(Player player, String text);
    void sendPacket(Player player, Object packet);

}
