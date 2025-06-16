package net.tefyer.matteroffice.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.command.argument.ItemStackArgument;
import net.minecraft.command.argument.ItemStackArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.tefyer.matteroffice.MatterOffice;
import net.tefyer.matteroffice.utils.FileUtils;

import java.util.Collection;

public class SetEMCValue {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher,
                                CommandRegistryAccess commandRegistryAccess,
                                CommandManager.RegistrationEnvironment registrationEnvironment) {

        dispatcher.register(CommandManager.literal("setEmc").requires((source) -> source.hasPermissionLevel(2))
                .then(CommandManager.argument("item",
                        ItemStackArgumentType.itemStack(commandRegistryAccess)).then(CommandManager.argument("emc", IntegerArgumentType.integer(0))
                        .executes(context -> execute(
                        context.getSource(),
                        ItemStackArgumentType.getItemStackArgument(context, "item"),
                        IntegerArgumentType.getInteger(context, "emc")
                )))));
    }

    private static int execute(ServerCommandSource source, ItemStackArgument item, int emc)
            throws CommandSyntaxException {
        MatterOffice.database.add(item.getItem(), emc);
        FileUtils.addEMC(item.getItem(), emc);
        return 1;
    }


}
