package net.tefyer.matteroffice.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tefyer.matteroffice.MatterOffice;

public class BlockRegistry {

    public static final TransmutationStoneBlock TRANSMUTATION_TABLE_BLOCK = register("transumation_table",
            new TransmutationStoneBlock(AbstractBlock.Settings.create().strength(4.0f)));

    private static <T extends Block> T register(String path, T block) {
        Registry.register(Registries.BLOCK, Identifier.of(MatterOffice.MOD_ID, path), block);
        Registry.register(Registries.ITEM, Identifier.of(MatterOffice.MOD_ID, path),
                new BlockItem(block, new Item.Settings()));
        return block;
    }

    public static void init(){}
}
