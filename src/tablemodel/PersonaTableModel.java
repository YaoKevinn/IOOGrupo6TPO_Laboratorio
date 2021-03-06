package tablemodel;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import collections.PacienteCollection;
import collections.PersonaCollection;
import dto.PacienteDTO;
import dto.PersonaDTO;;

public class PersonaTableModel extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<PacienteDTO> lista;
	
	protected String[] columnNames = new String[] { "Paciente ID", "Nombre", "DNI", "Domicilio", "mail", "Sexo", "Fecha Nacimiento"}; 
	protected Class[] columnClasses = new Class[] { String.class, String.class, String.class, String.class, String.class, String.class, String.class}; 


	public String getColumnName(int col) { return columnNames[col]; } 
	public Class getColumnClass(int col) { return columnClasses[col]; } 
	
	public PersonaTableModel(PacienteCollection coleccionPaciente)
	{
		lista = coleccionPaciente.getPacienteList();
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return lista.size();
	}
	
    @Override
    public boolean isCellEditable(int row, int column)
    {
    	return false;
    }
    
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) 
		{
			case 0: return lista.get(rowIndex).getPacienteID(); 
			case 1: return lista.get(rowIndex).getNombre();
			case 2: return lista.get(rowIndex).getDni();
			case 3: return lista.get(rowIndex).getDomicilio();
			case 4: return lista.get(rowIndex).getMail();
			case 5: return lista.get(rowIndex).getSexo();
			case 6: return lista.get(rowIndex).getFechaNacimiento();
			default: return null; 
		}
	}
	
	public void agregar(PacienteDTO paciente)
	{
		lista.add(paciente);
		fireTableDataChanged();
	}
	
	public void refresh() {
		fireTableDataChanged();
	}
	
	public void eliminar(int fila)
	{
		lista.remove(fila);
		fireTableDataChanged();
	}
	
	public void eliminar(PacienteDTO paciente)
	{
		eliminar(lista.indexOf(paciente));
	}	

}
