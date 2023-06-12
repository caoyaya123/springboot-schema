package com.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.NewsDao;
import com.dao.NoteDao;
import com.entity.NewsEntity;
import com.entity.NoteEntity;
import com.entity.view.NewsView;
import com.entity.view.NoteView;
import com.entity.vo.NewsVO;
import com.entity.vo.NoteVO;
import com.service.NewsService;
import com.service.NoteService;
import com.utils.PageUtils;
import com.utils.Query;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@Service("noteService")
public class NoteServiceImpl extends ServiceImpl<NoteDao, NoteEntity> implements NoteService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<NoteEntity> page = this.selectPage(
                new Query<NoteEntity>(params).getPage(),
                new EntityWrapper<NoteEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<NoteEntity> wrapper) {
		  Page<NoteView> page =new Query<NoteView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

//    @Override
//    public Ipage<NoteEntity> selectPage(Page<NoteEntity> page, NoteVO note) {
//        return baseMapper.selectPage(page, note);
//    }


    @Override
	public List<NoteVO> selectListVO(Wrapper<NoteEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}

	@Override
	public NoteVO selectVO(Wrapper<NoteEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}

	@Override
	public List<NoteView> selectListView(Wrapper<NoteEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public NoteView selectView(Wrapper<NoteEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

}
