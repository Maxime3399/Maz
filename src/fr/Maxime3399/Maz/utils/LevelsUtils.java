package fr.Maxime3399.Maz.utils;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.managers.GuildController;

public class LevelsUtils {
	
	public static int getRequiredExp(int level) {
		
		int result = 0;
		
		if(level >= 201) {
			
			result = 100+65*level;
			
		}else if(level >= 101) {
			
			result = 100+45*level;
			
		}else if(level < 100) {
			
			result = 100+25*level;
			
		}
		
		return result;
		
	}
	
	public static void levelUP(Member member) {
		
		int exp = MySQLUtils.getInt("maz_players", "id", member.getUser().getId(), "exp");
		int level = MySQLUtils.getInt("maz_players", "id", member.getUser().getId(), "level");
		
		if(exp >= getRequiredExp(level)) {
			
			exp = exp-getRequiredExp(level);
			level++;
			
		}
		
		MySQLUtils.setInt("maz_players", "id", member.getUser().getId(), "exp", exp);
		MySQLUtils.setInt("maz_players", "id", member.getUser().getId(), "level", level);
		
		int anc = level-1;
		GuildController gc = member.getGuild().getController();
		Role rAncient = member.getGuild().getRolesByName("Niveau "+anc, true).get(0);
		gc.removeSingleRoleFromMember(member, rAncient);
		Role rNew = member.getGuild().getRolesByName("Niveau "+level, true).get(0);
		gc.addSingleRoleToMember(member, rNew);
		
	}

}
