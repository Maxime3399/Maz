package fr.Maxime3399.Maz.managers;

import fr.Maxime3399.Maz.commands.StopCMD;
import net.dv8tion.jda.core.entities.Message;

public class CommandsManager {

	public static void executeCommand(Message message, String command) {
		
		if(command.startsWith("stop")) {
			
			StopCMD.execute(message, command);
			
		}
		
	}
	
}
