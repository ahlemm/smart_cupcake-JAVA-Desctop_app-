/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.csvreader.CsvWriter;
import entite.Materiels;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.collections.ObservableList;


/**
 *
 * @author allela
 */
public class Csv {
       

  
                      
    public Csv(ObservableList<Materiels> v) {
        // TODO code application logic here
        String outputFile = "Materiels.csv";
		//String g=;
		// before we open the file check to see if it already exists
		boolean alreadyExists = new File(outputFile).exists();
			
		try {
			// use FileWriter constructor that specifies open for appending
			  CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');
			
			// if the file didn't already exist then we need to write out the header line
			if (!alreadyExists)
			{
				
				csvOutput.write("prixMateriel");
                                csvOutput.write("etat");
				csvOutput.write("info");
				csvOutput.write("type");
				
				

				csvOutput.endRecord();
			}
			// else assume that the file already has the correct header line
			//System.out.println(v.size());
                               

				csvOutput.endRecord();
                       for(int i=0;i<v.size();i++)
                        {
                         //  csvOutput.write(Integer.toString(v.get(i).getId()));//get(i).getMatricule()
                           csvOutput.write(""+v.get(i).getPrixMateriel());                     
                           csvOutput.write(v.get(i).getEtat());
                           csvOutput.write(v.get(i).getInfo());
                           csvOutput.write(v.get(i).getType());
                           
                           csvOutput.endRecord();

                        }
			
			
			csvOutput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
 
}
