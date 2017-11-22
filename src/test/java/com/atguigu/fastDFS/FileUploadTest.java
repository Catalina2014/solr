package com.atguigu.fastDFS;

import java.io.IOException;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

public class FileUploadTest {

	@Test
	public void uploadTest() throws IOException, MyException {
		//TrackerClient→TrackerServer→StorageClient
		String confPath = "/tracker.conf";
		String path = FileUploadTest.class.getResource(confPath).getPath();
		System.out.println(path);
		ClientGlobal.init(path);
		
		TrackerClient trackerClient = new TrackerClient();
		TrackerServer trackerServer = trackerClient.getConnection();
		StorageServer storageServer = null;
		StorageClient storageClient = new StorageClient(trackerServer, storageServer );
		
		String[] file = storageClient.upload_file("C:\\Users\\Administrator\\Desktop/a.jpg", "jpg", null);
		System.out.println("groupName = " + file[0]);
		System.out.println("fileName = " + file[1]);
	}
	
	@Test
	public void deleteFileTest() throws IOException, MyException {
		//TrackerClient→TrackerServer→StorageClient
		String confPath = "/tracker.conf";
		String path = FileUploadTest.class.getResource(confPath).getPath();
		System.out.println(path);
		ClientGlobal.init(path);
		
		TrackerClient trackerClient = new TrackerClient();
		TrackerServer trackerServer = trackerClient.getConnection();
		StorageServer storageServer = null;
		StorageClient storageClient = new StorageClient(trackerServer, storageServer );
		//groupName = group1
		//fileName = M00/00/00/wKjLhFoUDQ2AOPjvAAAQ3o6-0EU589.jpg
		//0 for success, none zero for fail (error code)
		int result = storageClient.delete_file("group1", "M00/00/00/wKjLhFoUDQ2AOPjvAAAQ3o6-0EU589.jpg");
		System.out.println(result);
		
	}
}
