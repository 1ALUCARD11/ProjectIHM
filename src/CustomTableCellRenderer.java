import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CustomTableCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Alternance de couleurs
        if (row == 0) {
            component.setBackground(new Color(255, 255, 255)); // Gris clair pour la première ligne
        } else if (row % 2 == 0) {
            component.setBackground(Color.WHITE);
        } else {
            component.setBackground(new Color(250, 222, 211)); // Alternance de gris clair
        }

        return component;
    }
}
