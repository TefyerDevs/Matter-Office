package net.tefyer.matteroffice.data.payload;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

import static net.tefyer.matteroffice.MatterOffice.MOD_ID;

public record EMCPayload(int TotalEMC) implements CustomPayload {
    public static final Identifier EMC_VALUE_ID = Identifier.of(MOD_ID, "emc_value");
    public static final CustomPayload.Id<EMCPayload> ID = new CustomPayload.Id<>(EMC_VALUE_ID);
    public static final PacketCodec<PacketByteBuf, EMCPayload> CODEC = PacketCodec.tuple(
            PacketCodecs.INTEGER, EMCPayload::TotalEMC,
            EMCPayload::new);


    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
