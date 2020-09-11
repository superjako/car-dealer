package com.sg.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sg.bean.CarEngine;
import com.sg.mapper.CarEngineMapper;
import com.sg.service.CarEngineService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 发动机信息表 服务实现类
 * </p>
 *
 * @author sunpeng
 * @since 2020-09-11
 */
@Service
public class CarEngineServiceImpl extends ServiceImpl<CarEngineMapper, CarEngine> implements CarEngineService {

}
