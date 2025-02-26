package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	//storing the data from excelto 2 dimensional array
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException{
		String path =".\\testdata\\loginF.xlsx"; // taking xl file form testdata
		
		ExcelUtility xlutil=new ExcelUtility(path);
		
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalCols=xlutil.getCellCount("Sheet1", 1);
		
		String logindata[][] =new String[totalrows][totalCols];  // created for 2 dimension array 
		//for rows as 0th row is headerwe are giving i =1
		for(int i =1;i<=totalrows;i++) 
			{    //1  read the data from xl storing in 2 dimensional array
				  //for colmns as col starts from 0  
			for(int j =0;j<totalCols;j++) {   //0 i is rows j is col
				logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j);
			}
		}
		
	return logindata;		
		
	}
}
