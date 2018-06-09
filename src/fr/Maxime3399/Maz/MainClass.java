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
		
		System.out.println("[Maz] Connexion � la base de donn�es...");
		if(MySQLUtils.connect()) {
			
			System.out.println("[Maz] Connexion � la base de donn�es �tablie !");
			
			boolean error = false;
			System.out.println("[Maz] Connexion du bot au serveur...");
			
			try {
				
				jda = new JDABuilder(AccountType.BOT).setToken(MySQLUtils.getString("maz_info", "info_type", "secret_key", "info_string")).buildAsync();
				
			} catch (LoginException e) {
				
				e.printStackTrace();
				error = true;
				
			}
			
			if(error) {
				
				System.out.println("[Maz] La connexion au serveur discord a �chou�e !");
				
			}else {
				
				System.out.println("[Maz] Bot connect� au serveur discord !");
				EventsManager.registerEvents();
				
			}
			
		}else {
			
			System.out.println("[Maz] Connexion � la base de donn�e impossible.");
			
		}
		
	}
	
	public static JDA getJDA() {
		
		return jda;
		
	}
	
	public static void shutdown() {
		
		System.out.println("[Maz] Bot d�connect�.");
		jda.shutdown();
		System.exit(0);
		
	}

}
