package fr.Maxime3399.Maz.commands;

import fr.Maxime3399.Maz.utils.LevelsUtils;
import fr.Maxime3399.Maz.utils.MySQLUtils;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.Role;

public class XpCMD {
	
	public static void execute(Message message, String command) {
		
		Role role = message.getGuild().getRoles().get(0);
		Role role2 = message.getGuild().getRoles().get(1);
		if(message.getGuild().getMember(message.getAuthor()).getRoles().contains(role) || message.getGuild().getMember(message.getAuthor()).getRoles().contains(role2)) {
			
			String args[] = command.split(" ");
			
			if(args.length == 3) {
				
				boolean error = false;
				int exp = 0;
				try {
					exp = Integer.parseInt(args[2]);
				}catch (Exception e) {
					error = true;
				}
				
				if(error) {
					
					message.getChannel().sendMessage(message.getAuthor().getAsMention()+" | Commande invalide ! Aide : `//xp [@user] [x]`").complete();
					
				}else {
					
					Member member = message.getMentionedMembers().get(0);
					MySQLUtils.setInt("maz_players", "id", member.getUser().getId(), "exp", MySQLUtils.getInt("maz_players", "id", member.getUser().getId(), "exp")+exp);
					LevelsUtils.levelUP(member);
					
					message.getChannel().sendMessage(message.getAuthor().getAsMention()+" | L'XP de "+member.getAsMention()+" a été modifié !").complete();
					
				}
				
			}else {
				
				message.getChannel().sendMessage(message.getAuthor().getAsMention()+" | Commande invalide ! Aide : `//xp [@user] [x]`").complete();
				
			}
			
		}else {
			
			message.getChannel().sendMessage(message.getAuthor().getAsMention()+" | Vous n'avez pas la permission d'effectuer cette commande !").complete();
			
		}
		
	}

}
