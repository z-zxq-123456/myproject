package com.dcits.galaxy.base.util;

import com.dcits.galaxy.base.file.BufferedRandomAccessFile;
import com.dcits.galaxy.base.file.FilePositions;
import com.dcits.galaxy.base.tuple.TwoTuple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class FileUtils {
    private static final Logger logger = LoggerFactory
            .getLogger(FileUtils.class);


    /**
     * 读取文件内容到输入流
     *
     * @param filePath
     * @return
     */
    public static InputStream readFileStream(String filePath) {
        try {
            FileInputStream fs = new FileInputStream(filePath);
            return fs;
        } catch (FileNotFoundException e1) {
            if (logger.isErrorEnabled())
                logger.error(ExceptionUtils.getStackTrace(e1));
            return null;
        }
    }

    /**
     * 读取文件
     *
     * @param filePath
     * @return
     */
    public static String readFile(String filePath) {
        File file = new File(filePath);
        BufferedReader reader = null;
        FileReader fReader = null;
        // modify for sonar
        String str = null;
        try {
            fReader = new FileReader(file);
            reader = new BufferedReader(fReader);
            String temp;
            StringBuilder sb = new StringBuilder();
            while ((temp = reader.readLine()) != null) {
                sb.append(temp);
            }
            str = sb.toString();
            reader.close();
        } catch (IOException e) {
            if (logger.isErrorEnabled())
                logger.error(ExceptionUtils.getStackTrace(e));
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    if (logger.isErrorEnabled())
                        logger.error(ExceptionUtils.getStackTrace(e1));
                }
            }
            if (null != fReader) {
                try {
                    fReader.close();
                } catch (IOException e) {
                    if (logger.isErrorEnabled())
                        logger.error(ExceptionUtils.getStackTrace(e));
                }
            }
        }
        return str;
    }

    /**
     * 写文件
     *
     * @param path
     * @param str
     * @throws IOException
     */
    public static void writeFile(String path, String str) throws IOException {
        File file = new File(path);
        if (file.exists() && file.isFile()) {
            file.delete();
        }
        FileWriter fw = new FileWriter(file);
        PrintWriter out = new PrintWriter(fw);
        out.write(str);
        out.println();
        fw.close();
        out.close();
    }

    /**
     * 写文件
     *
     * @param path
     * @param content
     * @param encoding
     * @return
     */
    public static boolean writeFile(String path, String content, String encoding) {
        boolean result = false;
        File file = new File(path);
        if (file.exists() && file.isFile()) {
            file.delete();
        }

        try {
            FileOutputStream output = new FileOutputStream(file);
            output.write(content.getBytes(encoding));
            output.flush();
            output.close();
            result = true;
        } catch (Exception e) {
            if (logger.isErrorEnabled())
                logger.error(ExceptionUtils.getStackTrace(e));
        }
        return result;
    }

    /**
     * 创建文件夹
     *
     * @param path
     * @return
     */
    public static boolean createDir(String path) {
        File file = new File(path);
        if (file.exists()) {
            return true;
        } else {
            if (file.mkdirs()) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * 获取文件偏移量与实际读取行数，缓冲区默认64K
     *
     * @param file 文件路径
     * @param pos  字节偏移量
     * @param num  记录数
     * @return
     */
    private static FilePositions bufferedRandomAccessFileRowCount(File file, long pos, int num) {
        return bufferedRandomAccessFileRowCount(file, pos, num, -1);
    }

    /**
     * 获取文件偏移量与实际读取行数，自定义缓冲区大小
     *
     * @param file 文件
     * @param pos  字节偏移量
     * @param num  记录数
     * @param size 缓冲区大小
     * @return
     */
    private static FilePositions bufferedRandomAccessFileRowCount(File file, long pos, int num, int size) {
        BufferedRandomAccessFile reader = null;
        FilePositions res = null;
        int count = 0;
        try {
            if (size < 0) {
                reader = new BufferedRandomAccessFile(file, "r");
            } else {
                reader = new BufferedRandomAccessFile(file, "r", size);
            }
            reader.seek(pos);
            for (int i = 0; i < num; i++) {
                String pin = reader.readLine();
                if (StringUtils.isBlank(pin)) {
                    break;
                }
                count++;
            }
            res = new FilePositions(reader.getFilePointer(), count);
        } catch (Exception e) {
            if (logger.isErrorEnabled())
                logger.error(ExceptionUtils.getStackTrace(e));
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                if (logger.isErrorEnabled())
                    logger.error(ExceptionUtils.getStackTrace(e));
            }
        }
        return res;
    }


    /**
     * 通过BufferedRandomAccessFile获取文件偏移量与实际读取行数，默认缓冲区64K
     *
     * @param file 文件
     * @param pos  字节偏移量
     * @param num  记录数
     * @return
     */
    public static List<String> bufferedRandomAccessFileReadRows(File file, String encoding, long pos, int num) {
        return bufferedRandomAccessFileReadRows(file, encoding, pos, num, -1);
    }

    /**
     * 通过BufferedRandomAccessFile获取文件偏移量与实际读取行数，自定义缓冲区大小
     *
     * @param file 文件
     * @param pos  字节偏移量
     * @param num  记录数
     * @param size 缓冲区大小
     * @return
     */
    public static List<String> bufferedRandomAccessFileReadRows(File file, String encoding, long pos, int num, int size) {
        List<String> pins = new ArrayList();
        BufferedRandomAccessFile reader = null;
        try {
            if (size < 0) {
                reader = new BufferedRandomAccessFile(file, "r");
            } else {
                reader = new BufferedRandomAccessFile(file, "r", size);
            }
            reader.seek(pos);
            for (int i = 0; i < num; i++) {
                String pin = reader.readLine();
                if (StringUtils.isBlank(pin)) {
                    break;
                }
                pins.add(new String(pin.getBytes("8859_1"), encoding));
            }
        } catch (Exception e) {
            if (logger.isErrorEnabled())
                logger.error(ExceptionUtils.getStackTrace(e));
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                if (logger.isErrorEnabled())
                    logger.error(ExceptionUtils.getStackTrace(e));
            }
        }
        return pins;
    }


    /**
     * 获取文件总行数
     *
     * @param filePath
     * @return
     */
    public static TwoTuple<Long, List<FilePositions>> getRowCount(String filePath, int splitNum) {
        return getRowCount(filePath, splitNum, -1);
    }

    /**
     * 获取文件总行数
     *
     * @param filePath
     * @return
     */
    public static TwoTuple<Long, List<FilePositions>> getRowCount(String filePath, int splitNum, int size) {
        long pos = 0L;
        long total = 0L;
        List<FilePositions> lSpilt = new ArrayList();
        while (true) {
            FilePositions fPos;
            if (size < 0)
                fPos = bufferedRandomAccessFileRowCount(new File(filePath), pos, splitNum);
            else
                fPos = bufferedRandomAccessFileRowCount(new File(filePath), pos, splitNum, size);
            int count = fPos.getNum();
            total += count;
            lSpilt.add(new FilePositions(pos, count));
            if (count == 0) {
                break;
            }
            if (count < splitNum) {
                break;
            }
            pos = fPos.getPos();
        }
        return new TwoTuple<>(total, lSpilt);
    }


    /**
     * 获取文件总行数
     *
     * @param filePath
     * @return
     */
    public static int getRowCount(String filePath) {
        int count = 0;
        // modify for sonar
        InputStream input = null;
        BufferedReader b = null;
        try {
            input = new FileInputStream(new File(filePath));
            b = new BufferedReader(new InputStreamReader(input));
            while (b.readLine() != null) {
                count++;
            }
        } catch (IOException e) {
            count = -1;
            if (logger.isErrorEnabled())
                logger.error(ExceptionUtils.getStackTrace(e));
        } finally {
            if (null != b) {
                try {
                    b.close();
                } catch (IOException e) {
                    count = -1;
                    if (logger.isErrorEnabled())
                        logger.error(ExceptionUtils.getStackTrace(e));
                }
            }
            if (null != input) {
                try {
                    input.close();
                } catch (IOException e) {
                    count = -1;
                    if (logger.isErrorEnabled())
                        logger.error(ExceptionUtils.getStackTrace(e));
                }
            }
        }
        return count;
    }

    public static String getLastRow(String filePath) {
        String lastRow = null;
        // modify for sonar
        InputStream input = null;
        BufferedReader b = null;
        try {
            input = new FileInputStream(new File(filePath));
            b = new BufferedReader(new InputStreamReader(input));
            while (true) {
                String str = b.readLine();
                if (StringUtils.isBlank(str)) {
                    break;
                } else lastRow = str;
            }
        } catch (IOException e) {
            if (logger.isErrorEnabled())
                logger.error(ExceptionUtils.getStackTrace(e));
        } finally {
            if (null != b) {
                try {
                    b.close();
                } catch (IOException e) {
                    if (logger.isErrorEnabled())
                        logger.error(ExceptionUtils.getStackTrace(e));
                }
            }
            if (null != input) {
                try {
                    input.close();
                } catch (IOException e) {
                    if (logger.isErrorEnabled())
                        logger.error(ExceptionUtils.getStackTrace(e));
                }
            }
        }
        return lastRow;

    }

}
