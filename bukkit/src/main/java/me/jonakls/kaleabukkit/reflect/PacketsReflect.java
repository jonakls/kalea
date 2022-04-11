package me.jonakls.kaleabukkit.reflect;

import me.jonakls.kaleaapi.PacketModel;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class PacketsReflect implements PacketModel {

    private static final String VERSION = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];

    private static final Class<?> PACKET_OUT_TITLE = getNMSClass("PacketPlayOutTitle");
    private static final Class<?> PACKET_OUT_CHAT = getNMSClass("PacketPlayOutChat");
    private static final Class<?> CHAT_BASE_COMPONENT = getNMSClass("IChatBaseComponent");
    private static final Class<?> CHAT_COMPONENT = CHAT_BASE_COMPONENT.getDeclaredClasses()[0];
    private static final Class<?> PACKET_OUT_TITLE_CLASS = PACKET_OUT_TITLE.getDeclaredClasses()[0];

    private static Field TITLE_FIELD = null;
    private static Field SUBTITLE_FIELD = null;
    private static Method CHAT_COMPONENT_SERIALIZER = null;

    static {
        try {
            TITLE_FIELD = PACKET_OUT_TITLE_CLASS.getField("SUBTITLE");
            SUBTITLE_FIELD = PACKET_OUT_TITLE_CLASS.getField("TITLE");
            CHAT_COMPONENT_SERIALIZER = CHAT_BASE_COMPONENT.getMethod("a", String.class);
        } catch (NoSuchFieldException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private static Class<?> getNMSClass(String name) {
        try {
            return Class.forName("net.minecraft.server." + VERSION + "." + name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Object getChatComponent(String text) {
        Object chatComponent = null;
        try {
            chatComponent = CHAT_COMPONENT_SERIALIZER.invoke(null, text);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chatComponent;
    }

    @Override
    public void sendTitle(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        Constructor<?> subtitleConstructor;
        Object titlePacket;
        Object subtitlePacket;

        title = title.replaceAll("%player%", player.getName());
        subtitle = subtitle.replaceAll("%player%", player.getName());

        try {
            subtitleConstructor = PACKET_OUT_TITLE.getConstructor(
                    PACKET_OUT_TITLE_CLASS,
                    CHAT_COMPONENT,
                    Integer.TYPE,
                    Integer.TYPE,
                    Integer.TYPE);
            titlePacket = subtitleConstructor.newInstance(TITLE_FIELD.get(null),
                    getChatComponent("{\"text\":\"" + title + "\"}"),
                    fadeIn,
                    stay,
                    fadeOut);
            subtitleConstructor = PACKET_OUT_TITLE.getConstructor(
                    PACKET_OUT_TITLE_CLASS,
                    CHAT_COMPONENT,
                    Integer.TYPE,
                    Integer.TYPE,
                    Integer.TYPE);
            subtitlePacket = subtitleConstructor.newInstance(SUBTITLE_FIELD.get(null),
                    getChatComponent("{\"text\":\"" + subtitle + "\"}"),
                    fadeIn,
                    stay,
                    fadeOut);
            sendPacket(player, titlePacket);
            sendPacket(player, subtitlePacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendActionBar(Player player, String text) {
        Constructor<?> actionBarConstructor;
        Object actionBarPacket;

        text = text.replaceAll("%player%", player.getName());

        try {
            actionBarConstructor = PACKET_OUT_CHAT.getConstructor(CHAT_COMPONENT, Byte.TYPE);
            actionBarPacket = actionBarConstructor.newInstance(
                    getChatComponent("{\"text\":\"" + text + "\"}"),
                    (byte) 2
            );

            sendPacket(player, actionBarPacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendPacket(Player player, Object packet) {
        try {
            Object handle = player.getClass().getMethod("getHandle").invoke(player);
            Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
            playerConnection.getClass()
                    .getMethod("sendPacket", getNMSClass("Packet"))
                    .invoke(playerConnection, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
