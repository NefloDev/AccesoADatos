import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.Objects;
import java.util.function.Consumer;

import static com.mongodb.client.model.Filters.eq;

public class MongoDBManager {
    private static final String USER = "nebflo";
    private static final String PASSWORD = "qwerty1234";
    private static final String ENDPOINT = "ec2-54-146-188-92.compute-1.amazonaws.com";
    private static final String PORT = "27017";
    private static final String URL = String.format("mongodb://%s:%s@%s:%s", USER, PASSWORD, ENDPOINT, PORT);
    private static final String DATABASE = "nebflo";
    private static final String COLLECTION = "clients";
    private MongoClient client;
    private MongoCollection<Client> clients;

    private void startConnection(){
        try{
            client = MongoClients.create(URL);
            CodecProvider codecProvider = PojoCodecProvider.builder().automatic(true).build();
            CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
                    com.mongodb.MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromProviders(codecProvider));
            MongoDatabase database = client.getDatabase(DATABASE).withCodecRegistry(codecRegistry);
            clients = database.getCollection(COLLECTION, Client.class);
        }catch (Exception e){
            System.err.println("Error connecting to the database");
        }
    }

    private void stopConnection(){
        client.close();
    }

    public void createClient(Client client){
        startConnection();
        if(clients == null){
            System.err.println("Error creating client");
        }else{
            if(clients.find(eq("dni", client.getDni())).first() != null){
                stopConnection();
                System.err.println("Error creating client, client with same dni already exists");
            }else{
                //Para que el cliente tenga siempre el id siguiente al máximo
                createClientId(client);
                clients.insertOne(client);
            }
        }
        stopConnection();
    }

    public boolean deleteClient(Client client){
        startConnection();
        if(clients == null){
            System.err.println("Error deleting client");
        }else{
            clients.deleteOne(Document.parse("{clientid: " + client.getClientid() + "}"));
        }
        stopConnection();
        return true;
    }

    public boolean deleteClientById(int id) throws RuntimeException{
        startConnection();
        if(clients == null){
            System.err.println("Error deleting client");
        }else{
            clients.deleteOne(Document.parse("{clientid: " + id + "}"));
        }
        stopConnection();
        return true;
    }

    public void modifyClient(Client client) throws RuntimeException{
        startConnection();
        if(clients == null){
            System.err.println("Error modifying client");
        }else{
            if(client.getClientid() != 0 || clients.find(eq("clientid", client.getClientid())).first() != null){
                System.err.println("Error modifying client, client with same id already exists");
            }else{
                if (client.getClientid()==0){
                    clients.replaceOne(eq("clientid", client.getClientid()), client);
                }
                clients.replaceOne(eq("clientid", client.getClientid()), client);
            }
        }
        stopConnection();
    }

    public void showAllClients(){
        startConnection();
        if(clients == null){
            System.err.println("Error showing clients");
        }else{
            clients.find().forEach((Consumer<? super Client>) client -> System.out.println(client.toString()));
        }
        stopConnection();
    }

    public void showClientById(int id){
        startConnection();
        if(clients == null){
            System.err.println("Error showing client");
        }else{
            clients.find(eq("clientid", id)).forEach((Consumer<? super Client>) client -> System.out.println(client.toString()));
        }
        stopConnection();
    }

    public Client getClientById(int id){
        startConnection();
        if(clients == null){
            System.err.println("Error showing client");
        }else{
            return clients.find(eq("clientid", id)).first();
        }
        stopConnection();
        return null;
    }

    private void createClientId(Client client){
        client.setClientid(Objects.requireNonNull(clients.find().sort(Document.parse("{clientid: -1}")).first()).getClientid() + 1);
    }

}
