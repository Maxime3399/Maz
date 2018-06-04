package fr.Maxime3399.Maz.events;

import java.util.Timer;
import java.util.TimerTask;

import fr.Maxime3399.Maz.utils.LevelsUtils;
import fr.Maxime3399.Maz.utils.MySQLUtils;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.core.hooks.EventListener;

public class VoiceChannelEvents implements EventListener{

	@Override
	public void onEvent(Event event) {
		
		if(event instanceof GuildVoiceJoinEvent) {
			
			GuildVoiceJoinEvent e = (GuildVoiceJoinEvent) event;
			Member member = e.getMember();
			go(member, e.getChannelJoined());
			
		}
		
		if(event instanceof GuildVoiceMoveEvent) {
			
			GuildVoiceMoveEvent e = (GuildVoiceMoveEvent) event;
			Member member = e.getMember();
			go(member, e.getChannelJoined());
			
		}
		
	}
	
	private static void go(Member member, VoiceChannel vc) {
		
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				
				if(vc.getMembers().size() >= 2 && !vc.equals(member.getGuild().getVoiceChannelsByName("AFK", true).get(0))) {
					
					if(vc.getMembers().contains(member)) {
						MySQLUtils.setInt("maz_players", "id", member.getUser().getId(), "exp", MySQLUtils.getInt("maz_players", "id", member.getUser().getId(), "exp")+14);
						LevelsUtils.levelUP(member);
						go(member, vc);
					}
					
				}
				
			}
			
		}, 60000);
		
	}

}
