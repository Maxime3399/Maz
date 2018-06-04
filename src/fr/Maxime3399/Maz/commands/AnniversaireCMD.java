package fr.Maxime3399.Maz.commands;

import fr.Maxime3399.Maz.utils.MySQLUtils;
import net.dv8tion.jda.core.entities.Message;

public class AnniversaireCMD {
	
	public static void execute(Message message, String command) {
		
		String args[] = command.split(" ");
		
		if(!MySQLUtils.getString("maz_players", "id", message.getAuthor().getId(), "birthday").equalsIgnoreCase("none")) {
			
			message.getChannel().sendMessage(message.getAuthor().getAsMention()+" | Vous avez déjà définit votre date d'anniversaire !").complete();
			
		}else {
			
			if(args.length == 2) {
				
				if(args[1].contains("/")) {
					
					String i[] = args[1].split("/");
					int day = 0;
					int month = 0;
					boolean error = false;
					try {
						day = Integer.parseInt(i[0]);
					}catch(Exception e) {
						error = true;
					}
					try {
						month = Integer.parseInt(i[1]);
					}catch(Exception e) {
						error = true;
					}
					
					if(day > 0 && day <= 31) {
						//none
					}else {
						error = true;
					}
					if(month > 0 && month <= 12) {
						//none
					}else {
						error = true;
					}
					
					if(error) {
						
						message.getChannel().sendMessage(message.getAuthor().getAsMention()+" | Commande invalide ! Aide : `//anniversaire [dd/mm]`").complete();
						
					}else {
						
						MySQLUtils.setString("maz_players", "id", message.getAuthor().getId(), "birthday", day+"/"+month);
						message.getChannel().sendMessage(message.getAuthor().getAsMention()+" | Date d'anniversaire définie !").complete();
						
					}
					
				}else {
					
					message.getChannel().sendMessage(message.getAuthor().getAsMention()+" | Commande invalide ! Aide : `//anniversaire [dd/mm]`").complete();
					
				}
				
			}else {
				
				message.getChannel().sendMessage(message.getAuthor().getAsMention()+" | Commande invalide ! Aide : `//anniversaire [dd/mm]`").complete();
				
			}
			
		}
		
	}

}
