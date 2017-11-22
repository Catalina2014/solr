package com.atguigu.serviceImpl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.atguigu.bean.Picture;
import com.atguigu.mapper.PictureMapper;
import com.atguigu.service.PictureService;
import com.atguigu.utils.FastDFSUtils;

@Service
public class PictureServiceImpl implements PictureService {

	@Autowired
	private PictureMapper pictureMapper;

	@Override
	public void uploadFile(MultipartFile file, String desc) {

		if (file.isEmpty()) {
			if ("".equals(desc) || desc == null) {
				return;
			}
			pictureMapper.insertSelective(new Picture(null, 
					FastDFSUtils.NO_VALUE, FastDFSUtils.NO_VALUE, desc));
			return;
		}
		
		String originalFilename = file.getOriginalFilename();
		byte[] bytes = null;
		try {
			bytes = file.getBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int lastIndexOf = originalFilename.lastIndexOf(".");
		int endIndex = originalFilename.length();
		String fileExtName = originalFilename.substring(lastIndexOf + 1, endIndex);

		String[] uploadFile = FastDFSUtils.uploadFile(bytes, fileExtName);
		String groupName = uploadFile[0];
		String fileName = uploadFile[1];

		// 将文件路径存入数据库
		pictureMapper.insertSelective(new Picture(null, groupName, fileName, desc));

	}

	@Override
	public List<Picture> showPicList() {
		List<Picture> list = pictureMapper.select(new Picture());
		return list;
	}

	@Override
	public void deletePic(Integer id) {
		Picture picture = pictureMapper.selectByPrimaryKey(id);
		pictureMapper.deleteByPrimaryKey(id);

		// 0 for success, none zero for fail (error code)
		int result = FastDFSUtils.deleteFile(picture.getGroupName(), picture.getFileName());
		System.out.println(result);

	}

}
