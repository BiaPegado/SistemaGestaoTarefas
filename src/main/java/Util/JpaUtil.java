package Util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
	private static EntityManagerFactory managerPostgre;
	
	static {
		managerPostgre = Persistence.createEntityManagerFactory("AplicacaoEventoSelecao");
	}

	public static EntityManager getEntityManagerPostgre() {
		return managerPostgre.createEntityManager();
	}
}
