package net.tefyer.matteroffice.block;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tefyer.matteroffice.MatterOffice;
import net.tefyer.matteroffice.block.entity.TransumationStoneBlockEntity;

import java.util.function.Supplier;

public class BlockEntityRegistry {

    public static final BlockEntityType<TransumationStoneBlockEntity> PEDESTAL_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(MatterOffice.MOD_ID, "transmutation_table"),
                    BlockEntityType.Builder.create(TransumationStoneBlockEntity::new, BlockRegistry.TRANSMUTATION_TABLE_BLOCK).build(null));

    public static void init(){}

}
