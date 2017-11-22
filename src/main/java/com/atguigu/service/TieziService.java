package com.atguigu.service;

import java.util.Map;

import com.atguigu.bean.TieZi;

public interface TieziService {

	void saveTiezi(TieZi tiezi);

	Map<String, Map<String, String>> getTiezi(String keywords);

	TieZi getTieziDetails(Integer id);

}
