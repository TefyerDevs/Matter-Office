package net.tefyer.matteroffice.data;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;
import net.tefyer.matteroffice.MatterOffice;
import net.tefyer.matteroffice.data.emc.EMCPayload;

public class ModMessages {

    public static final Identifier EMC_SYNC_ID = MatterOffice.id("emc_sync");

    public static void registerPackets(){
        PayloadTypeRegistry.playS2C().register(EMCPayload.ID, EMCPayload.CODEC);

    }

    public static void registerC2SPacket(){
        ClientPlayNetworking.registerGlobalReceiver(EMCPayload.ID, EMCC2SPacket::payload);
    }

    public static void registerS2CPacket(){

    }
}
