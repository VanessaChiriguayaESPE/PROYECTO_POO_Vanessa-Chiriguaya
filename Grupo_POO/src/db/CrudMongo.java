package db;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
public class CrudMongo {
    // Atributos para la conexión y la colección
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    // Método para iniciar la conexión
    public void iniciarConexion() {
        mongoClient = new MongoClient("localhost", 27017);
        database = mongoClient.getDatabase("miBaseDeDatos");
        collection = database.getCollection("miColeccion");
    }

    // Método para crear un nuevo documento
    public void crearDato() {
        Document nuevoDocumento = new Document("campo1", "valor1")
                .append("campo2", "valor2")
                .append("campo4", "valor5");

        // Insertar documento
        collection.insertOne(nuevoDocumento);

        System.out.println("Documento insertado exitosamente.");
    }

     // Método para leer todos los documentos en la colección
public void leerDatos() {
    // Obtener un cursor que apunta a los resultados de la consulta
    MongoCursor<Document> cursor = collection.find().iterator();

    // Iterar sobre los documentos mientras haya más resultados
    while (cursor.hasNext()) {
        // Obtener el siguiente documento del cursor
        Document documento = cursor.next();

        // Imprimir la representación JSON del documento
        System.out.println(documento.toJson());
    }

    // Cerrar el cursor después de procesar todos los resultados
    cursor.close();
}

    // Método para actualizar un documento
    public void actualizarDato() {
        // Filtrar documento a actualizar
        Document filtro = new Document("campo1", "valor1");

        // Definir nuevos valores
        Document nuevosValores = new Document("$set", new Document("campo2", "nuevoValor"));

        // Actualizar documento
        collection.updateOne(filtro, nuevosValores);

        System.out.println("Documento actualizado exitosamente.");
    }

    // Método para eliminar un documento
    public void eliminarDato() {
        // Filtrar documento a eliminar
        Document filtro = new Document("campo1", "valor1");

        // Eliminar documento
        collection.deleteOne(filtro);

        System.out.println("Documento eliminado exitosamente.");
    }

    // Método para cerrar la conexión
    public void cerrarConexion() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("Conexión cerrada correctamente.");
        }
    }
   }

