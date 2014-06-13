package me.apottere.ohtools;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

/**
 * @author Andrew Potter
 */
public class ToolListener implements Listener {

    OffhandTools plugin;

    ToolListener(OffhandTools plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerRightClick(PlayerInteractEvent event) {

        if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();

            if(!player.isSneaking()) {
                PlayerInventory inventory = player.getInventory();

                if (inventory.getHeldItemSlot() < 8) {
                    ItemStack held = player.getItemInHand();
                    Material heldType = held.getType();

                    if (heldType.name().endsWith("_AXE") ||
                            heldType.name().endsWith("_PICKAXE") ||
                            heldType.name().endsWith("_SHOVEL") ||
                            heldType.name().endsWith("_HOE")) {

                        ItemStack item = inventory.getItem(inventory.getHeldItemSlot() + 1);

                        if (item.getType().isBlock()) {
                            Block block = event.getClickedBlock().getRelative(event.getBlockFace());

                            if (block.isEmpty() || block.isLiquid()) {
                                block.setType(item.getType());
                                item.setAmount(item.getAmount() - 1);
                                player.getInventory().setItem(player.getInventory().getHeldItemSlot() + 1, item);
                                player.playSound(player.getLocation(), Sound.STEP_STONE, 10, 0.9f);
                                player.updateInventory();
                                event.setCancelled(true);
                            }
                        }
                    }
                }
            }
        }
    }
}
