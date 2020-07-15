package com.rohanrele.clrunner;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.rohanrele.dao.RoleRepository;
import com.rohanrele.dao.UserRepository;
import com.rohanrele.entity.Role;
import com.rohanrele.entity.User;

@Configuration
public class DbDataPopulateCLRunner implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void run(String... args) throws Exception {
		// local variables
		long usersCount;

		// check if user table has any data
		usersCount = userRepository.count();

		// if user table has no data then populate data
		if (usersCount == 0) {
			// local variables
			List<User> users = null;
			User user = null;
			List<Role> roles = null;
			Role role1 = null;
			Role role2 = null;

			role1 = new Role();
			role1.setName("ASSOCIATE");
			role1 = roleRepository.save(role1);

			role2 = new Role();
			role2.setName("MANAGER");
			role2 = roleRepository.save(role2);

			users = new ArrayList<User>();
			user = new User();
			user.setUsername("appuser1");
			user.setPassword("appuser1password");
			user.getRoles().add(role1);
			users.add(user);
			user = new User();
			user.setUsername("appuser2");
			user.setPassword("appuser2password");
			user.getRoles().add(role2);
			users.add(user);

			// save to db
			userRepository.saveAll(users);
		} else { // else update tuples

		}
	}
}
