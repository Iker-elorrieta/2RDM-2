package Vista;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JList;

public class PanelReunionesPendientes extends JPanel {

private static final long serialVersionUID = 1L;
    
    private JButton btnVolver;
    private JTable table;

    /**
     * Create the panel.
     */
    public PanelReunionesPendientes() {

        setLayout(null);
        setBounds(100, 100, 707, 584);

        btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnVolver.setBounds(67, 354, 101, 25);
        add(btnVolver);
        
        JLabel lblNewLabel = new JLabel("REUNIONES PENDIENTES");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(67, 45, 424, 55);
        add(lblNewLabel);
        
        // Crear la tabla
        table = new JTable();
        table.setModel(generarModeloTabla());

        // Agregar la tabla dentro de un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(67, 111, 488, 226); // Ajustar posición y tamaño
        add(scrollPane);
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }

    public void setBtnVolver(JButton btnVolver) {
        this.btnVolver = btnVolver;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }
    
    private DefaultTableModel generarModeloTabla() {
        DefaultTableModel modeloTabla = new DefaultTableModel(
                null, // Sin datos iniciales
                new String[] {"Estado", "ID Reunión", "Estado Eus", "Profesor ID", 
                              "Alumno ID", "ID Centro", "Título", "Asunto", 
                              "Aula", "Fecha"} 
        );
        return modeloTabla;
    }
}