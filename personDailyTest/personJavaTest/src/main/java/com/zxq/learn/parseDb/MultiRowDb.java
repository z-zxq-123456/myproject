package com.zxq.learn.parseDb;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 数据库辅助工具类
 * 解析表的复合字段的定义
 * @param <T> AbstractBean
 * create by zhouxqh 2019-11-25
 */
@SuppressWarnings("all")
public class MultiRowDb{


    private static ConcurrentHashMap<String,TableConfig> rowMap = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String,Method> invokeMapCache = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String,Class> classMapCache = new ConcurrentHashMap<>();

    private String rulePath = "mergeRule/row-map.xml";

    public void setRulePath(String rulePath) {
        this.rulePath = rulePath;
    }

    public String getRulePath() {
        return rulePath;
    }

    public void afterPropertiesSet() throws Exception {

        URL url = this.getClass().getClassLoader().getResource(rulePath);
        if (url == null){
            throw new RuntimeException("row-map is not applied!");
        }

        Map<String,TableConfig> parseResult = parseXml(url);
        rowMap.putAll(parseResult);
    }

    /**
     * xml 配置文件解析
     * @param file 配置文件
     * @return List
     */
    private Map<String,TableConfig> parseXml(URL url){

        try
        {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            return parseBeanFromXml(url,builder);

        } catch (IOException | ParserConfigurationException e) {
        }
        return null;
    }

    private Map<String,TableConfig>  parseBeanFromXml(URL url,DocumentBuilder builder)
            throws IOException{

        Map<String,TableConfig> tableConfigMap = new ConcurrentHashMap<>();
        try {
            Document doc = builder.parse(url.openStream());
            Element root = doc.getDocumentElement();
            NodeList tableList = root.getElementsByTagName("table");

            for (int i = 0; i < tableList.getLength(); i++){
                Element element = (Element) tableList.item(i);
                TableConfig tableConfig = parseTableConfig(element);
                tableConfigMap.putIfAbsent(tableConfig.getName(),tableConfig);
            }
        }catch (SAXException e){
            e.printStackTrace();
        }
        return tableConfigMap;
    }

    /**
     * 解析所有列
     * @param element 表
     * @return List
     */
    private Map<String, TableConfig.RowConfig> parseRowConfig(Element element){

        Map<String, TableConfig.RowConfig> rowConfigMap = new HashMap<>();
        NodeList rowList = element.getElementsByTagName("row");
        for (int j = 0; j < rowList.getLength(); j++) {

            Element rowEle = (Element) rowList.item(j);
            TableConfig.RowConfig rowConfig = new TableConfig.RowConfig();
            rowConfig.setName(rowEle.getAttribute("name"));
            rowConfig.setReflect(rowEle.getAttribute("reflect"));
            rowConfig.setLocalStart(Integer.parseInt(rowEle.getAttribute("localStart")));
            rowConfig.setLocalEnd(Integer.parseInt(rowEle.getAttribute("localEnd")));
            rowConfigMap.put(rowConfig.getName(),rowConfig);
        }
        return rowConfigMap;
    }

    /**
     * 解析表中所有的复合字段
     * @param element root
     * @return TableConfig
     */
    private TableConfig parseTableConfig(Element element){
        TableConfig config = new TableConfig();
        config.setName(element.getAttribute("name"));
        config.setClassName(element.getAttribute("class"));
        config.setRowConfigs(parseRowConfig(element));
        return config;
    }

    /**
     * get multi row from single string
     * @param bean db model
     * @param table table name
     * @param field split and def individual
     * @return String
     */
    public static String getFromDBConfig(Object bean, String table,String field){
            ThreeTuple<String,Integer,Integer> threeTuple = getReflectValue(bean,table,field);
            if (threeTuple!=null){
                return  threeTuple.first != null
                        &&  threeTuple.first.trim().length()  > 0 ? threeTuple.first.substring(threeTuple.second,threeTuple.three).trim() : null;
            }
        return null;
    }

