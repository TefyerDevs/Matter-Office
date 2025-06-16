package net.tefyer.matteroffice.config;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Items;
import net.tefyer.matteroffice.MatterOffice;
import net.tefyer.matteroffice.database.DataBase;
import net.tefyer.matteroffice.utils.FileUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.logging.Logger;

import static net.tefyer.matteroffice.utils.FileUtils.createFile;

public class EMCConfig {
    public static final Path CONFIG_DIR = FabricLoader.getInstance().getConfigDir().resolve(MatterOffice.MOD_ID);

    public static final Path EMC_FILE = EMCConfig.CONFIG_DIR.resolve("emc_values.json");

    public static void base(){
        try{

            JSONObject object = new JSONObject(FileUtils.readFile(EMC_FILE));

            MatterOffice.LOGGER.debug(FileUtils.readFile(EMC_FILE));

            JSONObject entries = object.getJSONObject("entries");

            Iterator<String> keys = entries.keys();

            while (keys.hasNext()) {
                String itemID = keys.next();
                int emcValue = entries.getInt(itemID);
                MatterOffice.database.add(itemID, emcValue);
                System.out.println("READED ITEM: "+ itemID+" WITH EMC VALUE AS: "+ emcValue);
            }

        } catch (IOException e) {
            MatterOffice.LOGGER.error("coudnt read emc file.");
            throw new RuntimeException(e);
        }
    }

    public static void init() {

        if(!(FabricLoader.getInstance().getConfigDir().toFile().exists() && FabricLoader.getInstance().getConfigDir().toFile().isDirectory())){
            FileUtils.createNormalConfig();
        }

        if(!(CONFIG_DIR.toFile().exists() && CONFIG_DIR.toFile().isDirectory())){
            FileUtils.createConfig();
        }

        if(!(EMC_FILE.toFile().exists() && EMC_FILE.toFile().isFile())){
            createFile(EMC_FILE,"{\n" +
                    "  \"comment\": \"Use the in-game commands to edit this file\",\n" +
                    "  \"entries\": {}\n" +
                    "}");
        }

        base();
    }
}
