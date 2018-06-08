package fr.Maxime3399.Maz.managers;

import fr.Maxime3399.Maz.commands.AnniversaireCMD;
import fr.Maxime3399.Maz.commands.ClearCMD;
import fr.Maxime3399.Maz.commands.CreditsCMD;
import fr.Maxime3399.Maz.commands.ProfileCMD;
import fr.Maxime3399.Maz.commands.RegisterCMD;
import fr.Maxime3399.Maz.commands.StopCMD;
import fr.Maxime3399.Maz.commands.XpCMD;
import net.dv8tion.jda.core.entities.Message;

public class CommandsManager {

	public static void executeCommand(Message message, String command) {
		
		if(command.startsWith("stop")) {
			
			StopCMD.execute(message, command);
			
		}else if(command.startsWith("register")) {
			
			RegisterCMD.execute(message, command);
			
		}else if(command.startsWith("profile")) {
			
			ProfileCMD.execute(message, command);
			
		}else if(command.startsWith("clear")) {
			
			ClearCMD.execute(message, command);
			
		}else if(command.startsWith("anniversaire")) {
			
			AnniversaireCMD.execute(message, command);
			
		}else if(command.startsWith("xp")) {
			
			XpCMD.execute(message, command);
			
		}else if(command.startsWith("credits")) {
			
			CreditsCMD.execute(message, command);
			
		}
		
	}
	
}
