package fr.Maxime3399.Maz.managers;

import fr.Maxime3399.Maz.MainClass;
import fr.Maxime3399.Maz.events.JoinEvents;
import fr.Maxime3399.Maz.events.MessageEvents;
import fr.Maxime3399.Maz.events.VoiceChannelEvents;
import net.dv8tion.jda.core.JDA;

public class EventsManager {
	
	public static void registerEvents() {
		
		JDA jda = MainClass.getJDA();
		
		jda.addEventListener(new MessageEvents());
		jda.addEventListener(new JoinEvents());
		jda.addEventListener(new VoiceChannelEvents());
		
	}

}
