package fr.Maxime3399.Maz.commands;

import fr.Maxime3399.Maz.utils.LevelsUtils;
import fr.Maxime3399.Maz.utils.MySQLUtils;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;

public class ProfileCMD {
	
	public static void execute(Message message, String command) {
		
		String args[] = command.split(" ");
		
		if(args.length == 1) {
			
			String id = message.getAuthor().getId();
			int exp = MySQLUtils.getInt("maz_players", "id", message.getAuthor().getId(), "exp");
			int level = MySQLUtils.getInt("maz_players", "id", message.getAuthor().getId(), "level");
			int money = MySQLUtils.getInt("maz_players", "id", message.getAuthor().getId(), "money");
			String birthday = MySQLUtils.getString("maz_players", "id", message.getAuthor().getId(), "birthday");
			
			if(birthday.equals("none")) {
				birthday = "*Non définit*";
			}else {
				birthday = "`"+birthday+"`";
			}
			
			message.getChannel().sendMessage(" __Profile de "+message.getAuthor().getAsMention()+"__\n \n**Informations :**\nNom : `"+message.getAuthor().getName()+"`\nID : `"+id+"`\nAnniversaire : "+birthday+"\n \n**Compte :**\nCrédits : `"+money+"`\nXP : `"+exp+"/"+LevelsUtils.getRequiredExp(level)+"`\nNiveau : `"+level+"`").complete();
			
		}else if(args.length == 2) {
			
			if(message.getMentionedMembers().get(0) == null) {
				
				message.getChannel().sendMessage(message.getAuthor().getAsMention()+" | Commande invalide ! Aide : `//profile {@user}`").complete();
				
			}else {
				
				User user = message.getMentionedMembers().get(0).getUser();
				
				String id = user.getId();
				int exp = MySQLUtils.getInt("maz_players", "id", user.getId(), "exp");
				int level = MySQLUtils.getInt("maz_players", "id", user.getId(), "level");
				int money = MySQLUtils.getInt("maz_players", "id", user.getId(), "money");
				String birthday = MySQLUtils.getString("maz_players", "id", user.getId(), "birthday");
				
				if(birthday.equals("none")) {
					birthday = "*Non définit*";
				}else {
					birthday = "`"+birthday+"`";
				}
				
				message.getChannel().sendMessage(" __Profile de "+user.getAsMention()+"__\n \n**Informations :**\nNom : `"+user.getName()+"`\nID : `"+id+"`\nAnniversaire : "+birthday+"\n \n**Compte :**\nCrédits : `"+money+"`\nXP : `"+exp+"/"+LevelsUtils.getRequiredExp(level)+"`\nNiveau : `"+level+"`").complete();
				
			}
			
		}else {
			
			message.getChannel().sendMessage(message.getAuthor().getAsMention()+" | Commande invalide ! Aide : `//profile {@user}`").complete();
			
		}
		
	}

}
