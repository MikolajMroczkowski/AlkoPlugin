package pl.miki.alkoplugin.Managers;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Bee;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.jetbrains.annotations.Nullable;
import pl.miki.alkoplugin.Items.BeerItem;
import pl.miki.alkoplugin.Items.MagicPickaxeItem;
import pl.miki.alkoplugin.Items.MagicStickItem;
import pl.miki.alkoplugin.Items.TotemItem;

import java.util.ArrayList;
import java.util.List;

import static pl.miki.alkoplugin.AlkoPlugin.plugin;
import static pl.miki.alkoplugin.AlkoPlugin.key;

public class ShopManager implements Listener {
    public static Inventory shopInventory = plugin.getServer().createInventory(null, 9, "Szklep");

    public ShopManager() {
        //podpięcia do inventory
        shopInventory.setItem(0, new TotemItem(true));
        shopInventory.setItem(1, new MagicStickItem(true));
        shopInventory.setItem(2, new MagicPickaxeItem(true));
        shopInventory.setItem(3, new BeerItem(true));
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked(); // The player that clicked the item
        ItemStack clicked = event.getCurrentItem(); // The item that was clicked
        Inventory inventory = event.getInventory(); // The inventory that was clicked in
        if (inventory == shopInventory) {
            event.setCancelled(true);
            if(clicked == null || clicked.getType() == Material.AIR) return;
            switch (clicked.getType()){
                case STICK:
                    if (MoneyManager.getMoney(player.getName()) >= 500) {
                        MoneyManager.removeMoney(player.getName(), 500);
                        player.getInventory().addItem(new MagicStickItem(false));
                        player.playSound(player.getLocation(), "minecraft:block.amethyst_cluster.step", 1, 1);
                    } else {
                        player.sendMessage(Component.text("Nie masz wystarczająco pieniędzy").color(NamedTextColor.RED));
                        player.playSound(player.getLocation(), "minecraft:block.note_block.bass", 1, 1);
                    }
                    break;
                case TOTEM_OF_UNDYING:
                    if (MoneyManager.getMoney(player.getName()) >= 50) {
                        MoneyManager.removeMoney(player.getName(), 50);
                        player.getInventory().addItem(new TotemItem(false));
                        player.playSound(player.getLocation(), "minecraft:block.amethyst_cluster.step", 1, 1);
                    } else {
                        player.sendMessage(Component.text("Nie masz wystarczająco pieniędzy").color(NamedTextColor.RED));
                        player.playSound(player.getLocation(), "minecraft:block.note_block.bass", 1, 1);
                    }
                    break;
                case DIAMOND_PICKAXE:
                    if (MoneyManager.getMoney(player.getName()) >= 400) {
                        MoneyManager.removeMoney(player.getName(), 400);
                        player.getInventory().addItem(new MagicPickaxeItem(false));
                        player.playSound(player.getLocation(), "minecraft:block.amethyst_cluster.step", 1, 1);
                    } else {
                        player.sendMessage(Component.text("Nie masz wystarczająco pieniędzy").color(NamedTextColor.RED));
                        player.playSound(player.getLocation(), "minecraft:block.note_block.bass", 1, 1);
                    }
                    break;
                case POTION:
                    if (MoneyManager.getMoney(player.getName()) >= 5) {
                        MoneyManager.removeMoney(player.getName(), 5);
                        player.getInventory().addItem(new BeerItem(false));
                        player.playSound(player.getLocation(), "minecraft:block.amethyst_cluster.step", 1, 1);
                    } else {
                        player.sendMessage(Component.text("Nie masz wystarczająco pieniędzy").color(NamedTextColor.RED));
                        player.playSound(player.getLocation(), "minecraft:block.note_block.bass", 1, 1);
                    }
                    break;
            }

        }

    }
}
