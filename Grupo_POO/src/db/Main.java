package db;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author HP
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CrudMongo crudMongo = new CrudMongo();

        // Iniciar la conexión
        crudMongo.iniciarConexion();

        // Crear un nuevo documento
        crudMongo.crearDato();

        // Leer todos los documentos
        crudMongo.leerDatos();

        // Actualizar un documento
       // crudMongo.actualizarDato();

        // Eliminar un documento
       // crudMongo.eliminarDato();

        // Cerrar la conexión
        crudMongo.cerrarConexion();
        
    
    }
    
}
