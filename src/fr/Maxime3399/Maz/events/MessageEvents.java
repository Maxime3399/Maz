package fr.Maxime3399.Maz.events;

import fr.Maxime3399.Maz.MainClass;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

public class MessageEvents implements EventListener{

	@Override
	public void onEvent(Event event) {
		
		if(event instanceof MessageReceivedEvent) {
			
			MessageReceivedEvent e = (MessageReceivedEvent) event;
			
			if(e.getMessage().getContentDisplay().equalsIgnoreCase("ping")) {
				
				e.getTextChannel().sendMessage("pong").complete();
				
			}else if(e.getMessage().getContentDisplay().equalsIgnoreCase("//stop")) {
				
				MainClass.shutdown();
				
			}
			
		}
		
	}

}
