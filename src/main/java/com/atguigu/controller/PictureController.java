package com.atguigu.controller;

import java.util.List;

import javax.servlet.jsp.PageContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.atguigu.bean.Picture;
import com.atguigu.service.PictureService;

@Controller
public class PictureController {

	@Autowired
	private PictureService pictureService;
	
	@RequestMapping(value="/picture",method=RequestMethod.POST)
	public String uploadFile(@RequestParam("picDesc")String picDesc,@RequestParam("pic")MultipartFile file) {
		
		pictureService.uploadFile(file,picDesc);
		return "redirect:/pictures";
	}
	
	@RequestMapping(value="/pictures",method=RequestMethod.GET)
	public String showPicList(Model model) {
		List<Picture> pictures = pictureService.showPicList();
		model.addAttribute("pictures", pictures);
		return "pic_list";
	}
	
	@RequestMapping(value="/picture/{id}",method=RequestMethod.GET)
	public String deletePic(@PathVariable("id")Integer id) {
		pictureService.deletePic(id);
		return "redirect:/pictures";
	}
}
