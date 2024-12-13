package com.shop.luxora.repsitory;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.shop.luxora.entity.Role;
import com.shop.luxora.repository.RoleRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class RoleRepositoryTests {

	private RoleRepository repo;

	public RoleRepositoryTests(RoleRepository repo) {
		this.repo = repo;
	}
	
	@Test
	public void testCreateFirstRole() {
		Role roleadmin = new Role("Admin", "Can access and change anything.");
		Role savedRole = repo.save(roleadmin);
		assertThat(savedRole.getId()).isGreaterThan(0);
		
	}
	
	
}
