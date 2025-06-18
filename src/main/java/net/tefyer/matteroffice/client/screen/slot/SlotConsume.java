package net.tefyer.matteroffice.client.screen.slot;

import lombok.Getter;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.slot.Slot;
import net.tefyer.matteroffice.client.screen.invent.TransumationInventory;

public class SlotConsume extends Slot {
    @Getter
    private final TransumationInventory inventory;

    public SlotConsume(Inventory inventory,TransumationInventory transumationInventory, int index, int x, int y) {
        super(inventory, index, x, y);
        this.inventory = transumationInventory;
    }

}
