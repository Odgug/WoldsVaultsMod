package xyz.iwolfking.woldsvaults.custom.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TextComponent;
import xyz.iwolfking.woldsvaults.custom.commands.gui.ClaimBlockCategoryGui;

public class ClaimPurchaseCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("claimshop").executes(ClaimPurchaseCommand::openClaimShop));
    }

    private static int openClaimShop(CommandContext<CommandSourceStack> commandSourceStackCommandContext) throws CommandSyntaxException {
        ClaimBlockCategoryGui gui = new ClaimBlockCategoryGui(commandSourceStackCommandContext.getSource().getPlayerOrException());
        gui.updateDisplay();
        gui.setTitle(new TextComponent("Claim Shop"));
        gui.open();
        return 0;
    }
}
