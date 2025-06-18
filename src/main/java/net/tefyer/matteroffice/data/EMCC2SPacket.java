package net.tefyer.matteroffice.data;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;
import net.tefyer.matteroffice.data.emc.EMCData;
import net.tefyer.matteroffice.data.emc.EMCPayload;
import net.tefyer.matteroffice.data.inter.IEntityDataSaver;

public class EMCC2SPacket {

    public static void payload(CustomPayload cpayload, ClientPlayNetworking.Context context) {
        if(cpayload instanceof EMCPayload payload && context.player().getServer().getPlayerManager()
                .getPlayer(context.player().getUuid()) instanceof ServerPlayerEntity serverPlayerEntity){
            EMCData.addEmc((IEntityDataSaver) serverPlayerEntity, payload.Emc());
        }
    }
}
