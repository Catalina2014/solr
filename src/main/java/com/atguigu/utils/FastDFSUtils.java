package com.atguigu.utils;

import java.io.IOException;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

public class FastDFSUtils {
	public static final String NO_VALUE = "no_value";
	private static StorageClient storageClient = null;

	static {
		try {
			String confPath = "/tracker.conf";
			String path = FastDFSUtils.class.getResource(confPath).getPath();

			ClientGlobal.init(path);

			TrackerClient trackerClient = new TrackerClient();
			TrackerServer trackerServer = trackerClient.getConnection();

			storageClient = new StorageClient(trackerServer, null);
		} catch (IOException | MyException e) {
			e.printStackTrace();
		}
	}

	public static String[] uploadFile(byte[] bytes, String fileExtName) {
		/*if (bytes) {
			
		}*/
		
		String[] uploadFile = null;

		try {
			uploadFile = storageClient.upload_file(bytes, fileExtName, null);
		} catch (IOException | MyException e) {
			throw new RuntimeException(e.getMessage());
		}

		return uploadFile;
	}

	public static int deleteFile(String groupName, String fileName) {
		int result = -1;
		try {
			// 0 for success, none zero for fail (error code)
			result = storageClient.delete_file(groupName, fileName);
		} catch (IOException | MyException e) {
			throw new RuntimeException(e.getMessage());
		}

		return result;
	}

}
