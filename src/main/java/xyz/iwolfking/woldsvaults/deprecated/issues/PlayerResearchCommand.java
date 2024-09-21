package xyz.iwolfking.woldsvaults.deprecated.issues;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.MessageArgument;
import net.minecraft.network.chat.TextComponent;
import net.minecraftforge.common.UsernameCache;
import xyz.iwolfking.woldsvaults.api.helpers.util.MessageFunctions;

import java.util.UUID;

@Deprecated
public class PlayerResearchCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("researchs").then(Commands.argument("player", MessageArgument.message()).executes(AddCommand -> openResearchGUI(AddCommand.getSource(),
                MessageArgument.getMessage(AddCommand, "player").getString()
        ))));;
    }

    private static int openResearchGUI(CommandSourceStack ctx, String target) throws CommandSyntaxException {
        System.out.println("running research command");
        if (UsernameCache.getMap().containsValue(target)) {
            System.out.println("Username cache populated, found match.");
            if (ctx.getServer().getProfileCache().get(target).isPresent()) {
                UUID uuid = ctx.getServer().getProfileCache().get(target).get().getId();
                System.out.println("uuid: " + uuid);
                System.out.println(target);
                PlayerResearchesGUI gui = new PlayerResearchesGUI(ctx.getPlayerOrException(), uuid);
                gui.updateDisplay();
                gui.setTitle(new TextComponent(target + "'s Researches"));
                gui.open();
                return 0;

            }
        } else {
            System.out.println("Failed to find username in cache.");
            MessageFunctions.sendMessage(ctx.getPlayerOrException(), new TextComponent("Could not find a player with that username."));
            return 0;
        }
        return 0;
    }
}
