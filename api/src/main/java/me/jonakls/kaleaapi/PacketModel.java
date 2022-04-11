package me.jonakls.kaleaapi;

import org.bukkit.entity.Player;

public interface PacketModel {

    void sendTitle(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut);
    void sendPacket(Player player, Object packet);
    void sendActionBar(Player player, String text);

}
