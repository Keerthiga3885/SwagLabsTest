package Utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ExcelUtils {

    private static final String InputExcelFileLocation = "src/main/resources/SwagLabsTestData.xlsx";

    @DataProvider(name = "YourInfo")
    public Object[][] readExcel(){

        Object[][] excelData = null ;
        DataFormatter dataFormatter = new DataFormatter();

        try{

            InputStream inputStream = Files.newInputStream(Paths.get(InputExcelFileLocation));
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheet("YourInformation");

            int rowCount = sheet.getLastRowNum();
            int colCount = sheet.getRow(0).getLastCellNum();

            excelData = new Object[rowCount][colCount];

            for (int i = 1; i <=rowCount ; i++) {

                for (int j = 0; j < colCount; j++) {

                    excelData[i-1][j] = dataFormatter.formatCellValue(sheet.getRow(i).getCell(j));

                }

            }
            workbook.close();
            inputStream.close();

        }

        catch(IOException e){

            System.out.println("  Unable to read input file  ");

        }
        return excelData;
    }

}
