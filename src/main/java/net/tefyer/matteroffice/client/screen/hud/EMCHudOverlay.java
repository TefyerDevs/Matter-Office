package net.tefyer.matteroffice.client.screen.hud;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Formatting;
import net.tefyer.matteroffice.data.emc.EMCData;
import net.tefyer.matteroffice.data.emc.EMCPayload;
import net.tefyer.matteroffice.data.inter.IEntityDataSaver;
import net.tefyer.matteroffice.utils.DataUtils;

import java.util.concurrent.atomic.AtomicInteger;

public class EMCHudOverlay implements HudRenderCallback {
    @Override
    public void onHudRender(DrawContext drawContext, RenderTickCounter tickCounter) {
        int x =3, y=3;
        MinecraftClient mcClient = MinecraftClient.getInstance();
        if(mcClient != null){
            int width =mcClient.getWindow().getScaledWidth();
            int height =mcClient.getWindow().getScaledHeight();

            x = width/2;
            y = height;
        }

        int emc = 0;
        IEntityDataSaver player = ((IEntityDataSaver) MinecraftClient.getInstance().player);
        if(player.getPersistentData().contains(DataUtils.EMC)){
            emc = player.getPersistentData().getInt(DataUtils.EMC);
        }


        drawContext.drawText(mcClient.textRenderer,"EMC: "+emc,x,y, Formatting.WHITE.getColorValue(),
                true);
    }
}
