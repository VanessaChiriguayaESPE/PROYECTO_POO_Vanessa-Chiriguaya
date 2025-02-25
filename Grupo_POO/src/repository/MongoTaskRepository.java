/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import db.MongoDBConnection;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import org.bson.Document;
/**
 *
 * @author HP
 */
public class MongoTaskRepository implements TaskRepository {
  private MongoCollection<Document> collection;
    
    public MongoTaskRepository() {
        MongoDatabase database = MongoDBConnection.getDatabase();
        collection = database.getCollection("tasks");  
}
 @Override
    public List<Task> findPendingTasksByUsername(String username) {
        List<Task> tasks = new ArrayList<>();
        for (Document doc : collection.find(Filters.and(
                Filters.eq("username", username),
                Filters.eq("status", "pendiente")
        ))) {
            tasks.add(new Task(
                doc.getString("username"),
                doc.getString("description"),
                doc.getString("status"),
                doc.getString("dueDate")
            ));
        }
        return tasks;
    }
}