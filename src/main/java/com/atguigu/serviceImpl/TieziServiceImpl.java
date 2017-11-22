package com.atguigu.serviceImpl;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.bean.TieZi;
import com.atguigu.mapper.TieziMapper;
import com.atguigu.service.TieziService;

@Service
public class TieziServiceImpl implements TieziService{

	@Autowired
	private TieziMapper tieziMapper;
	
	@Autowired
	private SolrServer solrServer;
	
	//这应该是一个事务方法
	@Override
	@Transactional
	public void saveTiezi(TieZi tiezi) {
		//1.首先存入数据库
		tieziMapper.insertSelective(tiezi);
		
		//2.再存入索引库
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", tiezi.getId());
		document.addField("tiezi_title", tiezi.getTieziTitle());
		document.addField("tiezi_author", tiezi.getTieziAuthor());
		document.addField("tiezi_content", tiezi.getTieziContent());
		try {
			solrServer.add(document);
			solrServer.commit();
		} catch (SolrServerException | IOException e) {
			throw new RuntimeException();
		}
		
	}

	@Override
	public Map<String, Map<String, String>> getTiezi(String keywords) {
		
		//1.创建查询对象
		SolrQuery solrQuery = new SolrQuery();
		//2.添加要查询的关键词
		solrQuery.setQuery(keywords);
		//3.在多个字段内查询
		solrQuery.set("df", "keywords");
		
		//3.设置高亮模式
		solrQuery.setHighlight(true);
		//4.添加要高亮的字段
		solrQuery.addHighlightField("tiezi_title");
		solrQuery.addHighlightField("tiezi_author");
		solrQuery.addHighlightField("tiezi_content");
		
		//5.设置高亮效果
		solrQuery.setHighlightSimplePre("<span style=\"color:red\">");
		solrQuery.setHighlightSimplePost("</span>");
		
		Map<String, Map<String, String>> finalData = new HashMap<>();
		//6.执行查询
		try {
			QueryResponse response = solrServer.query(solrQuery);
			
			//7.处理查询结果:Map<文档id,Map<fieldName,fieldValue>>
			//第二个map封装的是文档id对应的数据
			
			//7.1 将全部的查询结果遍历出来,封装到Map中
			SolrDocumentList documentList = response.getResults();
			
			for (SolrDocument solrDocument : documentList) {
				//7.1.1 根据字段名id获取到document的id
				String id = (String) solrDocument.getFieldValue("id");
				Map<String, String> map = finalData.get(id);
				
				if (map == null) {
					//若map为null,则map没有被初始化,那么初始化map
					map = new HashMap<>();
					//需要将id与map对应起来,否则就不能够根据文档id取到相应文档的数据
					finalData.put(id, map);
				}
				
				//7.1.2 获取到document的字段名
				Collection<String> fieldNames = solrDocument.getFieldNames();
				//7.1.3 遍历字段名,根据字段名获取对应的值,放入finalData中
				for (String fieldName : fieldNames) {
					if ("_version_".equals(fieldName)) {
						continue;
					}
					String fieldValue = (String) solrDocument.getFieldValue(fieldName);
					map.put(fieldName, fieldValue);
				}
			}
			
			//7.2.1 获取高亮数据,debug得知Map<文档id,Map<fieldName,List<含有之前设置的高亮效果的fieldValue>>>
			Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
			
			//7.2.2用高亮的数据代替Map模型中非高亮的数据
			for (SolrDocument solrDocument : documentList) {
				//7.2.3 根据字段名id获取到document的id
				String id = (String) solrDocument.getFieldValue("id");
				//7.2.4 根据文档id获取到finalMap中,之前放入的没有高亮效果的整个文档数据
				Map<String, String> map = finalData.get(id);
				Map<String, List<String>> highlightingMap = highlighting.get(id);
				
				//7.1.2 获取到document的字段名
				Collection<String> fieldNames = solrDocument.getFieldNames();
				//7.1.3 遍历字段名,根据字段名获取对应的值,放入finalData中
				for (String fieldName : fieldNames) {
					List<String> list = highlightingMap.get(fieldName);
					//并不是每一个字段都有需要显示的高亮数据
					if (list != null && list.size() != 0) {
						//此时证明此文档,此字段有需要高亮显示的数据
						String fieldValue = list.get(0);
						//将此字段的数据替换为有高亮效果的数据
						map.put(fieldName, fieldValue);
					}
				}
			}
			
			
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		
		return finalData;
		
	}

	@Override
	public TieZi getTieziDetails(Integer id) {
		return tieziMapper.selectByPrimaryKey(id);
		
	}

}
