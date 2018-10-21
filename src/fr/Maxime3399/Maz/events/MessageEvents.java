package fr.Maxime3399.Maz.events;

import fr.Maxime3399.Maz.managers.CommandsManager;
import fr.Maxime3399.Maz.utils.LevelsUtils;
import fr.Maxime3399.Maz.utils.MySQLUtils;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

public class MessageEvents implements EventListener{
	
	@Override
	public void onEvent(Event event) {
		
		if(event instanceof MessageReceivedEvent) {
			
			MessageReceivedEvent e = (MessageReceivedEvent) event;
			
			if(!e.getAuthor().equals(e.getJDA().getSelfUser())) {
				
				String message = e.getMessage().getContentRaw();
				if(message.startsWith("//")) {
					
					message = message.replaceFirst("//", "");
					CommandsManager.executeCommand(e.getMessage(), message);
					
				}else {
					
					if(!message.startsWith("t!") && !message.startsWith(";;")) {
						
						MySQLUtils.setInt("maz_players", "id", e.getAuthor().getId(), "exp", MySQLUtils.getInt("maz_players", "id", e.getAuthor().getId(), "exp")+4);
						LevelsUtils.levelUP(e.getGuild().getMember(e.getAuthor()));
						
					}
					
				}
				
			}
			
		}
		
	}

}
