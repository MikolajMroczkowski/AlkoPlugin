package pl.miki.alkoplugin.Items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.List;

import static pl.miki.alkoplugin.AlkoPlugin.key;

public class BeerItem extends ItemStack {
    public BeerItem(boolean price) {
        super(Material.POTION);
        PotionMeta beerMeta = (PotionMeta) super.getItemMeta();
        beerMeta.displayName(Component.text("PIWO").color(NamedTextColor.AQUA).decoration(TextDecoration.BOLD, true));
        beerMeta.getPersistentDataContainer().set( key, PersistentDataType.STRING, "beer");
        ArrayList<Component> lore = new ArrayList<>(List.of(
                Component.text("Bedziesz najebany").color(NamedTextColor.DARK_PURPLE).decoration(TextDecoration.ITALIC, true)
        ));
        if(price){
            lore.add(Component.text("Cena 5 litrów").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, true));
        }
        beerMeta.lore(lore);
        beerMeta.addCustomEffect(PotionEffectType.SLOW.createEffect(20*2*60, 1), true);
        beerMeta.addCustomEffect(PotionEffectType.REGENERATION.createEffect(20*45, 1), true);
        beerMeta.addCustomEffect(PotionEffectType.SATURATION.createEffect(20*45, 5), true);
        beerMeta.addCustomEffect(PotionEffectType.WEAKNESS.createEffect(20*5*60, 1), true);
        beerMeta.addCustomEffect(PotionEffectType.CONFUSION.createEffect(20*60, 1), true);
        beerMeta.addCustomEffect(PotionEffectType.LEVITATION.createEffect(20*2, 1), true);
        beerMeta.addCustomEffect(PotionEffectType.SLOW_FALLING.createEffect(20*15, 1), true);
        beerMeta.addCustomEffect(PotionEffectType.FIRE_RESISTANCE.createEffect(1, 1), true);
        beerMeta.addItemFlags(ItemFlag.HIDE_ITEM_SPECIFICS);
        beerMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        beerMeta.setColor(Color.ORANGE);
        super.setItemMeta(beerMeta);
    }
}
