package pl.miki.alkoplugin.Managers;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static pl.miki.alkoplugin.AlkoPlugin.plugin;

public class ShopManager implements Listener {
    public static Inventory shopInventory = plugin.getServer().createInventory(null, 9, "Szklep");


    public ShopManager() {
        //bicz definition
        ItemStack magicStick = new ItemStack(Material.STICK, 1);
        ItemMeta magicStickItemMeta = magicStick.getItemMeta();
        magicStickItemMeta.displayName(Component.text("Kij od miotły").color(NamedTextColor.LIGHT_PURPLE).decoration(TextDecoration.BOLD, true));
        magicStickItemMeta.lore(new ArrayList<>(List.of(
                Component.text("Jak wpierdolisz to się nie pozbiera").color(NamedTextColor.DARK_PURPLE).decoration(TextDecoration.ITALIC, true),
                Component.text("Cena 500 litrów").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, true)
        )));
        magicStick.setItemMeta(magicStickItemMeta);
        magicStick.addUnsafeEnchantment(Enchantment.KNOCKBACK, 50);
        //totem definition
        ItemStack totem = new ItemStack(Material.TOTEM_OF_UNDYING, 1);
        ItemMeta totemItemMeta = totem.getItemMeta();
        totemItemMeta.displayName(Component.text("Statuetka nie-zgonu").color(NamedTextColor.GREEN).decoration(TextDecoration.BOLD, true));
        totemItemMeta.lore(new ArrayList<>(List.of(
                Component.text("Magiczna statuetka nie-zgonu").color(NamedTextColor.DARK_PURPLE).decoration(TextDecoration.ITALIC, true),
                Component.text("Cena 50 litrów").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, true)
        )));
        totem.setItemMeta(totemItemMeta);
        //podpięcia do inventory
        shopInventory.setItem(0, totem);
        shopInventory.setItem(1, magicStick);
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
                        player.sendMessage(Component.text("Kupiłeś bicz starego").color(NamedTextColor.GREEN));
                        ItemStack magicStick = new ItemStack(Material.STICK, 1);
                        ItemMeta magicStickItemMeta = magicStick.getItemMeta();
                        magicStickItemMeta.displayName(Component.text("Kij od miotły").color(NamedTextColor.LIGHT_PURPLE).decoration(TextDecoration.BOLD, true));
                        magicStickItemMeta.lore(new ArrayList<>(List.of(
                                Component.text("Jak wpierdolisz to się nie pozbiera").color(NamedTextColor.DARK_PURPLE).decoration(TextDecoration.ITALIC, true)
                        )));
                        magicStick.setItemMeta(magicStickItemMeta);
                        magicStick.addUnsafeEnchantment(Enchantment.KNOCKBACK, 50);
                        player.getInventory().addItem(magicStick);
                        player.playSound(player.getLocation(), "minecraft:block.amethyst_cluster.step", 1, 1);
                    } else {
                        player.sendMessage(Component.text("Nie masz wystarczająco pieniędzy").color(NamedTextColor.RED));
                        player.playSound(player.getLocation(), "minecraft:block.note_block.bass", 1, 1);
                    }
                    break;
                case TOTEM_OF_UNDYING:
                    if (MoneyManager.getMoney(player.getName()) >= 50) {
                        MoneyManager.removeMoney(player.getName(), 50);
                        ItemStack totem = new ItemStack(Material.TOTEM_OF_UNDYING, 1);
                        ItemMeta totemItemMeta = totem.getItemMeta();
                        totemItemMeta.displayName(Component.text("Statuetka nie-zgonu").color(NamedTextColor.GREEN).decoration(TextDecoration.BOLD, true));
                        totemItemMeta.lore(new ArrayList<>(List.of(
                                Component.text("Magiczna statuetka nie-zgonu").color(NamedTextColor.DARK_PURPLE).decoration(TextDecoration.ITALIC, true)
                        )));
                        totem.setItemMeta(totemItemMeta);
                        player.getInventory().addItem(totem);
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
