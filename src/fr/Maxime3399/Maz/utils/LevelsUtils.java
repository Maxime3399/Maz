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
		
		if(level != 500) {
			
			if(exp >= getRequiredExp(level)) {
				
				exp = exp-getRequiredExp(level);
				level++;
				
			}
			
			MySQLUtils.setInt("maz_players", "id", member.getUser().getId(), "exp", exp);
			MySQLUtils.setInt("maz_players", "id", member.getUser().getId(), "level", level);
			
			if(level >= 401) {
				level = level-400;
			}else if(level >= 301) {
				level = level-300;
			}else if(level >= 201) {
				level = level-200;
			}else if(level >= 101) {
				level = level-100;
			}
			
			int anc = level-1;
			GuildController gc = member.getGuild().getController();
			Role rAncient = member.getGuild().getRolesByName("Niveau "+anc, true).get(0);
			gc.removeSingleRoleFromMember(member, rAncient);
			Role rNew = member.getGuild().getRolesByName("Niveau "+level, true).get(0);
			gc.addSingleRoleToMember(member, rNew);
			
		}
		
	}

}
