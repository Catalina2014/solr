package com.atguigu.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.atguigu.bean.Picture;

public interface PictureService {


	void uploadFile(MultipartFile file, String picDesc);

	List<Picture> showPicList();

	void deletePic(Integer id);

}
