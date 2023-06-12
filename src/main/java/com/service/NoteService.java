package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.entity.NewsEntity;
import com.entity.NoteEntity;
import com.entity.view.NewsView;
import com.entity.view.NoteView;
import com.entity.vo.NewsVO;
import com.entity.vo.NoteVO;
import com.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 新闻资讯
 *
 * @author 
 * @email 
 * @date 2020-05-13 10:02:23
 */
public interface NoteService extends IService<NoteEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<NoteVO> selectListVO(Wrapper<NoteEntity> wrapper);
   	
   	NoteVO selectVO(@Param("ew") Wrapper<NoteEntity> wrapper);
   	
   	List<NoteView> selectListView(Wrapper<NoteEntity> wrapper);
   	
   	NoteView selectView(@Param("ew") Wrapper<NoteEntity> wrapper);
   	//111111111111
   	PageUtils queryPage(Map<String, Object> params,Wrapper<NoteEntity> wrapper);

//    Ipage<NoteEntity> selectPage(Page<NoteEntity> page, NoteVO note);
}

