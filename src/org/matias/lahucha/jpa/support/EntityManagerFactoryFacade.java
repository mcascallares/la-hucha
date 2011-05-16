package org.matias.lahucha.jpa.support;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class EntityManagerFactoryFacade {
	private static final EntityManagerFactory instance = Persistence
			.createEntityManagerFactory("default-persistence-unit");

	private EntityManagerFactoryFacade() {
	}

	public static EntityManagerFactory get() {
		return instance;
	}
}