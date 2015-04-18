package io.teamelite.brush_menu.system;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.HashMap;
import java.util.Map;

/**
 * @name 		BrushMenu
 * @author 		Liam Reffell and Kieron Wiltshire
 * @contact 	http://www.mcteamelite.com/
 * @license 	MIT License
 * @description
 * 				The plugin requires Java 1.6 or higher and depends on the VoxelSniper
 * 				Bukkit plugin. It allows a user to open up an inventory interface and
 * 				select their desired VoxelSniper brush.
 */
public class InventoryMenu {

    // Instance properties
    private Inventory inventory;
    private HashMap<Integer, MenuItem> items;

    /**
     * MenuInventory constructor
     *
     * @param holder The holder of the inventory
     */
    public InventoryMenu(InventoryHolder holder, String title) {
        this.inventory = Bukkit.createInventory(holder, InventoryType.CHEST, title);
        this.items = new HashMap<Integer, MenuItem>();
    }

    /**
     * Get the inventory
     *
     * @return The Inventory instance
     */
    public Inventory getInventory() {
        return this.inventory;
    }

    /**
     * Add a MenuItem to the inventory
     *
     * @param slot The inventory slot of the item
     * @param item The item to add to the inventory menu
     */
    public void addItem(int slot, MenuItem item) {
        this.items.put(slot, item);
    }

    /**
     * Add a MenuItem to the inventory
     *
     * @param item The item to add to the inventory menu
     */
    public void addItem(MenuItem item) {
        for(int i = 0; i < inventory.getSize(); i++) {
            if (!this.items.containsKey(i)) {
                this.addItem(i, item);
                return;
            }
        }
    }

    /**
     * Remove an item from the inventory
     *
     * @param slot The item slot of which to remove
     */
    public void removeItem(int slot) {
        if (this.items.containsKey(slot)) {
            this.items.remove(slot);

            // Remove it from the Inventory instance itself
            // TODO
        }
    }

    /**
     * Remove an item from the inventory
     *
     * @param item The item to remove
     */
    public void removeItem(MenuItem item) {
        if (this.items.containsValue(item)) {
            for (Map.Entry<Integer, MenuItem> entry : this.items.entrySet()) {
                if (entry.getValue().equals(item)) {
                    this.removeItem(entry.getKey());
                }
            }
        }
    }

}
