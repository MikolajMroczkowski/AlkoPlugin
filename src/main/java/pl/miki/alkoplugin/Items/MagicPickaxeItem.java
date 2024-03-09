package pl.miki.alkoplugin.Items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

import static pl.miki.alkoplugin.AlkoPlugin.key;

public class MagicPickaxeItem extends ItemStack {
    public MagicPickaxeItem(boolean price){
        super(Material.DIAMOND_PICKAXE);
        ItemMeta picaxeMeta = super.getItemMeta();
        picaxeMeta.displayName(Component.text("Dojebany kiluf").color(NamedTextColor.LIGHT_PURPLE).decoration(TextDecoration.BOLD, true));
        picaxeMeta.setUnbreakable(false);
        picaxeMeta.setUnbreakable(true);
        picaxeMeta.getPersistentDataContainer().set( key, PersistentDataType.STRING, "magic-pickaxe");
        ArrayList<TextComponent> lore = new ArrayList<>(List.of(
                Component.text("Kilof kopiący 3x3").color(NamedTextColor.DARK_PURPLE).decoration(TextDecoration.ITALIC, true),
                Component.text("Zajebisty do kretowania").color(NamedTextColor.DARK_PURPLE).decoration(TextDecoration.ITALIC, true)
        ));
        if(price){
            lore.add(Component.text("Cena 400 litrów").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, true));
        }
        picaxeMeta.lore(lore);
        super.setItemMeta(picaxeMeta);

    }
}
