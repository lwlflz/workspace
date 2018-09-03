package com.build.cloud.modules.productplan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.build.cloud.modules.productplan.service.IProRotaWorkerService;

/**
 * <p>
 *   前端控制器
 * </p>
 * @author liangsen
 * @since 2018-05-03
 */
@Controller
@RequestMapping("/proRotaWorker")
public class ProRotaWorkerController {
    
    @Autowired private IProRotaWorkerService proRotaWorkerService;
    
}
