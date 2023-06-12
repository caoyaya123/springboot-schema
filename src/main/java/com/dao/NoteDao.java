package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entity.NewsEntity;
import com.entity.NoteEntity;
import com.entity.view.NewsView;
import com.entity.view.NoteView;
import com.entity.vo.NewsVO;
import com.entity.vo.NoteVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 新闻资讯
 * 
 * @author 
 * @email 
 * @date 2020-05-13 10:02:23
 */
public interface NoteDao extends BaseMapper<NoteEntity> {


    	List<NoteVO> selectListVO(@Param("ew") Wrapper<NoteEntity> wrapper);

	NoteVO selectVO(@Param("ew") Wrapper<NoteEntity> wrapper);

	List<NoteView> selectListView(@Param("ew") Wrapper<NoteEntity> wrapper);
//  111
	List<NoteView> selectListView(Pagination page, @Param("ew") Wrapper<NoteEntity> wrapper);
	
	NoteView selectView(@Param("ew") Wrapper<NoteEntity> wrapper);

//    Ipage<NoteEntity> selectPage(Page<NoteEntity> page, @Param("no") NoteVO note);
}
