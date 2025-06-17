package net.tefyer.matteroffice.client.screen.hud;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.Formatting;
import net.tefyer.matteroffice.data.payload.EMCPayload;

import java.util.concurrent.atomic.AtomicInteger;

public class EMCHudOverlay implements HudRenderCallback {
    @Override
    public void onHudRender(DrawContext drawContext, RenderTickCounter tickCounter) {
        int x =0, y=0;
        MinecraftClient mcClient = MinecraftClient.getInstance();
        if(mcClient != null){
            int width =mcClient.getWindow().getScaledWidth();
            int height =mcClient.getWindow().getScaledHeight();

            x = width/2;
            y = height;
        }
        AtomicInteger emc = new AtomicInteger(0);
        ClientPlayNetworking.registerGlobalReceiver(EMCPayload.ID,(payload, context)->{
            emc.set(payload.TotalEMC());
        });


        drawContext.drawText(mcClient.textRenderer,"EMC: "+emc.get(),5,5, Formatting.WHITE.getColorValue(),
                true);
    }
}