    /**
     * getReflectValue
     * @param bean bean
     * @param table table
     * @param field field
     * @return ThreeTuple String: value | Integer:start | Integer:end
     */
    private static ThreeTuple<String,Integer,Integer> getReflectValue(Object bean ,String table,String field){

        TableConfig config = rowMap.get(table);
            Map<String, TableConfig.RowConfig> rowConfigMap = config.getRowConfigs();
            TableConfig.RowConfig rowConfig = rowConfigMap.get(field);
            try {
                Class cls = fetchClassByLoader(config.getClassName());
                String methodRead = getMethodName("get", rowConfig.getReflect());
                Method read = fetchMethodByLoader(cls,methodRead,null);

                return new ThreeTuple<>((String) read.invoke(bean, null),rowConfig.getLocalStart(),rowConfig.getLocalEnd());
            } catch (NoSuchMethodException | ClassNotFoundException
                    | IllegalAccessException | InvocationTargetException e) {
            }
        return null;
    }

    private static Class fetchClassByLoader(String className)
            throws ClassNotFoundException {

        Class cls;
        if (classMapCache.get(className) != null){
            cls = classMapCache.get(className);
        }else {
            cls = MultiRowDb.class.getClassLoader().loadClass(className);
            classMapCache.putIfAbsent(className,cls);
        }
        return cls;
    }

    private static Method fetchMethodByLoader(Class cls,String methodName,Class<?> paraTypes)
            throws NoSuchMethodException {

        Method method;
        if (invokeMapCache.get(methodName) != null) {
            method = invokeMapCache.get(methodName);
        } else {
            if (paraTypes != null){
                method = cls.getMethod(methodName,paraTypes);
            }else {
                method = cls.getMethod(methodName);
            }
            invokeMapCache.putIfAbsent(methodName, method);
        }
        return method;
    }

    /**
     * setReflectValue
     * @param bean bean
     * @param table table
     * @param field field
     * @param value value
     */
    private static void setReflectValue(Object bean ,String table,String field,String value){

        TableConfig config = rowMap.get(table);
        if (value != null &&config != null) {
            Map<String, TableConfig.RowConfig> rowConfigMap = config.getRowConfigs();
            TableConfig.RowConfig rowConfig = rowConfigMap.get(field);
            try {
                Class cls = fetchClassByLoader(config.getClassName());

                String methodRead = getMethodName("get", rowConfig.getReflect());
                Method read = fetchMethodByLoader(cls,methodRead,null);


                String res = (String) read.invoke(bean,null);
                String methodWrite = getMethodName("set", rowConfig.getReflect());
                Method write = fetchMethodByLoader(cls,methodWrite,String.class);

                write.invoke(bean,composit(res,value,rowConfig.getLocalStart(),rowConfig.getLocalEnd()));

            } catch (NoSuchMethodException | ClassNotFoundException
                    | IllegalAccessException | InvocationTargetException e) {
            }
        }
    }

    /**
     * concat str
     * @param o1 orign key
     * @param o2 new key
     * @param start begin
     * @param end end
     * @return composited String
     */
    @SuppressWarnings("all")
    private static String composit(String o1,String o2,int start,int end){

        boolean needPad = false,concatDir = false;

        /*格式化字符串*/
        String formated = formatInput(o2,start,end);

        String dir = "L";
        int leftPadIndexStr = 0;
        int leftPadIndexEnd = 0;
        /*首次更新，需要填充字符串*/
        if (o1 == null){
            if (start > 0){
                needPad = true;
                leftPadIndexStr = 0;
                leftPadIndexEnd = start - 1;
            }else {/*从首位开始拼接*/
                concatDir = true;
            }
        }else {
            //原字符串长度大约end,说明字符串有值，可以直接拼接
            //o1.length() < end,则需要将o1.length()到start的字符重新填充
            if (o1.length() < start ) {
                needPad = true;
                dir = "R";
                leftPadIndexStr = o1.length();
                leftPadIndexEnd = start - 1;
            }
            else if (o1.length() == start){/*顺序拼接*/
                concatDir = true;
            }
        }

        String res  = o1;
        if (needPad){
            res = leftPad(res,formated,leftPadIndexStr,leftPadIndexEnd," ",dir);
        }else if (concatDir){
            res = concat(res,formated);
        }else {
            res = concat(res,formated,start,end);
        }
        return res;
    }

    /**
     * format input str
     * @param str2 orign
     * @param begin  begin
     * @param end end
     * @return formated str
     */
    private static String formatInput(String str2,int begin,int end){
        String tempstr = str2;
        if (str2 .length() < (end - begin)){
            tempstr = leftPad(str2,end - begin,"R");
        }else if (str2 .length() > (end - begin)){
            tempstr = str2.substring(0,end - begin);
        }
        return tempstr;
    }

