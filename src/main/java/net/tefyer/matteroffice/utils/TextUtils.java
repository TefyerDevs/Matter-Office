package net.tefyer.matteroffice.utils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.tefyer.matteroffice.database.DataBase;

import java.text.DecimalFormat;

public class TextUtils {
    public static final DecimalFormat formatter = new DecimalFormat("###,###");

    public static final Text twoColourString(String oneString, String twoString, Formatting oneColour, Formatting twoColour){
        return Text.literal(oneString).formatted(oneColour).append(Text.literal(twoString).formatted(twoColour));
    }



    public static final Text emcText(ItemStack item, DataBase dataBase){
        return twoColourString("EMC: ", formatter.format(dataBase.get(item.getItem())), Formatting.YELLOW, Formatting.WHITE);
    }

    public static final Text stackedEmcText(ItemStack item, DataBase dataBase){
        return twoColourString("STACKED EMC: ", formatter.format((long) item.getCount() *dataBase.get(item.getItem())), Formatting.YELLOW, Formatting.WHITE);
    }
}
