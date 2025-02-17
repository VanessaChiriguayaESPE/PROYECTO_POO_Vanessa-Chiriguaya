import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Importación del driver de MongoDB
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class TaskManager extends JFrame {

    // Componentes de la interfaz
    private JTextField titleField;
    private JTextArea descriptionArea;
    private JButton addButton;

    // Colección de tareas en MongoDB
    private MongoCollection<Document> taskCollection;

    // Constructor: inicializa la interfaz y la conexión a MongoDB
    public TaskManager() {
        initComponents();
        initMongoDB();
    }

    // Configuración de la interfaz gráfica
    private void initComponents() {
        setTitle("Task Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Panel principal con BorderLayout y margen interno
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel para los campos de entrada con BoxLayout vertical
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        // Etiqueta y campo para el título de la tarea
        JLabel titleLabel = new JLabel("Título de la Tarea:");
        titleField = new JTextField();
        titleField.setMaximumSize(new Dimension(Integer.MAX_VALUE, titleField.getPreferredSize().height));

        // Etiqueta y área para la descripción de la tarea
        JLabel descriptionLabel = new JLabel("Descripción:");
        descriptionArea = new JTextArea(5, 20);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(descriptionArea);

        // Agregar componentes al panel del formulario
        formPanel.add(titleLabel);
        formPanel.add(titleField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio vertical
        formPanel.add(descriptionLabel);
        formPanel.add(scrollPane);

        // Botón para agregar la tarea
        addButton = new JButton("Agregar Tarea");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });

        // Agregar el formulario y el botón al panel principal
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(addButton, BorderLayout.SOUTH);

        // Agregar el panel principal a la ventana
        add(mainPanel);
    }

    // Inicialización de la conexión a MongoDB
    private void initMongoDB() {
        try {
            // Cadena de conexión (por defecto, localhost:27017)
            String uri = "mongodb://localhost:27017";
            MongoClient mongoClient = MongoClients.create(uri);
            // Se conecta a la base de datos "TaskDB" (se crea si no existe)
            MongoDatabase database = mongoClient.getDatabase("TaskDB");
            // Se conecta a la colección "tasks" (se crea al insertar el primer documento)
            taskCollection = database.getCollection("tasks");
            System.out.println("Conexión a MongoDB exitosa.");
        } catch (Exception ex) {
            System.err.println("Error al conectar a MongoDB: " + ex.getMessage());
            JOptionPane.showMessageDialog(this,
                    "Error al conectar a MongoDB: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para agregar una tarea a la base de datos
    private void addTask() {
        String title = titleField.getText().trim();
        String description = descriptionArea.getText().trim();

        // Validación: el título no debe estar vacío
        if (title.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "El título no puede estar vacío.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear un documento de MongoDB para la tarea
        Document taskDoc = new Document("title", title)
                                .append("description", description);

        try {
            // Insertar el documento en la colección "tasks"
            taskCollection.insertOne(taskDoc);
            JOptionPane.showMessageDialog(this, "Tarea agregada exitosamente.");
            // Limpiar los campos de entrada
            titleField.setText("");
            descriptionArea.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al agregar la tarea: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método principal para ejecutar la aplicación
    public static void main(String[] args) {
        // Ejecutar la interfaz en el hilo de despacho de eventos de Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TaskManager().setVisible(true);
            }
        });
    }
}
