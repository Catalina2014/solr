package com.atguigu.bean;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "table_pic")
public class Picture {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "group_name")
	private String groupName;

	@Column(name = "file_name")
	private String fileName;

	@Column(name = "pic_desc")
	private String picDesc;

	public Picture(Integer id, String groupName, String fileName, String picDesc) {
		super();
		this.id = id;
		this.groupName = groupName;
		this.fileName = fileName;
		this.picDesc = picDesc;
	}

	public Picture() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPicDesc() {
		return picDesc;
	}

	public void setPicDesc(String picDesc) {
		this.picDesc = picDesc;
	}

	@Override
	public String toString() {
		return "Picture [id=" + id + ", groupName=" + groupName + ", fileName=" + fileName + ", picDesc=" + picDesc
				+ "]";
	}

}
