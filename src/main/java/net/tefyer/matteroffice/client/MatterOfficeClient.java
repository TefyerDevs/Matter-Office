package net.tefyer.matteroffice.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;
import net.tefyer.matteroffice.client.menus.MenuRegistry;
import net.tefyer.matteroffice.client.screen.TransumationStoneScreen;
import net.tefyer.matteroffice.client.screen.hud.EMCHudOverlay;
import net.tefyer.matteroffice.data.payload.EMCPayload;

public class MatterOfficeClient implements ClientModInitializer {
    private static void handleDirtBrokenPayload(EMCPayload payload, ClientPlayNetworking.Context context) {
        ClientPlayerEntity player = context.client().player;
    }
    @Override
    public void onInitializeClient() {
        HandledScreens.register(MenuRegistry.TRANSUMATION_STONE_HANDLER, TransumationStoneScreen::new);
        ClientPlayNetworking.registerGlobalReceiver(EMCPayload.ID, MatterOfficeClient::handleDirtBrokenPayload);

        HudRenderCallback.EVENT.register(new EMCHudOverlay());
    }
}
