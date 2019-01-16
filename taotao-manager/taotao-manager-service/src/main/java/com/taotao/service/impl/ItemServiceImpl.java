package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.mapper.ItemMapper;
import com.taotao.pojo.Item;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
        //1.设置分页的信息 使用pagehelper
        if(page==null) page=1;
        if(rows==null) rows=30;
        System.out.println("xiaoma/page: "+page+" rows: "+rows);
        PageHelper.startPage(page, rows);

        //4.根据mapper调用查询所有数据的方法
        List<Item> list = itemMapper.queryItemList();
        System.out.println("xiaoma/"+list.size());
        System.out.println("xiaoma/"+list.get(0));
        //5.获取分页的信息
        PageInfo<Item> info = new PageInfo<>(list);
        //6.封装到EasyUIDataGridResult
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setTotal((int)info.getTotal());
        result.setRows(info.getList());
        System.out.println("xiaoma/res/"+info.getTotal());
        //7.返回
        return result;
    }
}
