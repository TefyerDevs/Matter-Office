package net.tefyer.matteroffice.client.menus;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.tefyer.matteroffice.MatterOffice;
import net.tefyer.matteroffice.client.screen.TransumationStoneScreenHandler;

public class MenuRegistry {

    public static final ScreenHandlerType<TransumationStoneScreenHandler> TRANSUMATION_STONE_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(MatterOffice.MOD_ID, "transumation_stone_handler"),
                    new ExtendedScreenHandlerType<>(TransumationStoneScreenHandler::new, BlockPos.PACKET_CODEC));


    public static void init(){}
}
