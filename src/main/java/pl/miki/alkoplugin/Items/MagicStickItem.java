package pl.miki.alkoplugin.Items;

import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import net.kyori.adventure.text.Component;

import java.util.ArrayList;
import java.util.List;

public class MagicStickItem extends ItemStack {
    public MagicStickItem(boolean price){
        super(Material.STICK);
        ItemMeta magicStickMeta = super.getItemMeta();
        magicStickMeta.displayName(Component.text("Kij od szczotki").color(NamedTextColor.LIGHT_PURPLE).decoration(TextDecoration.BOLD, true));
        ArrayList<Component> lore = new ArrayList<>(List.of(
                Component.text("Jak wpierdolisz to się nie pozbiera").color(NamedTextColor.DARK_PURPLE).decoration(TextDecoration.ITALIC, true)
        ));
        if(price){
            lore.add(Component.text("Cena 500 litrów").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, true));
        }
        super.addUnsafeEnchantment(Enchantment.KNOCKBACK, 50);
        magicStickMeta.lore(lore);
        super.setItemMeta(magicStickMeta);
    }
}
