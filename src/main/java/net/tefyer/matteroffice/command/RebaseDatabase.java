package net.tefyer.matteroffice.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.ItemStackArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.tefyer.matteroffice.MatterOffice;
import net.tefyer.matteroffice.config.EMCConfig;

public class RebaseDatabase {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher,
                                CommandRegistryAccess commandRegistryAccess,
                                CommandManager.RegistrationEnvironment registrationEnvironment) {
            dispatcher.register(CommandManager.literal("rebaseDatabase")
                    .requires(source -> source.hasPermissionLevel(2))
                    .executes(context -> execute(
                    context.getSource())));
        }

    private static int execute(ServerCommandSource source) {
        MatterOffice.database.reset();
        EMCConfig.base();
        return 1;
    }
}
