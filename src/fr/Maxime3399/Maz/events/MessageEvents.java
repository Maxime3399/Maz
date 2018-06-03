package fr.Maxime3399.Maz.events;

import fr.Maxime3399.Maz.managers.CommandsManager;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;
import net.dv8tion.jda.core.managers.GuildController;

public class MessageEvents implements EventListener{
	
	@Override
	public void onEvent(Event event) {
		
		if(event instanceof MessageReceivedEvent) {
			
			MessageReceivedEvent e = (MessageReceivedEvent) event;
			
			if(e.getMessage().getContentDisplay().equalsIgnoreCase("ping")) {
				
				e.getTextChannel().sendMessage("pong").complete();
				
			}
			
			if(!e.getAuthor().equals(e.getJDA().getSelfUser())) {
				
				String message = e.getMessage().getContentRaw();
				if(message.startsWith("//")) {
					
					message = message.replaceFirst("//", "");
					CommandsManager.executeCommand(e.getMessage(), message);
					
				}
				
			}
			
		}
		
	}

}
