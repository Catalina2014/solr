package com.atguigu.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.bean.TieZi;
import com.atguigu.service.TieziService;

@Controller
@RequestMapping(value="/solr")
public class TieziController {
	
	@Autowired
	private TieziService tieziServie;
	
	/**
	 * 创建新帖,一方面需要存入数据库,一方面需要存入索引库
	 * @return
	 */
	@RequestMapping(value="/tiezi",method=RequestMethod.POST)
	public String saveTiezi(TieZi tiezi) {
		tieziServie.saveTiezi(tiezi);
		
		return "redirect:/index.jsp";
	}
	
	/**
	 * 查询帖子
	 * @return
	 */
	@RequestMapping(value="/tiezi",method=RequestMethod.GET)
	public String getTiezi(@RequestParam("keywords") String keywords,Model model) {
		System.out.println(keywords);
		Map<String, Map<String, String>> map = tieziServie.getTiezi(keywords);
		model.addAttribute("resultMap", map);
		return "result";
	}
	
	
	/**
	 * 查询帖子详情
	 * @return
	 */
	@RequestMapping(value="/tiezi/{id}")
	public String getTieziDetails(@PathVariable("id") Integer id,Model model) {
		TieZi tieZi = tieziServie.getTieziDetails(id);
		model.addAttribute("resultMap", tieZi);
		return "tiezi_details";
	}
	
	

}
