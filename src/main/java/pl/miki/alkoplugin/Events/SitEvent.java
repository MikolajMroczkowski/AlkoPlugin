package pl.miki.alkoplugin.Events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.event.vehicle.VehicleMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static pl.miki.alkoplugin.AlkoPlugin.plugin;

public class SitEvent implements Listener {
    ArrayList<String> sittingPlayers = new ArrayList<String>();
    ArrayList<Material> sitBlocks = new ArrayList<Material>();

    public SitEvent() {
        for (Material material : Material.values()) {
            if (material.name().contains("STAIRS")) {
                sitBlocks.add(material);
            }
        }

    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (sitBlocks.contains(event.getClickedBlock().getType())) {
                Horse horse = (Horse) event.getPlayer().getWorld().spawnEntity(event.getClickedBlock().getLocation().add(0.5, -1, 0.5), EntityType.HORSE);
                horse.setStyle(Horse.Style.NONE);
                Stairs stairs = (Stairs) event.getClickedBlock().getBlockData();
                plugin.getLogger().info(stairs.getFacing().toString());
                switch (stairs.getFacing().toString()) {
                    case "NORTH":
                        horse.teleport(horse.getLocation().add(0, 0, 0.2));
                        break;
                    case "SOUTH":
                        horse.teleport(horse.getLocation().add(0, 0, -0.2));
                        break;
                    case "EAST":
                        horse.teleport(horse.getLocation().add(-0.2, 0, 0));
                        break;
                    case "WEST":
                        horse.teleport(horse.getLocation().add(0.2, 0, 0));
                        break;
                }
                horse.setAI(false);
                horse.setSilent(true);
                horse.setCollidable(false);
                horse.setTamed(false);
                horse.setGravity(false);
                horse.setAdult();
                horse.setInvulnerable(true);
                horse.setInvisible(true);
                horse.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(1);
                horse.addPassenger(event.getPlayer());
                sittingPlayers.add(event.getPlayer().getName());
            }
        }
    }

    @EventHandler
    public void onVehicleExit(VehicleExitEvent event) {
        plugin.getLogger().info("Vehicle exit event");
        if (sittingPlayers.contains(event.getExited().getName())) {
            plugin.getLogger().info("User found");
            sittingPlayers.remove(event.getExited().getName());
            Horse horse = (Horse) event.getVehicle();
            horse.remove();
            event.getExited().teleport(event.getExited().getLocation().add(0, 1, 0));
        }
    }
}

