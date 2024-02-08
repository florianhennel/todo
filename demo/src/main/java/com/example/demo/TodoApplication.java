package com.example.demo;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.ArrayList;


@SpringBootApplication
@RestController	
public class TodoApplication {
	public static ArrayList<ToDo> tasks = new ArrayList<ToDo>();
	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}
	@CrossOrigin(origins = "http://localhost:5173")
	@GetMapping("/getTasks")
	public ArrayList<ToDo> hello() {
      return tasks;
    }
	@CrossOrigin(origins = "http://localhost:5173")
	@PostMapping("/addTask")
	public ToDo addTask(@RequestBody MyRequestObject newTask){
		ToDo todo = new ToDo(tasks.size(),newTask.getTask());
		tasks.add(todo);
		return todo;
	}

}


