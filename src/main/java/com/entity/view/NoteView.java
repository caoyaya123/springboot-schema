package com.entity.view;

import com.baomidou.mybatisplus.annotations.TableName;
import com.entity.NewsEntity;
import com.entity.NoteEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;


/**
 * 新闻资讯
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2020-05-13 10:02:23
 */
@TableName("note")
public class NoteView extends NoteEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public NoteView(){
	}

 	public NoteView(NoteEntity noteEntity){
 	try {
			BeanUtils.copyProperties(this, noteEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}
