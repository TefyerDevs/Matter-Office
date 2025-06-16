package net.tefyer.matteroffice.utils;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.tefyer.matteroffice.MatterOffice;
import net.tefyer.matteroffice.config.EMCConfig;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import static net.tefyer.matteroffice.config.EMCConfig.CONFIG_DIR;

public class FileUtils {
    public static void createConfig(){
        if(CONFIG_DIR.toFile().mkdir()){
            MatterOffice.LOGGER.debug("Could create the config folder.");
        }else{
            MatterOffice.LOGGER.error("`Couldn't create the config folder.");
        }
    }
    public static void createNormalConfig(){
        if(FabricLoader.getInstance().getConfigDir().toFile().mkdir()){
            MatterOffice.LOGGER.debug("Could create the config folder.");
        }else{
            MatterOffice.LOGGER.error("`Couldn't create the config folder.");
        }
    }

    public static void createFile(Path path, String content){
        try {
            FileWriter myWriter = new FileWriter(path.toFile());
            myWriter.write(content);
            myWriter.close();
        } catch (IOException e) {
            MatterOffice.LOGGER.error("Failed to create file: " + path.getFileName());
            e.printStackTrace();
        }
    }

    public static String readFile(Path emcFile) throws IOException {
        return new String(Files.readAllBytes(emcFile));
    }

    public static void addEMC(Item item, int emc) {
        try{
            JSONObject main = new JSONObject(readFile(EMCConfig.EMC_FILE));
            JSONObject entries = main.getJSONObject("entries");
            entries.put(getName(item), emc);
            main.put("entries", entries);

            write(EMCConfig.EMC_FILE,main);
        } catch (IOException e) {
            MatterOffice.LOGGER.error("Failed to add to file: " + EMCConfig.EMC_FILE.getFileName());
            e.printStackTrace();
        }
    }

    private static void write(Path path, JSONObject object) {

        try {
            FileWriter myWriter = new FileWriter(path.toFile());
            myWriter.write(object.toString(4));
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static String getName(Item item) {
        String name = Registries.ITEM.getId(item).toString();
        if(name.contains("minecraft:"))
            name.replace("minecraft:", "");
        return name;
    }
}
