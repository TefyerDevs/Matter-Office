package net.tefyer.matteroffice;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.tefyer.matteroffice.command.SetEMCValue;
import net.tefyer.matteroffice.config.EMCConfig;
import net.tefyer.matteroffice.database.DataBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.Normalizer;

import static net.tefyer.matteroffice.utils.TextUtils.emcText;
import static net.tefyer.matteroffice.utils.TextUtils.stackedEmcText;


public class MatterOffice implements ModInitializer {
    public static final String MOD_ID = "matter_office";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static DataBase database = new DataBase();

    @Override
    public void onInitialize() {
        EMCConfig.init();

        registerCommands();

        ItemTooltipCallback.EVENT.register((itemStack, tooltipContext, tooltipType, list) -> {
            if(database.has(itemStack.getItem())){
                list.add(emcText(itemStack, database));
                if(itemStack.getCount() > 1){
                    list.add(stackedEmcText(itemStack, database));
                }
            }
        });
    }

    private static void registerCommands(){
        CommandRegistrationCallback.EVENT.register(SetEMCValue::register);
    }
}
