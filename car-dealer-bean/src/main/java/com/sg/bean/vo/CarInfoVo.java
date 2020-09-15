package com.sg.bean.vo;

import com.sg.bean.*;
import lombok.Data;

@Data
public class CarInfoVo {
    private CarBaseInfo carBaseInfo;

    private CarBody carBody;

    private CarChassis carChassis;

    private CarEngine carEngine;

    private CarGearbox carGearbox;
}
