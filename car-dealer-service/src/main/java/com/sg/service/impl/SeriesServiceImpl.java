package com.sg.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sg.bean.Series;
import com.sg.mapper.SeriesMapper;
import com.sg.service.SeriesService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 品牌对应车系表 服务实现类
 * </p>
 *
 * @author sunpeng
 * @since 2020-10-19
 */
@Service
public class SeriesServiceImpl extends ServiceImpl<SeriesMapper, Series> implements SeriesService {

}
