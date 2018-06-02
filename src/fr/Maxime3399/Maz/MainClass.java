package fr.Maxime3399.Maz;

import javax.security.auth.login.LoginException;

import fr.Maxime3399.Maz.managers.EventsManager;
import fr.Maxime3399.Maz.utils.MySQLUtils;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

public class MainClass {
	
	private static JDA jda;
	
	public static void main(String[] args) {
		
		if(MySQLUtils.connect()) {
			
			System.out.println("[Maz] Connexion � la base de donn�e �tablie !");
			
			boolean error = false;
			System.out.println("[Maz] Connexion du bot au serveur...");
			
			try {
				
				jda = new JDABuilder(AccountType.BOT).setToken("NDUxNzY4MzUyMDk3MTczNTQ0.DfMXKA.5fYsUex1VkSTRQoNl4XRdXgkITY").buildAsync();
				
			} catch (LoginException e) {
				
				e.printStackTrace();
				error = true;
				
			}
			
			if(error) {
				
				System.out.println("[Maz] La connexion a �chou�e !");
				
			}else {
				
				System.out.println("[Maz] Bot connect� au serveur discord !");
				EventsManager.registerEvents(jda);
				
			}
			
		}else {
			
			System.out.println("[Maz] Connexion � la base de donn�e impossible.");
			
		}
		
	}
	
	public static void shutdown() {
		
		jda.shutdown();
		
	}

}
