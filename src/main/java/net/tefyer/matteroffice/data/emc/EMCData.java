package net.tefyer.matteroffice.data.emc;

import net.minecraft.nbt.NbtCompound;
import net.tefyer.matteroffice.data.inter.IEntityDataSaver;
import net.tefyer.matteroffice.utils.DataUtils;

public class EMCData {
    public static int addEmc(IEntityDataSaver player, int amount){
        NbtCompound nbt = player.getPersistentData();
        int emc = nbt.getInt(DataUtils.EMC);
        emc+=amount;

        nbt.putInt(DataUtils.EMC, emc);

        return emc;
    }
    public static int removeEmc(IEntityDataSaver player, int amount){
        NbtCompound nbt = player.getPersistentData();
        int emc = nbt.getInt(DataUtils.EMC);
        emc-=amount;

        nbt.putInt(DataUtils.EMC, emc);

        return emc;
    }
}
