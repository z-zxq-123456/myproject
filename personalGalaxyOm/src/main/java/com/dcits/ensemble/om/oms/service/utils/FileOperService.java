package com.dcits.ensemble.om.oms.service.utils;

import java.io.File;
import java.io.IOException;

import com.dcits.galaxy.base.exception.GalaxyException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



/**
 * 文件传输Service*
 * 
 * @author tangxlf
 * @date 2015-08-11
 */
@Service
public class FileOperService {
	/**
	 * 单个文件上传 
	 * @param MultipartFile  sourceFile 上传的sprinmvc文件对象
	 * @param String         path 包括文件名的文件路径
	 */
	public void singleFileUpload(MultipartFile sourceFile, String path) {
		if (sourceFile != null&&!sourceFile.isEmpty()) {
			try {
				File file = new File(path);
				if(!file.getParentFile().exists()){
					if(!file.getParentFile().mkdirs()){
						throw new GalaxyException("创建文件夹失败!");
					}
				}
				sourceFile.transferTo(new File(path));
			} catch (IllegalStateException | IOException e) {
                e.printStackTrace();
				throw new GalaxyException(e.getMessage());
			}

		} else {
            System.out.println("上传文件不存在!");
			throw new GalaxyException("上传文件不存在!");
		}
	}
	
	
	
	
	/**
	 * 单个文件上传 
	 * @param MultipartFile  sourceFile 上传的sprinmvc文件对象
	 * @param return          boolean true:删除成功   false:删除失败
	 */
	public boolean deleteFile( String path) {
		path = path.substring(0,path.lastIndexOf("/"));
		File file = new File(path);
		return deleteDir(file);
	}
	
	private boolean deleteDir(File dir){
		if(dir.isDirectory()){
			String[] children = dir.list();
			for(String fileName:children){
				boolean success = deleteDir(new File(dir,fileName));
				if(!success){
					return false;
				}
			}
		}
		return dir.delete();
	}
	/**
	 * 获取文件后缀名 
	 * @param String fileName 文件名
	 * @param return       String 文件后缀名
	 */
	public String getFileSuffix(String fileName) {			
		return fileName.substring(fileName.lastIndexOf(".")+1);
	} 
	
	public static void main(String[] args){
		String path = "E:/cctv/cl/1/add/txl.tar";
		new FileOperService().deleteFile(path);
//		path = path.substring(0,path.lastIndexOf("/"));
//		System.out.println(path);
	}
}
