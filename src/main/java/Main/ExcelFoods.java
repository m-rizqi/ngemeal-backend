package Main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import org.apache.poi.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFoods {
    static Scanner sc = new Scanner(System.in);
    static String path = "E:\\Ngemeal-Firebase\\src\\main\\java\\Repository\\Excel\\Foods.xlsx";
    public static void main(String[] args) throws IOException {
//        FileInputStream file = new FileInputStream(path);
//        XSSFWorkbook workbook = new XSSFWorkbook(file);
//        XSSFSheet sheet1 = workbook.getSheetAt(0);
//        XSSFRow row1 = sheet1.getRow(10);
//        XSSFCell cell1 = row1.getCell(0);
//        System.out.println(cell1.getStringCellValue());
//        workbook.close();
//        file.close();

        System.out.println("Menu\n1.Add Place\n2.Add Menu\n3.Exit");

        boolean exit = false;
        while(!exit){
            System.out.println("Choice : ");
            int a = sc.nextInt();
            switch (a){
                case 1:
                    System.out.println("Start Index : ");
                    writePlace(sc.nextInt());
                    break;
                case 2:
                    System.out.println("Start Index : ");
                    writeMenu(sc.nextInt());
                case 3:
                    exit = true;
                    break;
            }
        }

    }
    public static void writePlace(int start) throws IOException {
        int index = start;
        FileInputStream file = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet1 = workbook.getSheetAt(0);


        XSSFRow row0 = sheet1.getRow(index);

        XSSFCell cell00 = row0.createCell(0);
        cell00.setCellValue((String) "Place");

        XSSFCell cell01 = row0.createCell(1);
        cell01.setCellValue((String) "Latitude");

        XSSFCell cell02 = row0.createCell(2);
        cell02.setCellValue((String) "Longitude");

        XSSFCell cell03 = row0.createCell(3);
        cell03.setCellValue((String) "Gofood");

        XSSFCell cell04 = row0.createCell(4);
        cell04.setCellValue((String) "Grabfood");

        XSSFCell cell05 = row0.createCell(5);
        cell05.setCellValue((String) "Address");


        XSSFRow row1 = sheet1.getRow(++index);

        System.out.print("\nPlace : ");
        String place = sc.next();
        place += " "+ sc.nextLine();
        XSSFCell cell10 = row1.createCell(0);
        cell10.setCellValue((String) place);

        System.out.print("\nLatitude : ");
        double latitude = sc.nextDouble();
        XSSFCell cell11 = row1.createCell(1);
        cell11.setCellValue((double) latitude);


        System.out.print("\nLongitude : ");
        double longitude = sc.nextDouble();
        XSSFCell cell12 = row1.createCell(2);
        cell12.setCellValue((double) longitude);

        System.out.print("\nGofood : ");
        String gofood = sc.next();
        gofood += " "+ sc.nextLine();
        XSSFCell cell13 = row1.createCell(3);
        cell13.setCellValue((String) gofood);

        System.out.print("\nAddress : ");
        String address = sc.next();
        address += " "+ sc.nextLine();
        XSSFCell cell15 = row1.createCell(5);
        cell15.setCellValue((String) address);


        file.close();
        FileOutputStream fileoutput = new FileOutputStream(path);
        workbook.write(fileoutput);
        workbook.close();
        fileoutput.close();
    }

    public static void writeMenu(int start) throws FileNotFoundException, IOException{
        int index = start;
        boolean exit = false;
        while(!exit){
        FileInputStream file = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet1 = workbook.getSheetAt(0);
        XSSFRow row = sheet1.getRow(index);
            System.out.println(row);
        System.out.print("\nName : ");
        String name = sc.next();
        name += " "+ sc.nextLine();
        XSSFCell cell0 = row.createCell(0);
        cell0.setCellValue((String) name);
        System.out.print("\nGofood : ");
        String gofood = sc.next();
        gofood += " "+ sc.nextLine();
        XSSFCell cell1 = row.createCell(1);
        cell1.setCellValue((String) gofood);
        System.out.print("\nTag : ");
        String tag = sc.next();
        tag += " "+ sc.nextLine();
        XSSFCell cell3 = row.createCell(3);
        cell3.setCellValue((String) tag);
        file.close();
        FileOutputStream fileoutput = new FileOutputStream(path);
        workbook.write(fileoutput);
        workbook.close();
        fileoutput.close();
        
        index++;
        System.out.println("Exit ? 1/2");
        int ex = sc.nextInt();
        if(ex == 1 ){
        exit = true;
        }
        }
    }    
}
