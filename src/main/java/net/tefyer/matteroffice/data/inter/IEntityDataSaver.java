package net.tefyer.matteroffice.data.inter;

import net.minecraft.component.type.NbtComponent;
import net.minecraft.nbt.NbtCompound;

public interface IEntityDataSaver {
    NbtCompound getPersistentData();
}