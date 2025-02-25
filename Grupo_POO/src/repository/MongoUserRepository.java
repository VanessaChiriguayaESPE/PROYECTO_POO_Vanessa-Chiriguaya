/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.User;
import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;
import db.MongoDBConnection;
/**
 *
 * @author HP
 */
public class MongoUserRepository implements UserRepository{
    private MongoCollection<Document> collection;
    
    public MongoUserRepository() {
        MongoDatabase database = MongoDBConnection.getDatabase();
        collection = database.getCollection("users");
    }
    
    @Override
    public User findByUsername(String username) {
        Document doc = collection.find(eq("username", username)).first();
        if (doc != null) {
            return new User(
                doc.getString("username"),
                doc.getString("password"),
                doc.getString("fullName")
            );
        }
        return null;
    }
} 

