package fr.Maxime3399.Maz.commands;

import java.util.List;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageHistory;
import net.dv8tion.jda.core.entities.Role;

public class ClearCMD {
	
	public static void execute(Message message, String command) {
		
		Role role = message.getGuild().getRoles().get(0);
		Role role2 = message.getGuild().getRoles().get(1);
		if(message.getGuild().getMember(message.getAuthor()).getRoles().contains(role) || message.getGuild().getMember(message.getAuthor()).getRoles().contains(role2)) {
			
			MessageHistory history = new MessageHistory(message.getChannel());
			List<Message> msgs;
			try {
				while(history.size() > 0) {
					msgs = history.retrievePast(1).complete();
					msgs.get(0).delete().complete();
				}
			}catch (Exception e) {
				//none
			}
			
			/*new Timer().schedule(new TimerTask() {
				
				@Override
				public void run() {
					
					MessageHistory history = new MessageHistory(message.getChannel());
					
					if(history.size() == 0) {
						
						this.cancel();
						
					}else {
						
						history.getMessageById(message.getChannel().getLatestMessageIdLong()).delete().queue();
						
					}
					
				}
				
			}, 10);*/
			
		}else {
			
			message.getChannel().sendMessage(message.getAuthor().getAsMention()+" | Vous n'avez pas la permission d'effectuer cette commande !").complete();
			
		}
		
	}

}
