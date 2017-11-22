package com.atguigu.bean;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="tiezi")
public class TieZi {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tiezi_id")
	private int id;
	
	@Column(name="tiezi_title")
	private String tieziTitle;
	
	@Column(name="tiezi_author")
	private String tieziAuthor;
	
	@Column(name="tiezi_content")
	private String tieziContent;

	public TieZi(int id, String tieziTitle, String tieziAuthor, String tieziContent) {
		this.id = id;
		this.tieziTitle = tieziTitle;
		this.tieziAuthor = tieziAuthor;
		this.tieziContent = tieziContent;
	}

	public TieZi() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTieziTitle() {
		return tieziTitle;
	}

	public void setTieziTitle(String tieziTitle) {
		this.tieziTitle = tieziTitle;
	}

	public String getTieziAuthor() {
		return tieziAuthor;
	}

	public void setTieziAuthor(String tieziAuthor) {
		this.tieziAuthor = tieziAuthor;
	}

	public String getTieziContent() {
		return tieziContent;
	}

	public void setTieziContent(String tieziContent) {
		this.tieziContent = tieziContent;
	}

	@Override
	public String toString() {
		return "TieZi [id=" + id + ", tieziTitle=" + tieziTitle + ", tieziAuthor=" + tieziAuthor + ", tieziContent="
				+ tieziContent + "]";
	}

}