    private static String concat(String str,String str2,int begin,int end){

        StringBuilder sb = new StringBuilder();
        sb.append(str, 0, begin);
        sb.append(str2);
        sb.append(str.substring(end));
        return sb.toString();
    }

    private static String concat(String str,String str2){

        if (str == null)
            return str2;

        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str2);
        return sb.toString();
    }

    private static String leftPad(String str,int len,String padStr){
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        for (int i = 0; i < len; i++){
            sb.append(padStr);
        }
        return sb.toString();
    }

    private static String leftPad(String str,String str2,int begin,int end,String padStr,String dir){

        StringBuilder sb = new StringBuilder();
        if ("L".equals(dir)){
            for (int i = begin; i <= end; i++){
                sb.append(padStr);
            }
        }else if ("R".equals(dir)){
            sb.append(str);
            for (int i = begin; i <= end; i++){
                sb.append(padStr);
            }
        }
        sb.append(str2);
        return sb.toString();
    }


    /**
     * get method name
     * @param op op type
     * @param name field
     * @return String
     */
    private static String getMethodName(String op,String name){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(op).append(name.substring(0,1).toUpperCase()).append(name.substring(1));
        return stringBuilder.toString();
    }


    /**
     * getFromDBConfig
     * @param bean bean
     * @param rowDef rowDef
     * @return Object
     */
    public static Object getFromDBConfig(Object bean, RowDef rowDef){
        return getFromDBConfig(bean,rowDef.getTable(),rowDef.getRow());
    }


    /**
     * setDbParams
     * @param bean  bean
     * @param table table name
     * @param field table field
     * @param value value
     */
    public static void setDbParams(Object bean, String table,String field,String value){
        setReflectValue(bean,table,field,value);
    }

    public static void setDbParams(Object bean,RowDef rowDef,String value){
        setDbParams(bean,rowDef.getTable(),rowDef.getRow(),value);
    }

    public static void main(String[] args) {
        try {
            new MultiRowDb().afterPropertiesSet();
            MbAcct acct = new MbAcct();
            System.out.println(getFromDBConfig(acct,RowDef.mbAcct_whiteFlag));


            MbAcctAttach attach = new MbAcctAttach();
            attach.setBalUpdType("232434");
//            attach.setBack1("12345");
//            System.out.println(getFromDBConfig(attach,RowDef.mbAcctAttach_matHolidayDelay));
//            setDbParams(attach,RowDef.mbAcctAttach_matHolidayDelay,"Y");
            System.out.println(getFromDBConfig(attach,RowDef.mbAcctAttach_matHolidayDelay));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class TableConfig{

        private String name;
        private String className;
        private Map<String,RowConfig> rowConfigs;


        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Map<String, RowConfig> getRowConfigs() {
            return rowConfigs;
        }

        public void setRowConfigs(Map<String, RowConfig> rowConfigs) {
            this.rowConfigs = rowConfigs;
        }

        private static class RowConfig{

            private String name;
            private String reflect;
            private int localStart;
            private int localEnd;

            public int getLocalStart() {
                return localStart;
            }

            public void setLocalStart(int localStart) {
                this.localStart = localStart;
            }

            public int getLocalEnd() {
                return localEnd;
            }

            public void setLocalEnd(int localEnd) {
                this.localEnd = localEnd;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }


            public String getReflect() {
                return reflect;
            }

            public void setReflect(String reflect) {
                this.reflect = reflect;
            }
        }

    }

    public enum RowDef{

        mbAcct_whiteFlag("mb_acct","whiteFlag","是否白名单账户"),
        mbAcctAttach_matHolidayDelay("mb_acct_attach","matHolidayDelay","定期节假日到期是否顺延");

        private String table;
        private String row;
        private String desc;

        RowDef(String table, String row,String desc) {
            this.table = table;
            this.row = row;
            this.desc = desc;
        }

        public String getTable() {
            return table;
        }

        public void setTable(String table) {
            this.table = table;
        }

        public String getRow() {
            return row;
        }

        public void setRow(String row) {
            this.row = row;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }


}
