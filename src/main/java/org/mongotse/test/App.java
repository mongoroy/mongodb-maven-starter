package org.mongotse.test;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ReadPreference;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
	MongoClientURI uri = new MongoClientURI( args[0] );
        MongoClient mongoClient = new MongoClient( uri );
        MongoDatabase database = mongoClient.getDatabase("test");
        MongoCollection collection = database.getCollection("blah");//.withReadPreference(ReadPreference.nearest());
        MongoCursor<Document> cursor = collection.find( eq( "name", "roy" ) ).iterator();
        try {
            while ( cursor.hasNext() ) {
                System.out.println( cursor.next().toJson() );
            }
        }
        finally {
            cursor.close();
        }
    }
}
