package fr.Maxime3399.Maz.commands;

import fr.Maxime3399.Maz.MainClass;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.Role;

public class StopCMD {
	
	public static void execute(Message message, String command) {
		
		Role role = message.getGuild().getRoles().get(0);
		if(message.getGuild().getMember(message.getAuthor()).getRoles().contains(role)) {
			
			message.getChannel().sendMessage(message.getAuthor().getAsMention()+" | Déconnection du bot.").complete();
			MainClass.shutdown();
			
		}else {
			
			message.getChannel().sendMessage(message.getAuthor().getAsMention()+" | Vous n'avez pas la permission d'effectuer cette commande !").complete();
			
		}
		
	}

}
