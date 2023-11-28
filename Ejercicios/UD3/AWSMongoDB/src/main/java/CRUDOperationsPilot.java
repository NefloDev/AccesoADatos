import Entities.Driver;
import com.mongodb.client.*;
import static com.mongodb.client.model.Sorts.ascending;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.MongoClient.getDefaultCodecRegistry;
import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;

public class CRUDOperationsPilot {
    private final static String USER = "alejandro";
    private final static String PASSWORD = "alejo";
    private final static String DNS = "ec2-54-160-92-6.compute-1.amazonaws.com";
    private final static String PORT = "27017";
    private final static String DATABASE = "f1-2006";
    private final static String URL = "mongodb://" + USER + ":" + PASSWORD + "@" + DNS + ":" + PORT + "/" + DATABASE;
    private static MongoCollection<Driver> collection;
    private static MongoClient client;

    private static void startConnection(){
        try{
            client = MongoClients.create(URL);
            CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
            CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
            MongoDatabase database = client.getDatabase(DATABASE).withCodecRegistry(pojoCodecRegistry);
            collection = database.getCollection("drivers", Driver.class);
        }catch (Exception e){
            System.out.println("Error connecting to the database");
        }
    }

    public static void createPilot(Driver pilot) throws RuntimeException{
        startConnection();
        if(collection == null){
            throw new RuntimeException("Error creating pilot");
        }
        collection.insertOne(pilot);
        stopConnection();

    }

    public static Driver readPilot(String code) throws RuntimeException{
        startConnection();
        if(collection == null){
            stopConnection();
            throw new RuntimeException("Error reading pilot");
        }
        stopConnection();
        return collection.find(org.bson.Document.parse("{code: \"" + code + "\"}")).first();
    }

    public static List<Driver> readPilots(){
        startConnection();
        ArrayList<Driver> drivers = new ArrayList<>();
        if(collection == null){
            stopConnection();
            throw new RuntimeException("Error reading pilots");
        }
        try(MongoCursor<Driver> cursor = collection.find().iterator()){
            while (cursor.hasNext()){
                drivers.add(cursor.next());
            }
            stopConnection();
            return drivers;
        }catch (Exception e){
            System.out.println("Error reading pilots");
            stopConnection();
            return null;
        }
    }

    public static void updateDriver(Driver d){
        startConnection();
        if (collection==null){
            stopConnection();
            throw new RuntimeException("Error updating driver");
        }
        collection.replaceOne(eq("driverid", d.getDriverid()), d);
        stopConnection();
    }

    public static void deleteDriver(Driver d){
        startConnection();
        if (collection==null){
            stopConnection();
            throw new RuntimeException("Error deleting driver");
        }
        collection.deleteOne(eq("driverid", d.getDriverid()));
        stopConnection();
    }

    public static void showPilotsOrderedByAgeDescending(){
        startConnection();
        if (collection==null){
            stopConnection();
            throw new RuntimeException("Error showing pilots");
        }
        try{
            collection.find().sort(ascending("dob"))
                    .forEach(driver -> System.out.println(driver.getForename() + " - " + (2006 - driver.getDob().getYear())));
        }catch (Exception e){
            stopConnection();
            System.out.println("Error showing pilots");
        }
    }
    public static void showPilotsOlderThan(int age){
        startConnection();
        if (collection==null){
            stopConnection();
            throw new RuntimeException("Error showing pilots");
        }
        try{
            collection.find().sort(ascending("dob")).forEach(driver -> {
                if (2006 - driver.getDob().getYear() > age)
                    System.out.println(driver.getForename() + " - " + (2006 - driver.getDob().getYear()));
            });
        }catch (Exception e){
            stopConnection();
            System.out.println("Error showing pilots");
        }
    }

    public static void stopConnection(){
        client.close();
    }

}
