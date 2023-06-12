package com.entity.vo;

import java.io.Serializable;


/**
 * 简历
 */
public class NoteVO implements Serializable {
	private static final long serialVersionUID = 1L;
//	private int pageIndex;
//	private int pageSize;
	private Long id;

	private String name;
	private String note;

//	public int getPageIndex() {
//		return pageIndex;
//	}
//
//	public void setPageIndex(int pageIndex) {
//		this.pageIndex = pageIndex;
//	}
//
//	public int getPageSize() {
//		return pageSize;
//	}
//
//	public void setPageSize(int pageSize) {
//		this.pageSize = pageSize;
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}