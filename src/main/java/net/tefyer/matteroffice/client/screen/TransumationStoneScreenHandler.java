package net.tefyer.matteroffice.client.screen;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.math.BlockPos;
import net.tefyer.matteroffice.client.menus.MenuRegistry;
import org.jetbrains.annotations.NotNull;

public class TransumationStoneScreenHandler extends ScreenHandler {
    private final Inventory inventory;

    public TransumationStoneScreenHandler(int syncId, PlayerInventory playerInventory, BlockPos blockPos) {
        super(MenuRegistry.TRANSUMATION_STONE_HANDLER, syncId);
        this.inventory = ((Inventory) playerInventory.player.getWorld().getBlockEntity(blockPos));


        addPInventory(playerInventory,35, 117);
    }

    @Override
    public void onSlotClick(int slotIndex, int button, SlotActionType actionType, PlayerEntity player) {
        super.onSlotClick(slotIndex, button, actionType, player);
    }

    protected void addPInventory(PlayerInventory playerInventory, int xStart, int yStart) {
        int slotSize = 18;
        int rows = 3;
        //Main Inventory
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < 9; j++) {
                addSlot(createSlot(playerInventory, j + i * 9 + 9, xStart + j * slotSize, yStart + i * slotSize));
            }
        }
        yStart = yStart + slotSize * rows + 4;
        //Hot Bar
        for (int i = 0; i < 9; i++) {
            addSlot(createSlot(playerInventory, i, xStart + i * slotSize, yStart));
        }
    }


    private Slot createSlot(PlayerInventory playerInventory, int index, int x, int y){
        return new Slot(playerInventory, index, x, y);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }


    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }
        return newStack;
    }
}
