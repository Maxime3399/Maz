package fr.Maxime3399.Maz.utils;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.managers.GuildController;

public class LevelsUtils {
	
	public static int getRequiredExp(int level) {
		
		int result = 0;
		
		if(level >= 401) {
			
			result = 100+100*level;
			
		}if(level >= 301) {
			
			result = 100+85*level;
			
		}else if(level >= 201) {
			
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
		int Rl = 0;
		
		if(!member.getUser().getName().equalsIgnoreCase("Tatsumaki") && !member.getUser().getName().equalsIgnoreCase("FredBoat♪♪")) {
			
			if(level != 500) {
				
				if(exp >= getRequiredExp(level)) {
					
					exp = exp-getRequiredExp(level);
					level = level+1;
					Rl = level;
					
					MySQLUtils.setInt("maz_players", "id", member.getUser().getId(), "exp", exp);
					MySQLUtils.setInt("maz_players", "id", member.getUser().getId(), "level", level);
					
					member.getGuild().getTextChannelsByName("🔋bot", true).get(0).sendMessage(member.getAsMention()+" est monté au niveau "+level+" !").complete();
					GuildController gc = member.getGuild().getController();
					
					if(level == 401 || level == 301 || level == 201 || level == 101) {
						
						Role rAncient = member.getGuild().getRolesByName("👑Niveau "+100, true).get(0);
						gc.removeSingleRoleFromMember(member, rAncient).complete();
						
					}
					
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
					if(anc != 0) {
						Role rAncient = null;
						if(anc == 100) {
							rAncient = member.getGuild().getRolesByName("👑Niveau👑 "+anc, true).get(0);
						}else if(anc >= 97) {
							rAncient = member.getGuild().getRolesByName("👑Niveau "+anc, true).get(0);
						}else if(anc >= 90) {
							rAncient = member.getGuild().getRolesByName("⚡Niveau "+anc, true).get(0);
						}else {
							rAncient = member.getGuild().getRolesByName("Niveau "+anc, true).get(0);
						}
						gc.removeSingleRoleFromMember(member, rAncient).complete();
					}
					Role rNew = null;
					if(level == 100) {
						rNew = member.getGuild().getRolesByName("👑Niveau👑 "+level, true).get(0);
					}else if(level >= 97) {
						rNew = member.getGuild().getRolesByName("👑Niveau "+level, true).get(0);
					}else if(level >= 90) {
						rNew = member.getGuild().getRolesByName("⚡Niveau "+level, true).get(0);
					}else {
						rNew = member.getGuild().getRolesByName("Niveau "+level, true).get(0);
					}
					gc.addSingleRoleToMember(member, rNew).complete();
					
					if(Rl == 50) {
						Role rS = member.getGuild().getRolesByName("🔰Actif", true).get(0);
						gc.addSingleRoleToMember(member, rS);
					}else if(Rl == 100) {
						Role rS = member.getGuild().getRolesByName("🔰Habitué🔰", true).get(0);
						gc.addSingleRoleToMember(member, rS);
					}else if(Rl == 150) {
						Role rS = member.getGuild().getRolesByName("⭐Ammateur", true).get(0);
						gc.addSingleRoleToMember(member, rS);
					}else if(Rl == 200) {
						Role rS = member.getGuild().getRolesByName("⭐Accro⭐", true).get(0);
						gc.addSingleRoleToMember(member, rS);
					}else if(Rl == 250) {
						Role rS = member.getGuild().getRolesByName("💮Adicte", true).get(0);
						gc.addSingleRoleToMember(member, rS);
					}else if(Rl == 300) {
						Role rS = member.getGuild().getRolesByName("💮Doyen💮", true).get(0);
						gc.addSingleRoleToMember(member, rS);
					}else if(Rl == 350) {
						Role rS = member.getGuild().getRolesByName("🔥Ancêtre", true).get(0);
						gc.addSingleRoleToMember(member, rS);
					}else if(Rl == 400) {
						Role rS = member.getGuild().getRolesByName("🔥Légende🔥", true).get(0);
						gc.addSingleRoleToMember(member, rS);
					}
					
					levelUP(member);
					
				}
				
			}
			
		}
		
	}

}
