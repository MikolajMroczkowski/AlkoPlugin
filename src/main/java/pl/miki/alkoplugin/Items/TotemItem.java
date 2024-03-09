package pl.miki.alkoplugin.Items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class TotemItem extends ItemStack {
    public TotemItem(boolean price) {
        super(Material.TOTEM_OF_UNDYING);
        ItemMeta totemItemMeta = super.getItemMeta();
        totemItemMeta.displayName(Component.text("Statuetka nie-zgonu").color(NamedTextColor.GREEN).decoration(TextDecoration.BOLD, true));
        ArrayList<TextComponent> lore = new ArrayList<>(List.of(
                Component.text("Magiczna statuetka nie-zgonu").color(NamedTextColor.DARK_PURPLE).decoration(TextDecoration.ITALIC, true)
        ));
        if(price){
            lore.add(Component.text("Cena 50 litrów").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, true));
        }
        totemItemMeta.lore(lore);
        super.setItemMeta(totemItemMeta);
    }
}
