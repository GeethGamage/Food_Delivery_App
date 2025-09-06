import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.util.*;

public class ExcelProcessor {
    public static void main(String[] args) {
        String filePath = "path/to/your/excel.xlsx";
        processExcel(filePath);
    }

    public static void processExcel(String filePath) {
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming first sheet
            Row headerRow = sheet.getRow(1); // Get column names from row 2

            // Extract category names from D to K
            List<String> categories = new ArrayList<>();
            for (int i = 3; i <= 10; i++) {
                Cell cell = headerRow.getCell(i);
                if (cell != null) {
                    categories.add(cell.getStringCellValue().trim().toLowerCase());
                }
            }

            categories.add("other"); // Add "Other" category

            for (int i = 2; i <= sheet.getLastRowNum(); i++) { // Start from row 3
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Cell cell = row.getCell(2); // Column C
                if (cell == null || cell.getCellType() != CellType.STRING) continue;

                String[] sentences = cell.getStringCellValue().split(",");
                Map<String, Integer> categoryMap = new HashMap<>();
                for (String category : categories) categoryMap.put(category, 2);

                for (String sentence : sentences) {
                    sentence = sentence.trim().toLowerCase();
                    boolean matched = false;

                    for (String category : categories) {
                        if (sentence.equals(category)) {
                            categoryMap.put(category, 1);
                            matched = true;
                        }
                    }

                    if (!matched) {
                        categoryMap.put("other", 1);
                    }
                }

                // Writing results to the Excel file
                int colIndex = 3;
                for (String category : categories) {
                    Cell resultCell = row.createCell(colIndex++);
                    resultCell.setCellValue(categoryMap.get(category));
                }
            }

            // Save the updated file
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }

            System.out.println("Processing complete. File updated.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
