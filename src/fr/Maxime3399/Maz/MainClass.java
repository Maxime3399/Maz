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
		
		System.out.println("[Maz] Connexion à la base de données...");
		if(MySQLUtils.connect()) {
			
			System.out.println("[Maz] Connexion à la base de données établie !");
			
			boolean error = false;
			System.out.println("[Maz] Connexion du bot au serveur...");
			
			try {
				
				jda = new JDABuilder(AccountType.BOT).setToken(MySQLUtils.getString("maz_info", "info_type", "secret_key", "info_string")).buildAsync();
				
			} catch (LoginException e) {
				
				e.printStackTrace();
				error = true;
				
			}
			
			if(error) {
				
				System.out.println("[Maz] La connexion au serveur discord a échouée !");
				
			}else {
				
				System.out.println("[Maz] Bot connecté au serveur discord !");
				EventsManager.registerEvents();
				
			}
			
		}else {
			
			System.out.println("[Maz] Connexion à la base de donnée impossible.");
			
		}
		
	}
	
	public static JDA getJDA() {
		
		return jda;
		
	}
	
	public static void shutdown() {
		
		System.out.println("[Maz] Bot déconnecté.");
		jda.shutdown();
		System.exit(0);
		
	}

}
