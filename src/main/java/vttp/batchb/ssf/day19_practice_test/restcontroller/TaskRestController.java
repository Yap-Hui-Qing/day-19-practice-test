package vttp.batchb.ssf.day19_practice_test.restcontroller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import vttp.batchb.ssf.day19_practice_test.models.Task;

@RestController
@RequestMapping
public class TaskRestController {

    private final static Logger logger = Logger.getLogger(TaskRestController.class.getName());

    // @Autowired
    // @Qualifier("redis-0")
    // RedisTemplate<String, String> redisTemplate;

    // @GetMapping("/load")
    // public String loadData() throws FileNotFoundException, IOException, ParseException{
    //     File file = new File("src/main/resources/static/todos.json");

    //     FileReader reader = new FileReader(file);
    //     BufferedReader br = new BufferedReader(reader);

    //     String json = "";
    //     String line;

    //     while ((line=br.readLine()) != null){
    //         json = json + line;
    //     }

    //     JsonReader jsonReader = Json.createReader(new StringReader(json));
    //     JsonArray array = jsonReader.readArray();

    //     List<Task> tasks = new LinkedList<>();
        
    //     for (int i = 0; i<array.size(); i++){
    //         JsonObject task = array.getJsonObject(i);

    //         // Sun, 10/22/2024
    //         SimpleDateFormat sdf = new SimpleDateFormat("E, MM/dd/yyyy");
    //         Date dueDate = sdf.parse(task.getString("due_date"));
    //         long epochmillisecondsdueDate = dueDate.getTime();
    //         Date createdDate = sdf.parse(task.getString("created_at"));
    //         long epochmillisecondscreatedDate = createdDate.getTime();
    //         Date updatedDate = sdf.parse(task.getString("updated_at"));
    //         long epochmillisecondsupdatedDate = updatedDate.getTime();

    //         // date should be stored as epochmilliseconds
    //         JsonObject jsonObjectTask = Json.createObjectBuilder()
    //             .add("id", task.getString("id"))
    //             .add("name", task.getString("name"))
    //             .add("description", task.getString("description"))
    //             .add("due_date", epochmillisecondsdueDate)
    //             .add("priority_level", task.getString("priority_level"))
    //             .add("status", task.getString("status"))
    //             .add("created_at", epochmillisecondscreatedDate)
    //             .add("updated_at", epochmillisecondsupdatedDate)
    //             .build();

    //         redisTemplate.opsForHash().put("tasks", task.getString("id"), jsonObjectTask.toString());
    //     }

    //     Map<Object, Object> taskinfos = redisTemplate.opsForHash().entries("tasks");

    //     List<Object> taskinfo = redisTemplate.opsForHash().values("tasks");

    //     return taskinfo.toString();

    // }
    
}
