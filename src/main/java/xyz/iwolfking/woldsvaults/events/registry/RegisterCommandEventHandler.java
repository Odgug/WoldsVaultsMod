package xyz.iwolfking.woldsvaults.events.registry;

import net.minecraftforge.event.RegisterCommandsEvent;
import xyz.iwolfking.woldsvaults.custom.commands.ClaimPurchaseCommand;

public class RegisterCommandEventHandler {
    public static void woldsvaults_registerCommandsEvent(RegisterCommandsEvent event) {
        //PlayerResearchCommand.register(event.getDispatcher());
        ClaimPurchaseCommand.register(event.getDispatcher());
    }
}