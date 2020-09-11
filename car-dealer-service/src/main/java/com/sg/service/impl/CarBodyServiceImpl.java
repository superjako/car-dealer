package com.sg.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sg.bean.CarBody;
import com.sg.mapper.CarBodyMapper;
import com.sg.service.CarBodyService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 车身信息表 服务实现类
 * </p>
 *
 * @author sunpeng
 * @since 2020-09-11
 */
@Service
public class CarBodyServiceImpl extends ServiceImpl<CarBodyMapper, CarBody> implements CarBodyService {

}
