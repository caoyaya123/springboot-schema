package com.controller;

import java.awt.geom.IllegalPathStateException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import cn.hutool.db.Page;
//import com.baomidou.mybatisplus.plugins.Page;
import com.entity.NoteEntity;
import com.entity.view.NoteView;
import com.entity.vo.NoteVO;
import com.service.NoteService;
import com.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.NewsEntity;
import com.entity.view.NewsView;

import com.service.NewsService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.CommonUtil;


/**
 * 新闻资讯
 * 后端接口
 *
 * @author
 * @email
 * @date 2020-05-13 10:02:23
 */
@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;
    @Autowired
    private NoteService noteService;


    /**
     * 后端列表
     * 新闻初始页面
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, NewsEntity news, HttpServletRequest request) {
        EntityWrapper<NewsEntity> ew = new EntityWrapper<NewsEntity>();
        PageUtils page = newsService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, news), params), params));
        return R.ok().put("data", page);
    }

    /**
     * 后端列表
     * 简历初始页面
     */
    @RequestMapping("/pageNote")
    public R pageNote(@RequestParam Map<String, Object> params, NoteEntity note, HttpServletRequest request) {
        EntityWrapper<NoteEntity> ew = new EntityWrapper<NoteEntity>();
        //param 前端封装的三个值
//        sqlSegment
        PageUtils page = noteService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, note), params), params));
        System.out.println("ew" + ew);
        System.out.println("page" + page);

        System.out.println(R.ok().put("data", page));
        return R.ok().put("data", page);
    }
    /**
     * 后端列表
     * 简历初始页面
     */
//    @RequestMapping("/pageNote/{pageIndex}/{pageSize}")
//    public R pageNote(@PathVariable int pageIndex, @PathVariable int pageSize, NoteVO note) {
//        Page<NoteEntity> page = new Page<>(pageIndex,pageSize);
//        Ipage<NoteEntity> result = noteService.selectPage(page , note);
//        Map<Object,Ipage> map = new HashMap<>();
//        map.put("map",map);
//        return R.ok().put(map);
//    }
    /**
     * 前端列表
     */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, NewsEntity news, HttpServletRequest request) {
        EntityWrapper<NewsEntity> ew = new EntityWrapper<NewsEntity>();
        PageUtils page = newsService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, news), params), params));
        return R.ok().put("data", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/lists")
    public R list(NewsEntity news) {
        EntityWrapper<NewsEntity> ew = new EntityWrapper<NewsEntity>();
        ew.allEq(MPUtil.allEQMapPre(news, "news"));
        return R.ok().put("data", newsService.selectListView(ew));
    }

    /**
     * 新闻查询
     */
    @RequestMapping("/query")
    public R query(NewsEntity news) {
        EntityWrapper<NewsEntity> ew = new EntityWrapper<NewsEntity>();
        ew.allEq(MPUtil.allEQMapPre(news, "news"));
        NewsView newsView = newsService.selectView(ew);
        return R.ok("查询新闻资讯成功").put("data", newsView);
    }

    /**
     * 简历查询
     */
    @RequestMapping("/query1")
    public R query1(NoteEntity note) {
        EntityWrapper<NoteEntity> ew = new EntityWrapper<NoteEntity>();
        ew.allEq(MPUtil.allEQMapPre(note, "note"));
        NoteView noteView = noteService.selectView(ew);
        return R.ok("查询新闻资讯成功").put("data", noteView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") String id) {
        NewsEntity news = newsService.selectById(id);
        return R.ok().put("data", news);
    }


    /**
     * 简历后端详情
     */
    @RequestMapping("/info1/{id}")
    public R info1(@PathVariable("id") String id) {
        NoteEntity note = noteService.selectById(id);
        return R.ok().put("data", note);
    }

    /**
     * 前端详情
     */
    @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") String id) {
        NewsEntity news = newsService.selectById(id);
        return R.ok().put("data", news);
    }

    /**
     * 简历前端详情
     */
    @IgnoreAuth
    @RequestMapping("/detail1/{id}")
    public R detail1(@PathVariable("id") String id) {
        NoteEntity note = noteService.selectById(id);
        return R.ok().put("data", note);
    }


    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody NewsEntity news, HttpServletRequest request) {
        news.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
        //ValidatorUtils.validateEntity(news);
        newsService.insert(news);
        return R.ok();
    }

    /**
     * 简历后端保存
     */
    @RequestMapping("/save1")
    public R save1(@RequestBody NoteEntity note, HttpServletRequest request) {
        note.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
        //ValidatorUtils.validateEntity(news);
        noteService.insert(note);
        return R.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody NewsEntity news, HttpServletRequest request) {
        news.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
        //ValidatorUtils.validateEntity(news);
        newsService.insert(news);
        return R.ok();
    }

    /**
     * 简历前端保存
     */
    @RequestMapping("/add1")
    public R add(@RequestBody NoteEntity note, HttpServletRequest request) {
        note.setId(new Date().getTime() + new Double(Math.floor(Math.random() * 1000)).longValue());
        //ValidatorUtils.validateEntity(news);
        noteService.insert(note);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody NewsEntity news, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(news);
        newsService.updateById(news);//全部更新
        return R.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update1")
    public R update1(@RequestBody NoteEntity note, HttpServletRequest request) {
        //ValidatorUtils.validateEntity(news);
        noteService.updateById(note);//全部更新
        return R.ok();
    }
    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        newsService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 简历删除
     */
    @RequestMapping("/delete1")
    public R delete1(@RequestBody Long[] ids) {
        noteService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 提醒接口
     */
    @RequestMapping("/remind/{columnName}/{type}")
    public R remindCount(@PathVariable("columnName") String columnName, HttpServletRequest request,
                         @PathVariable("type") String type, @RequestParam Map<String, Object> map) {
        map.put("column", columnName);
        map.put("type", type);

        if (type.equals("2")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            Date remindStartDate = null;
            Date remindEndDate = null;
            if (map.get("remindstart") != null) {
                Integer remindStart = Integer.parseInt(map.get("remindstart").toString());
                c.setTime(new Date());
                c.add(Calendar.DAY_OF_MONTH, remindStart);
                remindStartDate = c.getTime();
                map.put("remindstart", sdf.format(remindStartDate));
            }
            if (map.get("remindend") != null) {
                Integer remindEnd = Integer.parseInt(map.get("remindend").toString());
                c.setTime(new Date());
                c.add(Calendar.DAY_OF_MONTH, remindEnd);
                remindEndDate = c.getTime();
                map.put("remindend", sdf.format(remindEndDate));
            }
        }

        Wrapper<NewsEntity> wrapper = new EntityWrapper<NewsEntity>();
        if (map.get("remindstart") != null) {
            wrapper.ge(columnName, map.get("remindstart"));
        }
        if (map.get("remindend") != null) {
            wrapper.le(columnName, map.get("remindend"));
        }


        int count = newsService.selectCount(wrapper);
        return R.ok().put("count", count);
    }


}
