package fr.Maxime3399.Maz.events;

import fr.Maxime3399.Maz.utils.MySQLUtils;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.hooks.EventListener;
import net.dv8tion.jda.core.managers.GuildController;

public class JoinEvents implements EventListener{

	@Override
	public void onEvent(Event event) {
		
		if(event instanceof GuildMemberJoinEvent) {
			
			GuildMemberJoinEvent e = (GuildMemberJoinEvent) event;
			Member member = e.getMember();
			
			MySQLUtils.execute("INSERT INTO `maz_players` (`id`, `exp`, `level`, `money`, `birthday`) VALUES ('"+member.getUser().getId()+"', '0', '0', '0', 'none');", true);
			
			GuildController gc = member.getGuild().getController();
			Role r = member.getGuild().getRolesByName("Membre", true).get(0);
			gc.addSingleRoleToMember(member, r);
			
		}
		
	}
	
}
