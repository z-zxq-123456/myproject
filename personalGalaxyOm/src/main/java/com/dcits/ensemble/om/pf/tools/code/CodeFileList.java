package com.dcits.ensemble.om.pf.tools.code;

import com.dcits.ensemble.om.pf.util.StorageManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tim on 2015/6/23.
 */
public class CodeFileList {

    private String path;

    private String basePath;// web站点路径

    private List<String> packAgeL = new ArrayList<>();

    private List<CodeFileItem> fileItemList = new ArrayList<>();

    public CodeFileList(String path, String basePath) {
        this.path = path;
        this.basePath = basePath;
    }

    public CodeFileList getFiles() {
        getFiles(path, "0", null);
        return this;
    }

    public List<CodeFileItem> getFileItemList() {
        return fileItemList;
    }

    public Integer getPackAgeIndex(String packAge) {
        if (!packAgeL.contains(packAge)) {
            packAgeL.add(packAge);
        }
        return packAgeL.size();
    }

    private void getFiles(String path, String pId, String packAge) {
        File root = new File(path);
        File[] files = root.listFiles();
        int i = 1;
        String currentId;
        String currentPackAge;
        String fPid;
        String fCurrentId;
        String packCurrentId;
        String packPid;
        for (File subf : files) {
            if (subf.isDirectory()) {
                currentPackAge = packAge;
                if (!pId.equals("0")) currentId = pId + i;
                else currentId = String.valueOf(i);
                if ("java".equals(subf.getParentFile().getName()) || null != packAge) {
                    if (null == packAge) {
                        currentPackAge = subf.getName();
                    } else
                        currentPackAge = currentPackAge.concat(".").concat(subf.getName());
                } else {
                    CodeFileItem fi;
                    if (subf.getName().equals("java"))
                        fi = new CodeFileItem(currentId, pId, subf.getName(), true, basePath + "/images/source-folder.gif");
                    else
                        fi = new CodeFileItem(currentId, pId, subf.getName(), true, basePath + "/images/fldr_obj.gif");
                    fileItemList.add(fi);
                }
                //System.out.println("d:" + subf.getName() + " " + currentId);
                getFiles(subf.getAbsolutePath(), currentId, currentPackAge);
            } else {
                fPid = pId;
                fCurrentId = fPid + i;
                if (null != packAge) {
                    int offset = packAge.split("\\.").length - 1;
                    //System.out.println("packAge:" + packAge);
                    //System.out.println("pId:" + pId);
                    if (offset > 0) {
                        // 求出当前包的ID
                        packCurrentId = pId.substring(0, pId.length() - offset - 1) + getPackAgeIndex(packAge);
                        // 包路径Id
                        packPid = packCurrentId.substring(0, packCurrentId.length() - 1);
                    } else {
                        // 求出当前包的ID
                        packCurrentId = pId;
                        // 包路径Id
                        packPid = pId.substring(0, pId.length() - 1);
                    }

                    //System.out.println("packCurrentId:" + packCurrentId + " packPid:" + packPid);
                    if (!contains(packCurrentId, packPid, packAge)) {
                        CodeFileItem fi = new CodeFileItem(packCurrentId, packPid, packAge, true, basePath + "/images/package_obj.gif");
                        fileItemList.add(fi);
                    }
                    fPid = packCurrentId;
                    // 重新计算currentId
                    fCurrentId = fPid + i;
                }
                CodeFileItem fi;
                if (subf.getName().endsWith("java"))
                    fi = new CodeFileItem(fCurrentId, fPid, subf.getName(), false, basePath + "/images/jcu_obj.gif");
                else if (subf.getName().endsWith("xml"))
                    fi = new CodeFileItem(fCurrentId, fPid, subf.getName(), false, basePath + "/images/xmldoc.gif");
                else
                    fi = new CodeFileItem(fCurrentId, fPid, subf.getName(), false, basePath + "/images/sourceEditor.gif");
                fi.setFilePath(subf.getAbsolutePath());
                //System.out.println("f:" + subf.getName() + " " + fCurrentId);
                fileItemList.add(fi);
            }
            i++;
        }
    }

    public String readFile(String id, String pId, String name) {
        for (CodeFileItem fi : fileItemList) {
            if (name.equals(fi.getName()) && pId.equals(fi.getpId()) && id.equals(fi.getId())) {
                return StorageManager.readFileBypath(fi.getFilePath());

            }
        }
        return null;
    }

    private boolean contains(String id, String pId, String name) {
        for (CodeFileItem fi : fileItemList) {
            if (name.equals(fi.getName()) && pId.equals(fi.getpId()) && id.equals(fi.getId()))
                return true;
        }
        return false;
    }

    public static class CodeFileItem {

        private String id;

        private String pId;

        private String name;

        private boolean open;

        private String icon;

        private String filePath;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getpId() {
            return pId;
        }

        public void setpId(String pId) {
            this.pId = pId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isOpen() {
            return open;
        }

        public void setOpen(boolean open) {
            this.open = open;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public CodeFileItem(String id, String pId, String name, boolean open, String icon) {
            this.id = id;
            this.pId = pId;
            this.name = name;
            this.open = open;
            this.icon = icon;
        }

        public String toString() {
            return "{\"id\":\"" + id + "\",\"pId\":\"" + pId + "\",\"name\":\"" + name + "\",\"open\":" + open + ",\"icon\":\"" + icon + "\"}";
        }
    }
}
