package net.tefyer.matteroffice.data.emc;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.tefyer.matteroffice.utils.DataUtils;

import static net.tefyer.matteroffice.MatterOffice.MOD_ID;

public record EMCPayload(Integer Emc) implements CustomPayload {
    public static final CustomPayload.Id<EMCPayload> ID = new CustomPayload.Id<>(DataUtils.EMC_ID);
    public static final PacketCodec<PacketByteBuf, EMCPayload> CODEC = PacketCodec.tuple(
            PacketCodecs.INTEGER, EMCPayload::Emc,
            EMCPayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
