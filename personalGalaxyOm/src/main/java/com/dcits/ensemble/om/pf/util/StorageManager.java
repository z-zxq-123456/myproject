package com.dcits.ensemble.om.pf.util;

import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

/**
 * @author maruie
 * @date 2016-01-12
 * StorageManager
 * file 操作
 */
public class StorageManager {
    public static final int BUFFER_SIZE = 8192;

    public StorageManager() {
    }

    public static String readJettyPort(String path) {
        String conPath = path + "galaxy.properties";
        //galaxy.protocol.port
        String fileContent = StorageManager.readFileBypath(conPath);
        int begin = fileContent.indexOf("galaxy.web.port");
        if (begin <= 0) {
            return null;
        }
        begin += "galaxy.web.port".length() + 1;
        int end = begin;
        while (fileContent.charAt(end) != '\r') {
            end++;
        }
        return fileContent.substring(begin, end);
    }

    public static String readFileBypath(String path) {
        try {
            File file = new File(path);
            InputStream is = new FileInputStream(file);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            return new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return new BaseState(false, AppInfo.IO_ERROR).toJSONString();
        }
    }

    public static State saveFileByContent(String content, String outDir) {
        State state = new BaseState(true);
        try {
            File outFile = new File(outDir);
            FileOutputStream bos = new FileOutputStream(outFile);
            int count = content.length();
            bos.write(content.getBytes(), 0, count);
            bos.flush();
            bos.close();
            return state;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
            return new BaseState(false, AppInfo.IO_ERROR);
        }
    }

    public static void callWriteXmlFile(Document doc, Writer w) {
        try {
            Source source = new DOMSource(doc);
            Result result = new StreamResult(w);
            Transformer xformer = TransformerFactory.newInstance().newTransformer();
            xformer.transform(source, result);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public static State saveFileByInputStream(InputStream is, String path, String tmpDir) {
        State state;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        FileOutputStream out = null;
        try {
            System.out.println(tmpDir);
            File tmpFile = getTmpFile(tmpDir);
            byte[] dataBuf = new byte[2048];
            bis = new BufferedInputStream(is, StorageManager.BUFFER_SIZE);
            out = new FileOutputStream(tmpFile);
            bos = new BufferedOutputStream(out, StorageManager.BUFFER_SIZE);
            int count = 0;
            while ((count = bis.read(dataBuf)) != -1) {
                bos.write(dataBuf, 0, count);
            }
            bos.flush();
            System.out.println(tmpFile + "TMP File Path:" + path);
            state = saveTmpFile(tmpFile, path);
            if (!state.isSuccess()) {
                tmpFile.delete();
                System.out.println("TMP File FAILED.");
            }
            return state;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
            return new BaseState(false, AppInfo.IO_ERROR);
        } finally {
            if (null != bos) {
                try {
                    bos.close();
                } catch (IOException e) {
                }
            }
            if (null != bis) {
                try {
                    bis.close();
                } catch (IOException e) {
                }
            }
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }
        //return new BaseState(false, AppInfo.IO_ERROR);
    }

    private static File getTmpFile(String pathDir) {
        File tmpDir = new File(pathDir);
        String tmpFileName = (Math.random() * 10000 + "").replace(".", "");
        return new File(tmpDir, tmpFileName);
    }

    private static State saveTmpFile(File tmpFile, String path) {
        State state = null;
        File targetSaveFile = new File(path);
        if (targetSaveFile.canWrite()) {
            targetSaveFile.delete();
        }
        try {
            FileUtils.moveFile(tmpFile, targetSaveFile);
        } catch (IOException e) {
            System.out.println("TMP File " + e.getLocalizedMessage());
            return new BaseState(false, AppInfo.IO_ERROR);
        }

        state = new BaseState(true);
        state.putInfo("size", targetSaveFile.length());
        state.putInfo("title", targetSaveFile.getName());

        return state;
    }

    public static boolean valid(File file) {
        if ((!file.exists())) {
            return false;
        }
        if (!file.canWrite()) {
            return false;
        }
        return true;
    }
}