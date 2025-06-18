package net.tefyer.matteroffice.client.screen.invent;

import lombok.Getter;
import net.minecraft.inventory.Inventory;

public class TransumationInventory {
    @Getter
    public Inventory inventory;
    public TransumationInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
