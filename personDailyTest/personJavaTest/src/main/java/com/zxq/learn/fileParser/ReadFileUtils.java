package com.zxq.learn.fileParser;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件读取类
 * Created{ by zhouxqh} on 2018/1/5.
 */
public class ReadFileUtils {

//    private static Logger logger = LoggerFactory.getLogger(ReadFileUtils.class);

    private Workbook wb;
    private Sheet sheet;
    private Row row;
    private static final String root = ReadFileUtils.class.getResource("/").getPath();
    private static final String SEPERATE = "/";
    public ReadFileUtils() {
    }

    class MyFileFielter implements FileFilter{

        private String prefix;

        MyFileFielter(String prefix) {
            this.prefix = prefix;
        }

        public boolean accept(File pathname) {
            if (pathname.isDirectory()){
                File [] files = pathname.listFiles();
                for (File f:files){
                    if ((f.getName().endsWith(".xls")
                            || f.getName().endsWith(".xlsx")) && pathname.getName().contains(prefix))
                        return true;
                }
            }else {
                if ((pathname.getName().endsWith(".xls")
                        || pathname.getName().endsWith(".xlsx")) && pathname.getName().contains(prefix) )
                    return true;
            }
            return false;
        }
    }
    public ReadFileUtils(String filePath)
                            throws Exception{

        if (filePath=="" || filePath ==null){
            return;
        }
        String prefix = filePath.substring(filePath.lastIndexOf(ReadFileUtils.SEPERATE)+1);
        String curpath;
        if (filePath.startsWith(ReadFileUtils.SEPERATE)){
            curpath = filePath.substring(1,filePath.indexOf(ReadFileUtils.SEPERATE)+1);
        }
        else
        {
            curpath = filePath.substring(0,filePath.indexOf(ReadFileUtils.SEPERATE)+1);
        }

        boolean exist = false;
        try {

            File file = new File(root+curpath);
            File [] files = file.listFiles(new MyFileFielter(prefix));
            if (files != null && files.length > 0){
                exist = true;

                for (File f:files){

                    String fileName = f.getName();
                    String ext = fileName.substring(fileName.lastIndexOf("."));
                    InputStream is = new FileInputStream(f);

                    if (".xls".equals(ext)){
                        wb = new HSSFWorkbook(is);
                    }else if(".xlsx".equals(ext)){
                        wb = new XSSFWorkbook(f);
                    }
                }
            }
          /*  ClassLoader classLoader = ReadFileUtils.class.getClassLoader();
            Enumeration<URL> urls =  classLoader.getResources(filePath);
            while (urls.hasMoreElements()){
                exist = true;
                URL url = urls.nextElement();
                String ext = filePath.substring(filePath.lastIndexOf("."));
                try {
                    InputStream is = new FileInputStream(url.getPath());
                    if (".xls".equals(ext)){
                        wb = new HSSFWorkbook(is);
                    }else if(".xlsx".equals(ext)){
                        wb = new XSSFWorkbook(is);
                    }else {
                        wb = null;
                    }
                }
                catch (FileNotFoundException e){
//            logger.error("FileNotFoundException",e);
                }
                catch (IOException e){
//            logger.error("FileNotFoundException",e);
                }
            }*/
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        if (!exist)
            throw new Exception("file is not exist in current path !");
    }

    /**
     * 读取Excel表格表头
     * @return
     */
    public String[] readExcelFile() throws Exception{

        if(wb == null){
            throw new Exception("WorlBook对象为空！");
        }
        sheet = wb.getSheetAt(0);
        row = sheet.getRow(0);
        int colum = row.getPhysicalNumberOfCells();
        System.out.println("Column: "+colum);
        String[] title  = new String[colum];
        for (int i=0;i<colum;i++){
            title[i] = row.getCell(i).getStringCellValue();
        }
        return title;
    }

    /**
     * 读取Excel内容
     * @return
     * @throws Exception
     */
    public Map<Integer,Map<Integer,Object>> readExcelContent() throws Exception{
        if(wb==null){
            throw new Exception("Workbook对象为空！");
        }
        Map<Integer, Map<Integer,Object>> content = new HashMap<Integer, Map<Integer,Object>>();

        sheet = wb.getSheetAt(0);
        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        // 正文内容应该从第二行开始,第一行为表头的标题
        for (int i = 1; i <= rowNum; i++) {
            row = sheet.getRow(i);
            int j = 0;
            Map<Integer,Object> cellValue = new HashMap<Integer, Object>();
            while (j < colNum) {
                Object obj = getCellFormatValue(row.getCell(j));
                cellValue.put(j, obj);
                j++;
            }
            content.put(i, cellValue);
        }
        return content;
    }

    /**
     * 判断文件是否存在.
     * @param fileDir  文件路径
     * @return
     */
    public static boolean fileExist(String fileDir){
        boolean flag = false;
        File file = new File(fileDir);
        flag = file.exists();
        return flag;
    }

    /**
     * 判断文件的sheet是否存在.
     * @param fileDir   文件路径
     * @param sheetName  表格索引名
     * @return
     */
    public  boolean sheetExist(String fileDir,String sheetName) throws Exception{
        boolean flag = false;
        File file = new File(fileDir);
        if(file.exists()){    //文件存在
            //创建workbook
            try {
                wb = new XSSFWorkbook(new FileInputStream(file));
                //添加Worksheet（不添加sheet时生成的xls文件打开时会报错)
                sheet = wb.getSheet(sheetName);
                if(sheet!=null)
                    flag = true;
            } catch (Exception e) {
                throw e;
            }
        }else{    //文件不存在
            flag = false;
        }
        return flag;
    }

    /**
     * 创建新excel.
     * @param fileDir  excel的路径
     * @param sheetName 要创建的表格索引
     * @param titleRow excel的第一行即表格头
     */
    public  void createExcel(String fileDir,String sheetName,String titleRow[]) throws Exception{
        //创建workbook
        XSSFWorkbook workbook = new XSSFWorkbook();
        //添加Worksheet（不添加sheet时生成的xls文件打开时会报错)
        XSSFSheet sheet1 = workbook.createSheet(sheetName);
        //新建文件
        FileOutputStream out = null;
        try {
            //添加表头
            XSSFRow row = workbook.getSheet(sheetName).createRow(0);    //创建第一行
            for(short i = 0;i < titleRow.length;i++){
                XSSFCell cell = row.createCell(i);
                cell.setCellValue(titleRow[i]);
            }
            out = new FileOutputStream(root+fileDir);
            workbook.write(out);
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 删除文件.
     * @param fileDir  文件路径
     */
    public static boolean deleteExcel(String fileDir) {
        boolean flag = false;
        File file = new File(fileDir);
        // 判断目录或文件是否存在
        if (!file.exists()) {  // 不存在返回 false
            return flag;
        } else {
            // 判断是否为文件
            if (file.isFile()) {  // 为文件时调用删除文件方法
                file.delete();
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 往excel中写入(已存在的数据无法写入).
     * @param fileDir    文件路径
     * @param sheetName  表格索引
     * @throws Exception
     */
    public void writeToExcel(String fileDir,String sheetName,List<Map> mapList) throws Exception{
        //创建workbook
//        File file = new File(fileDir);
//        try {
//            wb = new HSSFWorkbook(new FileInputStream(file));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //流
        FileOutputStream out = null;
        sheet = wb.getSheet(sheetName);
        // 获取表格的总行数
        // int rowCount = sheet.getLastRowNum() + 1; // 需要加一
        // 获取表头的列数
        int columnCount = sheet.getRow(0).getLastCellNum()+1;
        try {
            // 获得表头行对象
            row = sheet.getRow(0);
            if(row!=null){
                for(int rowId=0;rowId<mapList.size();rowId++){
                    Map map = mapList.get(rowId);
                    Row newRow=sheet.createRow(rowId+1);
                    for (short columnIndex = 0; columnIndex < columnCount; columnIndex++) {  //遍历表头
                        String mapKey = newRow.getCell(columnIndex).toString().trim().toString().trim();
                        Cell cell = newRow.createCell(columnIndex);
                        cell.setCellValue(map.get(mapKey)==null ? null : map.get(mapKey).toString());
                    }
                }
            }

            out = new FileOutputStream(fileDir);
            wb.write(out);
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * 根据Cell类型设置数据
     *
     * @param cell
     * @return
     */
    private Object getCellFormatValue(Cell cell) {
        Object cellvalue = "";
        if (cell != null ) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:// 如果当前Cell的Type为NUMERIC
                case Cell.CELL_TYPE_FORMULA: {
                    // 判断当前的cell是否为Date
                    if (DateUtil.isCellDateFormatted(cell)) {
                        // 如果是Date类型则，转化为Data格式
                        // data格式是带时分秒的：2013-7-10 0:00:00
                        // cellvalue = cell.getDateCellValue().toLocaleString();
                        // data格式是不带带时分秒的：2013-7-10
                        Date date = cell.getDateCellValue();
                        cellvalue = date;
                    } else {// 如果是纯数字

                        // 取得当前Cell的数值
                        cellvalue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case Cell.CELL_TYPE_STRING:// 如果当前Cell的Type为STRING
                    // 取得当前的Cell字符串
                    cellvalue = cell.getRichStringCellValue().getString();
                    break;
                default:// 默认的Cell值
                    cellvalue = "";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;
    }

    /**
     * 读取txt文件
     * @param filePath
     * @return
     */
    public static String readTxt(String filePath){

        String fileContent = "";
        try
        {
            File f = new File(filePath);
            if(f.isFile()&&f.exists())
            {
                InputStreamReader read = new InputStreamReader(new FileInputStream(f),"gbk");
                BufferedReader reader=new BufferedReader(read);
                String line;
                while ((line = reader.readLine()) != null )
                {
                    fileContent += line;
                }
                read.close();
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return fileContent;
    }

    /**
     * 写入Txt
     * @param fileName
     * @param fileContent
     */
    public static void writeTxt(String fileName,String fileContent){
        try
        {
            File f = new File(fileName);
            if (!f.exists())
            {
                f.createNewFile();
            }
            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f),"gbk");
            BufferedWriter writer=new BufferedWriter(write);
            writer.write(fileContent);
            writer.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}
