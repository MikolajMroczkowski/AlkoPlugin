package pl.miki.alkoplugin.Events;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

import static pl.miki.alkoplugin.AlkoPlugin.key;

public class MagicPickaxe implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player p = event.getPlayer();
        Block origin= event.getBlock();
        ArrayList<Block> blocks = new ArrayList<>();
        ItemMeta itemMeta = p.getInventory().getItemInMainHand().getItemMeta();
        if(itemMeta == null){
            return;
        }
        PersistentDataContainer pdc = itemMeta.getPersistentDataContainer();
        if(!pdc.has(key, PersistentDataType.STRING)){
            return;
        }
        Boolean isMagicPickaxe = pdc.get(key, PersistentDataType.STRING).equals("magic-pickaxe");
        if(isMagicPickaxe){

            switch (getCardinalDirection(p)){
                case NORTH, SOUTH:
                    for(int x = -1; x<2; x++){
                        for(int y = -1; y<2; y++){
                            blocks.add(origin.getLocation().add(x, y, 0).getBlock());
                        }
                    }
                    break;
                case EAST, WEST:
                    for(int z = -1; z<2; z++){
                        for(int y = -1; y<2; y++){
                            blocks.add(origin.getLocation().add(0, y, z).getBlock());
                        }
                    }
                    break;
                case UP, DOWN:
                    for(int x = -1; x<2; x++){
                        for(int z = -1; z<2; z++){
                            blocks.add(origin.getLocation().add(x, 0, z).getBlock());
                        }
                    }
                    break;
            }
            for(Block b : blocks){
                if(b.getType().isBlock()){
                    b.breakNaturally();
                    for (ItemStack drops : b.getDrops()) {
                        origin.getLocation().getWorld().dropItemNaturally(origin.getLocation(), drops);
                    }
                }
            }
        }
    }
    PlayerDirection getCardinalDirection(Player player) {
        double rotation = (player.getLocation().getYaw() - 90) % 360;
        float y = player.getLocation().getPitch();
        if (rotation < 0) {
            rotation += 360.0;
        }
        if (y < -45) {
            return PlayerDirection.UP;
        } else if (y > 45) {
            return PlayerDirection.DOWN;
        }
        if (0 <= rotation && rotation < 45.0) {
            return PlayerDirection.WEST;
        } else if (45.0 <= rotation && rotation < 135.0) {
            return PlayerDirection.NORTH;
        } else if (135.0 <= rotation && rotation < 225.0) {
            return PlayerDirection.EAST;
        } else if (225.0 <= rotation && rotation < 315.0) {
            return PlayerDirection.SOUTH;
        } else if (315.0 <= rotation && rotation < 360.0) {
            return PlayerDirection.WEST;
        } else {
            return null;
        }
    }
    enum PlayerDirection {
        UP, DOWN, NORTH, EAST, SOUTH, WEST
    }
}
