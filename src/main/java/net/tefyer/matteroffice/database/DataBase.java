package net.tefyer.matteroffice.database;

import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;

public class DataBase {
    private Object2ObjectMap<String, Integer> VALUES = new Object2ObjectArrayMap<>();

    public boolean has(String name){
        if(!VALUES.containsKey(name))
            return false;
        return get(name) != 0;
    }
    public int get(String name){
        return VALUES.get(name);
    }
    public void add(String name, int matter_value){
        if(!name.contains(":"))
            name = "minecraft:"+name;
        VALUES.put(name,matter_value);
    }

    public boolean has(Item item){
        return has(Registries.ITEM.getId(item).toString());
    }
    public int get(Item item){
        return get(Registries.ITEM.getId(item).toString());
    }
    public void add(Item item, int matter_value){
        add(Registries.ITEM.getId(item).toString(), matter_value);
    }
}
