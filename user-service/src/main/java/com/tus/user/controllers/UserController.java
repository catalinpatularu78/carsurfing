package com.tus.user.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import com.tus.user.models.ERole;
import com.tus.user.models.Role;
import com.tus.user.models.User;
import com.tus.user.payload.request.EditRequest;
import com.tus.user.payload.response.MessageResponse;
import com.tus.user.repository.RoleRepository;
import com.tus.user.repository.UserRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.password.PasswordEncoder;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {
  
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	
	@GetMapping("/headerback")
	public String generalMessage(@RequestHeader("Authorization") String header) {
		return header;
	}
	
	@GetMapping("/userauth/{id}")
	  @PreAuthorize("(hasRole('USER') and #id == authentication.principal.id) or hasRole('MODERATOR') or hasRole('ADMIN')")
	  public Boolean userAuth(@PathVariable("id") Long id) {
			return true;
	  }
	@GetMapping("/generalauth")
	  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	  public Boolean generAlauth() {
			return true;
	  }

	@GetMapping("/adminauth")
	  @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	  public Boolean adminAuth() {
			return true;
	  }

	
	
  @GetMapping("/users")
  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
  public List<User> getAllUsers() {

		return repo.findAll();

  }
  
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")

	public Optional<User> getUser(@PathVariable("id") Long id) {

		return repo.findById(id);

	}
	
	@PutMapping("/{id}")
	@PreAuthorize("(hasRole('USER') and #id == authentication.principal.id) or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<?> editUser(@PathVariable("id") Long id, @Valid @RequestBody EditRequest editRequest) {

		if (repo.findById(id) != null) {
			User user = new User(editRequest.getUsername(), 
						editRequest.getEmail(),
		            	encoder.encode(editRequest.getPassword()), editRequest.getPhone(), editRequest.getDescription());
			user.setId(id);
			 Set<String> strRoles = editRequest.getRole();
			    Set<Role> roles = new HashSet<>();

			    if (strRoles == null) {
			      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
			          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			      roles.add(userRole);
			    } else {
			      strRoles.forEach(role -> {
			        switch (role) {
			        case "admin":
			          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
			              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			          roles.add(adminRole);

			          break;
			        case "mod":
			          Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
			              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			          roles.add(modRole);

			          break;
			        default:
			          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
			              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			          roles.add(userRole);
			        }
			      });
			    }
			    user.setRoles(roles);
			    repo.save(user);

			    return ResponseEntity.ok(new MessageResponse("User edited successfully!"));
		}
		else{
			return ResponseEntity
			          .badRequest()
			          .body(new MessageResponse("Error: ID not found!"));
		}

	
	
  }

	@DeleteMapping("/{id}")
	@PreAuthorize("(hasRole('USER') and #id == authentication.principal.id) or hasRole('MODERATOR') or hasRole('ADMIN')")

	public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
		if (repo.findById(id) != null) {
			repo.deleteById(id);
			return ResponseEntity.ok(new MessageResponse("User deleted successfully!"));
		}
		else {
			return ResponseEntity
			          .badRequest()
			          .body(new MessageResponse("Error: ID not found!"));
		}

	}
	
	@PutMapping("/editrating/{reviewedId}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public void editRating(@PathVariable("reviewedId") Long reviewedId, @Valid @RequestBody Double rating) {
		if (repo.findById(reviewedId) != null) {
			Optional<User> oldUser=repo.findById(reviewedId);
			User user = oldUser.get();
			user.setRating(rating);
			repo.save(user);

			
		}

	
	
  }


}

	