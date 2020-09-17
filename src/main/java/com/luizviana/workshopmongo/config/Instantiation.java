package com.luizviana.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.luizviana.workshopmongo.dto.AuthorDTO;
import com.luizviana.workshopmongo.dto.CommentDTO;
import com.luizviana.workshopmongo.entities.Post;
import com.luizviana.workshopmongo.entities.User;
import com.luizviana.workshopmongo.repository.PostRepository;
import com.luizviana.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PostRepository postRepo;

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepo.deleteAll();
		postRepo.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		userRepo.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2018 11:53:22"), "Partiu Viagem", "Vou Viajar FLw!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("20/02/2018 14:22:13"), "Buenas", "Acordei feliz hoje!",new AuthorDTO(maria));
		postRepo.saveAll(Arrays.asList(post1, post2));
		
		CommentDTO com1 = new CommentDTO("Boa Viagem!",sdf.parse("21/03/2018 13:02:22"), new AuthorDTO(alex));
		CommentDTO com2 = new CommentDTO("Boa Viagem!!",sdf.parse("21/03/2018 14:02:22"), new AuthorDTO(bob));
		CommentDTO com3 = new CommentDTO("Tenha um otimo dia!!",sdf.parse("20/02/2018 15:02:22"), new AuthorDTO(bob));
		post1.getComments().addAll(Arrays.asList(com1, com2));
		post2.getComments().add(com3);
		postRepo.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepo.save(maria);
	}

}
