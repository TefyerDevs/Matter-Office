package net.tefyer.matteroffice.client.screen;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.math.BlockPos;
import net.tefyer.matteroffice.MatterOffice;
import net.tefyer.matteroffice.client.menus.MenuRegistry;
import net.tefyer.matteroffice.client.screen.invent.TransumationInventory;
import net.tefyer.matteroffice.client.screen.slot.SlotConsume;
import net.tefyer.matteroffice.client.screen.slot.SlotUnlearn;
import net.tefyer.matteroffice.data.ModMessages;
import net.tefyer.matteroffice.data.emc.EMCPayload;
import net.tefyer.matteroffice.database.DataBase;
import org.jetbrains.annotations.NotNull;

public class TransumationStoneScreenHandler extends ScreenHandler {
    private final TransumationInventory transumationInventory;
    private final Inventory inventory;

    public TransumationStoneScreenHandler(int syncId, PlayerInventory playerInventory, BlockPos blockPos) {
        super(MenuRegistry.TRANSUMATION_STONE_HANDLER, syncId);
        this.inventory = ((Inventory) playerInventory.player.getWorld().getBlockEntity(blockPos));
        this.transumationInventory = new TransumationInventory(inventory);

        addSlot(new SlotConsume(inventory, transumationInventory, 0, 107, 97));
//        addSlot(unlearn = new SlotUnlearn(inventory, transumationInventory, 1, 89, 97));

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

            if(slot.getIndex() == 0 && MatterOffice.database.has(originalStack.getItem())){
                long itemEMC = MatterOffice.database.get(originalStack.getItem());
                if(itemEMC > 0){
                    ClientPlayNetworking.send(new EMCPayload((int) itemEMC));
                }
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
