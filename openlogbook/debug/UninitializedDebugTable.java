package openlogbook.debug;

/**
 * Returns the constant string for all the columns.
 */
public class UninitializedDebugTable extends AbstractDebugTable {

    /**
     * Creates a new UninitializedDebugTable.
     *
     * @param tableName   name of the string table
     * @param columnNames names of the columns in the table
     *
     */
    public UninitializedDebugTable(String tableName, String[] columnNames) {
       super(tableName, columnNames);
    }

    /**
     * Creates a new UninitializedDebugTable.
     *
     */
    public UninitializedDebugTable() {
       super("", COLUMN_NAMES);
    }


    /**
     * Gets the number of rows in the table.
     *
     * @return the number of rows in the table
     * @todo Implement this openlogbook.debug.DebugTable method
     */
    public int getRowCount() {
        return 1;
    }

    /**
     * Gets the contents of a cell in the table.
     *
     * @param row     specifies the table row
     * @param column  specifies the table column
     *
     * @return the contents of a cell
     */
    public Object getTableCell(int row, int column) {
        return MODEL_NOT_INITIALIZED;
    }

    private static final String MODEL_NOT_INITIALIZED = "Model not initialized";
    private static final String COLUMN_NAMES[] = {"Name", "Value"} ;

    /**
     * The serialVersionUID for the class openlogbook.debug.UnitializedDebugTable.
     * This should not be changed unless incompatible changes are made to this class.
     * If you do not know what that means, you should not be changing this class.
     * DO NOT copy this to another class.
     * Any class that is created using this class as a template should regenerate
     * a new serialVersionUID with the serialVer tool.
     *
     */
    static final long serialVersionUID = 557038489930690054L;
}
