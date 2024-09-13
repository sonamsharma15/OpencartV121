package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	
	
//DataProvider1::get data from excel file
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException{//2D array as return type
		
		//taking excel file path/location from testData folder
		String path=".\\testData\\OpenCart_LoginData.xlsx";
		
		
//read data from excel sheet and store it into 2D array which return data to test-method as parameter.
		
		
		//get the row and cell count of excel sheet 1st==rows and cells of 2D array
		
		//get the row and cell count from excel sheet:
		ExcelUtility xlutil=new ExcelUtility(path);//creating object of ExcelUtility-class
		
		int totalrows=xlutil.getRowCount("Sheet1");
	    int totalcols=xlutil.getCellCount("Sheet1", 1);
		
		//create 2D array to store data fetched from excel sheet:
	    
	    String loginData[][] =new String[totalrows][totalcols];
		
		//read data from excel using 2 for-loops:i=row,j=col
	    
	    for (int i = 1; i < totalrows; i++) {//1st row in excel is header so ignore
	    	
	    	for (int j = 0; j < totalcols; j++) {
	    		
	    		loginData[i-1][j]=xlutil.getCellData("Sheet1", i, j);  //2D array:::1-0  1-1  1-2  1-3
				
			}
			
		}
		
	 	return loginData;//return 2D arrya
		}
	
	//DataProvider2::get data from excel file2 for other Testcase2
	
	//DataProvider3::get data from excel file3 for other Testcase3
	
	//DataProvider4::get data from excel file4 for other Testcase4

}
