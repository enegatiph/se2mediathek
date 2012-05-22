package de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.subwerkzeuge.vormerkmedienauflister;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.JTableHeader;

import de.uni_hamburg.informatik.swt.se2.mediathek.werkzeuge.UIConstants;

/**
 * {@link VormerkMedienauflisterUI} beschreibt die grafische
 * Benutzungsoberfläche für das {@link VormerkMedienauflisterWerkzeug}.
 * 
 * @author SE2-Team
 * @version SoSe 2012
 */
public class VormerkMedienauflisterUI
{
    private VormerkMedienTableModel _medienTableModel;
    private JPanel _hauptPanel;
    private JTable _medienTable;

    /**
     * Initialisiert eine neue {@link VormerkMedienauflisterUI}.
     */
    public VormerkMedienauflisterUI()
    {
        erzeugeHauptPanel();
        erzeugeMedienTable();
    }

    /**
     * Erzeugt das Haupt-Panel.
     */
    private void erzeugeHauptPanel()
    {
        _hauptPanel = new JPanel(new BorderLayout());
    }

    /**
     * Erzeugt die Tabelle für die Anzeige der Medien.
     */
    private void erzeugeMedienTable()
    {
        JScrollPane medienAuflisterScrollPane = new JScrollPane();
        medienAuflisterScrollPane.setBorder(BorderFactory.createTitledBorder(
                null, "Medien", TitledBorder.LEADING,
                TitledBorder.DEFAULT_POSITION, UIConstants.HEADER_FONT));
        medienAuflisterScrollPane.setBackground(UIConstants.BACKGROUND_COLOR);
        medienAuflisterScrollPane.getVerticalScrollBar().setBackground(
                UIConstants.BACKGROUND_COLOR);
        medienAuflisterScrollPane.getHorizontalScrollBar().setBackground(
                UIConstants.BACKGROUND_COLOR);

        _medienTableModel = new VormerkMedienTableModel();
        _medienTable = new JTable();
        medienAuflisterScrollPane.setViewportView(_medienTable);
        _medienTable.setModel(_medienTableModel);
        JTableHeader tableHeader = _medienTable.getTableHeader();
        tableHeader.setFont(UIConstants.HEADER_FONT);
        tableHeader.setReorderingAllowed(false);
        tableHeader.setResizingAllowed(false);
        _medienTable.setFont(UIConstants.TEXT_FONT);

        _hauptPanel.add(medienAuflisterScrollPane, BorderLayout.CENTER);
    }

    /**
     * Gibt die Medienauflistertabelle ({@link JTable}) zurück.
     * 
     * @ensure result != null
     */
    public JTable getMedienAuflisterTable()
    {
        return _medienTable;
    }

    /**
     * Gibt das {@link VormerkMedienTableModel} der UI zurück.
     * 
     * @ensure result != null
     */
    public VormerkMedienTableModel getMedienAuflisterTableModel()
    {
        return _medienTableModel;
    }

    /**
     * Gibt das Haupt-Panel der UI zurück.
     * 
     * @ensure result != null
     */
    public JPanel getUIPanel()
    {
        return _hauptPanel;
    }
}
