package fr.Maxime3399.Maz.commands;

import fr.Maxime3399.Maz.utils.MySQLUtils;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.Role;

public class RegisterCMD {
	
	public static void execute(Message message, String command) {
		
		Role role = message.getGuild().getRoles().get(0);
		if(message.getGuild().getMember(message.getAuthor()).getRoles().contains(role)) {
			
			String[] args = command.split(" ");
			
			if(args.length == 2) {
				
				if(message.getMentionedMembers().get(0) == null) {
					
					message.getChannel().sendMessage(message.getAuthor().getAsMention()+" | Commande invalide ! Aide : `//register [@user]`").complete();
					
				}else {
					
					Member member = message.getMentionedMembers().get(0);
					
					if(MySQLUtils.execute("INSERT INTO `maz_players` (`id`, `exp`, `level`, `money`, `birthday`) VALUES ('"+member.getUser().getId()+"', '0', '0', '0', 'none');", true)) {
						
						message.getChannel().sendMessage(message.getAuthor().getAsMention()+" | "+member.getAsMention()+" a été ajouté à la base de données !").complete();
						
					}else {
						
						message.getChannel().sendMessage(message.getAuthor().getAsMention()+" | Une erreur est survenue !").complete();
						
					}
					
				}
				
			}else {
				
				message.getChannel().sendMessage(message.getAuthor().getAsMention()+" | Commande invalide ! Aide : `//register [@user]`").complete();
				
			}
			
		}else {
			
			message.getChannel().sendMessage(message.getAuthor().getAsMention()+" | Vous n'avez pas la permission d'effectuer cette commande !").complete();
			
		}
		
	}

}
