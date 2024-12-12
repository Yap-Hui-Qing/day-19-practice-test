package vttp.batchb.ssf.day19_practice_test;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vttp.batchb.ssf.day19_practice_test.service.TaskService;

@SpringBootApplication
public class Day19PracticeTestApplication implements CommandLineRunner{

	@Value("${json.file.path}")
	private String jsonFile;

	@Autowired
	private TaskService taskSvc;

	public static void main(String[] args) {
		SpringApplication.run(Day19PracticeTestApplication.class, args);
	}

	// task 2
	@Override
	public void run(String... args) throws Exception {
		
		List<Map<String, String>> values = taskSvc.readFile(jsonFile);

		for (Map<String, String> map : values){
			taskSvc.saveTask(map);
		}
	}

	

}
