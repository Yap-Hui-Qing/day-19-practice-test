package vttp.batchb.ssf.day19_practice_test.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp.batchb.ssf.day19_practice_test.models.Task;
import vttp.batchb.ssf.day19_practice_test.repository.TaskRepository;

import static vttp.batchb.ssf.day19_practice_test.models.Task.*;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepo;

    public List<Map<String, String>> readFile(String fileName) throws FileNotFoundException, IOException, ParseException{
        
        File file = new File(fileName);

        FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);

        String json = "";
        String line;

        while ((line=br.readLine()) != null){
            json = json + line;
        }

        JsonReader jsonReader = Json.createReader(new StringReader(json));
        JsonArray array = jsonReader.readArray();

        List<Map<String, String>> values = new LinkedList<>();
        
        for (int i = 0; i<array.size(); i++){

            Map<String, String> map = new HashMap<>();

            JsonObject task = array.getJsonObject(i);

            // Sun, 10/22/2024
            SimpleDateFormat sdf = new SimpleDateFormat("E, MM/dd/yyyy");
            Date dueDate = sdf.parse(task.getString("due_date"));
            long epochmillisecondsdueDate = dueDate.getTime();
            Date createdDate = sdf.parse(task.getString("created_at"));
            long epochmillisecondscreatedDate = createdDate.getTime();
            Date updatedDate = sdf.parse(task.getString("updated_at"));
            long epochmillisecondsupdatedDate = updatedDate.getTime();

            // date should be stored as epochmilliseconds
            JsonObject jsonObjectTask = Json.createObjectBuilder()
                .add("id", task.getString("id"))
                .add("name", task.getString("name"))
                .add("description", task.getString("description"))
                .add("due_date", epochmillisecondsdueDate)
                .add("priority_level", task.getString("priority_level"))
                .add("status", task.getString("status"))
                .add("created_at", epochmillisecondscreatedDate)
                .add("updated_at", epochmillisecondsupdatedDate)
                .build();

            map.put(task.getString("id"), jsonObjectTask.toString());
            values.add(map);
        }

        return values;

    }

    public void saveTask(Map<String, String> map){
        taskRepo.saveTask(map);
    }

    public List<Task> getTasks(String key){

        List<Task> tasks = new LinkedList<>();

        for (Object obj : taskRepo.getTasks(key)){
            tasks.add(toTask(obj.toString()));
        }

        return tasks;
    }
}
