package pl.miki.alkoplugin.Events;

import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffectType;

import static pl.miki.alkoplugin.AlkoPlugin.key;

public class BeerPotion implements Listener {
    public void onPotionDrink(PlayerItemConsumeEvent e){
        ItemStack is = e.getItem();
        if(is.getType().equals(Material.POTION)){
            ItemMeta itemMeta =is.getItemMeta();
            if(itemMeta == null){
                return;
            }
            PersistentDataContainer pdc = itemMeta.getPersistentDataContainer();
            if(!pdc.has(key, PersistentDataType.STRING)){
                return;
            }
            boolean isBeer = pdc.get(key, PersistentDataType.STRING).equals("beer");
            if(isBeer){
                e.getPlayer().removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
                e.getPlayer().addPotionEffect(PotionEffectType.SPEED.createEffect(2*60, 1));
                e.getPlayer().addPotionEffect(PotionEffectType.REGENERATION.createEffect(30, 1));
                e.getPlayer().addPotionEffect(PotionEffectType.WEAKNESS.createEffect(5*60, 1));
                e.getPlayer().addPotionEffect(PotionEffectType.BLINDNESS.createEffect(60, 1));
                e.getPlayer().addPotionEffect(PotionEffectType.CONFUSION.createEffect(60, 1));
                e.getPlayer().addPotionEffect(PotionEffectType.LEVITATION.createEffect(5, 5));
                e.getPlayer().addPotionEffect(PotionEffectType.SLOW_FALLING.createEffect(15, 1));

            }
        }
    }
}

