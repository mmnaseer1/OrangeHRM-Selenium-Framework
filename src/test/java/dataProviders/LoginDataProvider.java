package dataProviders;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

public class LoginDataProvider {

	@DataProvider(name = "OrangeHRM_LoginData")
	public String[][] getLoginData(Method m) throws IOException{
		String excelFileName = m.getName();
		String excelFilePath = "testData/OrangeHRM_LoginData.xlsx";
		String[][] data;
		
		try(InputStream is = getClass().getClassLoader().getResourceAsStream(excelFilePath)){
			
			if (is == null) {
				throw new IOException("Could not find the file on classpath" + excelFilePath);
			}
			
			try(Workbook workBook = WorkbookFactory.create(is)){
				Sheet sheet = workBook.getSheet(excelFileName);
				
				int rowCount = sheet.getLastRowNum();
				int colCount = sheet.getRow(rowCount).getLastCellNum();
				
				data = new String [rowCount][colCount];
				DataFormatter format = new DataFormatter();
				
				for (int i = 1; i <= rowCount; i++) {
					for (int j = 0; j < colCount; j++) {
						data[i - 1][j] = format.formatCellValue(sheet.getRow(i).getCell(j));
					}
				}
			}
		}
		return data;
	}
}
