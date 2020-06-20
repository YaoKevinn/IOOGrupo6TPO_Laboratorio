package collections;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import dto.PeticionDTO;

public class PeticionCollection {
	private ArrayList<PeticionDTO> datos;
	
	public PeticionCollection()
	{
		datos = leer();
		
	}
	
	public ArrayList<PeticionDTO> getPeticionList()
	{
		return datos;
	}
	
	public PeticionDTO getPeticion(int index)
	{
		return datos.get(index);
	}
	
	public void addDemoData()
	{
		for (int i=0; i<100; i++)
			datos.add(new PeticionDTO());
	}
	
	public void grabar() {
		File archivo = new File("./peticiones.txt");
		FileWriter fileWriter; 
		BufferedWriter bwEscritor; 
		String texto;
		Gson g = new Gson();
		texto = g.toJson(datos);
		//grabo el String
		try{
			//Este bloque de codigo obligatoriamente debe ir dentro de un try.
			fileWriter = new FileWriter(archivo);
			fileWriter.write(texto);
			bwEscritor = new BufferedWriter(fileWriter);
			bwEscritor.close();		
		}catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null,ex.getMessage());
		}
	}
	
    private ArrayList<PeticionDTO> leer() {
    	ArrayList<PeticionDTO> peticiones = new ArrayList<PeticionDTO>();
        String cadena;
        File archivo = new File("./peticiones.txt");
        if (archivo.exists())
        {
            FileReader f;
    		try {
    			f = new FileReader(archivo);
    	        BufferedReader b = new BufferedReader(f);
    	        cadena = b.readLine();
    	        System.out.println(cadena);
    	        JsonParser parser = new JsonParser();
    	        JsonArray gsonArr = parser.parse(cadena).getAsJsonArray();
    	        Gson g = new Gson();
    	        for(JsonElement js : gsonArr) //System.out.println(js.toString());
    	        	peticiones.add(g.fromJson(js, PeticionDTO.class));
    	        
    	        b.close();
    	        
    	        //for(Object o : aux)
    	        //	scores.add((Score) o);
    	        return peticiones;
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }
		return peticiones;
		
    }

}
