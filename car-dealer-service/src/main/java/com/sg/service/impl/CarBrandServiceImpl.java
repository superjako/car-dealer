package com.sg.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sg.bean.CarBrand;
import com.sg.mapper.CarBrandMapper;
import com.sg.service.CarBrandService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 汽车品牌表 服务实现类
 * </p>
 *
 * @author sunpeng
 * @since 2020-10-15
 */
@Service
public class CarBrandServiceImpl extends ServiceImpl<CarBrandMapper, CarBrand> implements CarBrandService {

}
