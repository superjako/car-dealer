package com.sg.api;


import com.alibaba.cloudapi.sdk.model.ApiCallback;
import com.alibaba.cloudapi.sdk.model.ApiRequest;
import com.alibaba.cloudapi.sdk.model.ApiResponse;
import com.alibaba.cloudapi.sdk.model.HttpClientBuilderParams;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.BaseController;
import com.sg.bean.CarBrand;
import com.sg.bean.Series;
import com.sg.bean.vo.CarBaseInfoVo;
import com.sg.common.HttpApiClientFinance_car;
import com.sg.constant.SystemConstant;
import com.sg.exception.BusinessException;
import com.sg.service.CarBaseInfoService;
import com.sg.service.CarBrandService;
import com.sg.service.SeriesService;
import com.sun.deploy.security.CertStore;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 车辆基本信息表 前端控制器
 * </p>
 *
 * @author sunpeng
 * @since 2020-09-11
 */
@Api(tags = "车辆管理")
@RestController
@RequestMapping("/api/carBaseInfo")
public class CarBaseInfoController extends BaseController {
    @Autowired
    CarBaseInfoService carBaseInfoService;

    @Autowired
    CarBrandService carBrandService;

    @Autowired
    SeriesService seriesService;

    /**
     * 分页查询数据
     *
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询接口")
    @GetMapping("/selectPageList")
    public R selectPageList(
            @RequestParam(required = false) String brandId,
            @RequestParam(required = false) String shopId,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Integer startPrice,
            @RequestParam(required = false) Integer endPrice,
            @RequestParam(required = false) Integer startFirstPay,
            @RequestParam(required = false) Integer endFirstPay,
            @RequestParam(required = false) Integer pageNum,
            @RequestParam(required = false) Integer pageSize) {
        if (pageNum == null) pageNum = SystemConstant.PAGE_NUM;
        if (pageSize == null) pageSize = SystemConstant.PAGE_SIZE;
        return R.ok(carBaseInfoService.selectCarInfoPage(new Page<>(pageNum, pageSize), brandId, shopId, type, startPrice, endPrice, startFirstPay, endFirstPay));
    }

    @ApiOperation(value = "发布和编辑车辆信息")
    @PostMapping("/saveCarInfo")
    public R saveCarInfo(@ApiParam(value = "车辆信息", required = true) @RequestBody CarBaseInfoVo carBaseInfo) throws BusinessException {
        carBaseInfoService.saveInfo(carBaseInfo, "");
        return R.ok("");
    }

    @ApiOperation(value = "删除车辆信息")
    @DeleteMapping("/deleteInfo")
    public R deleteInfo(@ApiParam(value = "车辆信息id", required = true) @RequestParam String id) throws Exception {
        carBaseInfoService.deleteInfo(id, "");
        return R.ok("");
    }

    @ApiOperation(value = "车辆详情信息")
    @GetMapping("/detail")
    public R detail(@ApiParam(value = "车辆信息id", required = true) @RequestParam String id) throws Exception {
        CarBaseInfoVo detail = carBaseInfoService.detail(id);
        return R.ok(detail);
    }

    @ApiOperation(value = "导入车辆品牌")
    @GetMapping("/importCarBrand")
    public R importCarBrand() throws Exception {
        /* HttpClientBuilderParams httpParam = new HttpClientBuilderParams();
        httpParam.setAppKey("203867310");
        httpParam.setAppSecret("R0m2tWsfE7OlN0HMqJFKXJJ2FBkSb4NA");
        HttpApiClientFinance_car.getInstance().init(httpParam);*/


        //HTTPS Client init
        HttpClientBuilderParams httpsParam = new HttpClientBuilderParams();
        httpsParam.setAppKey("203867310");
        httpsParam.setAppSecret("R0m2tWsfE7OlN0HMqJFKXJJ2FBkSb4NA");
        HttpApiClientFinance_car.getInstance().init(httpsParam);

        HttpApiClientFinance_car.getInstance().汽车品牌列表_提速版(new ApiCallback() {
            @Override
            public void onFailure(ApiRequest apiRequest, Exception e) {
                System.out.println(e);
            }

            @Override
            public void onResponse(ApiRequest apiRequest, ApiResponse apiResponse) {
                System.out.println(apiResponse.toString());
            }
        });

