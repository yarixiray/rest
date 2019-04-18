package com.rest.webservices.rest.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserResource {

//    @Autowired
//    private UserDaoService userDaoService; // removed a part --->  = new UserDaoService();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    //    @GetMapping("/users")
//    public List<User> retrieveAllUsers() {
//        return userDaoService.findAll();
//    }
    @GetMapping("/jpa/users/{id}")
    public Resource<User> retrieveUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent()) {
            throw new UserNotFoundException("id-" + id);
        }
        Resource<User> resource = new Resource<User>(user.get());

        ControllerLinkBuilder linkTo =
                linkTo(methodOn(this.getClass()).retrieveAllUsers());

        resource.add(linkTo.withRel("all-users"));

        return resource;
    }

//    @GetMapping("/users/{id}")
//    public Resource<User> retrieveUser(@PathVariable int id) {
//        User user = userDaoService.findOne(id);
//        if (user == null) {
//            throw new UserNotFoundException("id-" + id);
//        }
//        Resource<User> resource = new Resource<>(user);
//        ControllerLinkBuilder linkTo =
//                linkTo(methodOn(this.getClass()).retrieveAllUsers());
//
//        resource.add(linkTo.withRel("all-users"));
//
//        return resource;
//    }


    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User saveUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saveUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

//    @PostMapping("/users")
//    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
//        User saveUser = userDaoService.save(user);
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(saveUser.getId()).toUri();
//        return ResponseEntity.created(location).build();
//    }


    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    //    @DeleteMapping("/users/{id}")
//    public void deleteUser(@PathVariable int id) {
//        User user = userDaoService.deleteById(id);
//        if (user == null) {
//            throw new UserNotFoundException("id-" + id);
//        }
//    }
    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrieveAllUser(@PathVariable int id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("id-" + id);
        }
        return userOptional.get().getPosts();
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPost(@RequestBody Post post, @PathVariable int id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("id-" + id);
        }
        User user = userOptional.get();
        post.setUser(user);
        postRepository.save(post);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

}
