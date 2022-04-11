package me.jonakls.kaleabukkit;

import me.jonakls.kaleaapi.TitleModel;
import me.jonakls.kaleabukkit.reflect.PacketsReflect;
import org.bukkit.entity.Player;

public class KaleaBukkit {

    private PacketsReflect packetsReflect;

    public KaleaBukkit() {
        this.packetsReflect = new PacketsReflect();
    }

    public void sendTitle(Player player, TitleModel titleModel) {
        this.packetsReflect.sendTitle(player, titleModel);
    }

    public void sendActionBar(Player player, String actionBar) {
        this.packetsReflect.sendActionBar(player, actionBar);
    }

}