        ApiResponse apiResponse = HttpApiClientFinance_car.getInstance().汽车品牌列表_提速版SyncMode();
        //ApiResponse apiResponse1 = HttpApiClientFinance_car.getInstance().汽车品牌配置详情_提速版SyncMode("4131");
        System.out.println(apiResponse);
        //String a = new String(apiResponse1.getBody());
        String b = new String(apiResponse.getBody());
        JSONObject jo = JSONObject.parseObject(b);
        Object object = jo.get("brandList");
        JSONArray jsonArr = JSONArray.parseArray(object.toString());
        for (Object o : jsonArr) {
            JSONObject jsonObject = JSONObject.parseObject(o.toString());
            CarBrand carBrand = new CarBrand();
            carBrand.setBrandLogo(jsonObject.get("imgUrl").toString());
            carBrand.setBrandName(jsonObject.get("brandName").toString());
            carBrand.setFirstLetter(jsonObject.get("letter").toString());
            carBrand.setId(jsonObject.get("id").toString());
            carBrand.setCreater("sp");
            carBrandService.save(carBrand);
        }
        //System.out.println(apiResponse1.getBody());
       /* String host = "https://autocars.market.alicloudapi.com";
        String path = "/carNewList";
        String method = "POST";
        String appcode = "2a264f1ef8e34444aafb06e823ce5ed2";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE" + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys =null;
        Map<String, String> bodys = new HashMap<String, String>();
        bodys= null;*/


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            /*HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());*/
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.ok("");
    }

    @ApiOperation(value = "根据品牌导入车系")
    @GetMapping("/importCarSeries")
    public R importCarSeries() throws Exception {
        //HTTPS Client init
        HttpClientBuilderParams httpsParam = new HttpClientBuilderParams();
        httpsParam.setAppKey("203867310");
        httpsParam.setAppSecret("R0m2tWsfE7OlN0HMqJFKXJJ2FBkSb4NA");
        HttpApiClientFinance_car.getInstance().init(httpsParam);

        HttpApiClientFinance_car.getInstance().汽车品牌列表_提速版(new ApiCallback() {
            @Override
            public void onFailure(ApiRequest apiRequest, Exception e) {
                System.out.println(e);
            }

            @Override
            public void onResponse(ApiRequest apiRequest, ApiResponse apiResponse) {
                System.out.println(apiResponse.toString());
            }
        });

        List<CarBrand> carBrandList = carBrandService.list();

        for (CarBrand carBrand : carBrandList) {
            ApiResponse apiResponse1 = HttpApiClientFinance_car.getInstance().汽车品牌配置详情_提速版SyncMode(carBrand.getId());
            String a = new String(apiResponse1.getBody());

            JSONObject jo = JSONObject.parseObject(a);
            Object object = jo.get("cars");
            JSONArray jsonObject1 = JSONArray.parseArray(object.toString());
            for (Object o : jsonObject1) {
                JSONObject object1=  JSONObject.parseObject(o.toString());

                JSONArray jsonArr = JSONArray.parseArray(object1.getString("typeList"));

                for (Object p : jsonArr) {
                    JSONObject jsonObject = JSONObject.parseObject(p.toString());
                    Series series = new Series();
                    series.setBrandId(carBrand.getId());
                    series.setName(jsonObject.get("typeName").toString());
                    series.setCreater("sp");
                    seriesService.save(series);
                }
            }

        }
        return R.ok("");
    }

    //
    public static void main(String[] args) {
       /* HttpClientBuilderParams httpParam = new HttpClientBuilderParams();
        httpParam.setAppKey("203867310");
        httpParam.setAppSecret("R0m2tWsfE7OlN0HMqJFKXJJ2FBkSb4NA");
        HttpApiClientFinance_car.getInstance().init(httpParam);*/


        //HTTPS Client init
        HttpClientBuilderParams httpsParam = new HttpClientBuilderParams();
        httpsParam.setAppKey("203867310");
        httpsParam.setAppSecret("R0m2tWsfE7OlN0HMqJFKXJJ2FBkSb4NA");
        HttpApiClientFinance_car.getInstance().init(httpsParam);

        HttpApiClientFinance_car.getInstance().汽车品牌列表_提速版(new ApiCallback() {
            @Override
            public void onFailure(ApiRequest apiRequest, Exception e) {
                System.out.println(e);
            }

            @Override
            public void onResponse(ApiRequest apiRequest, ApiResponse apiResponse) {
                System.out.println(apiResponse.toString());
            }
        });

        ApiResponse apiResponse = HttpApiClientFinance_car.getInstance().汽车品牌列表_提速版SyncMode();
        //ApiResponse apiResponse1 = HttpApiClientFinance_car.getInstance().汽车品牌配置详情_提速版SyncMode("4131");
        System.out.println(apiResponse);
        //String a = new String(apiResponse1.getBody());
        String b = new String(apiResponse.getBody());
        JSONObject jo = JSONObject.parseObject(b);
        Object object = jo.get("brandList");
        JSONArray jsonArr = JSONArray.parseArray(object.toString());
        for (Object o : jsonArr) {
            JSONObject jsonObject = JSONObject.parseObject(o.toString());
            CarBrand carBrand = new CarBrand();
            carBrand.setBrandLogo(jsonObject.get("imgUrl").toString());
            carBrand.setBrandName(jsonObject.get("brandName").toString());
            carBrand.setFirstLetter(jsonObject.get("letter").toString());
            carBrand.setId(jsonObject.get("id").toString());
            //carBrandService.save(carBrand);
        }
        //System.out.println(apiResponse1.getBody());
       /* String host = "https://autocars.market.alicloudapi.com";
        String path = "/carNewList";
        String method = "POST";
        String appcode = "2a264f1ef8e34444aafb06e823ce5ed2";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE" + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys =null;
        Map<String, String> bodys = new HashMap<String, String>();
        bodys= null;*/


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            /*HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());*/
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
