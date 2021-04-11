package com.mine.violet.service.impl;

import com.mine.violet.entity.BlogCategory;
import com.mine.violet.entity.BlogTag;
import com.mine.violet.mapper.BlogTagMapper;
import com.mine.violet.service.BlogTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author violet
 * @since 2020-08-11
 */
@Transactional
@Service
public class BlogTagServiceImpl extends ServiceImpl<BlogTagMapper, BlogTag> implements BlogTagService {

    @Override
    public void deleteById(String tagId) {
        BlogTag blogTag = baseMapper.selectById(tagId);
        int deleteId=0;
        if(blogTag.getIsDelete()==0){
            deleteId=1;
            baseMapper.updateId(tagId,deleteId);
        }else{
            deleteId=0;
            baseMapper.updateId(tagId,deleteId);
        }
    }
}
