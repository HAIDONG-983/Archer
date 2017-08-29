package com.ylink.wfms.exception;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import org.activiti.engine.ActivitiException;
import org.apache.avalon.framework.parameters.ParameterException;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 统一异常处理器
 * Created by yukunpeng on 2017/7/14.
 */
public class WfmsExceptionHandler implements HandlerExceptionResolver {
    private static Logger log = Logger.getLogger(WfmsExceptionHandler.class.getName());

    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object handler,
                                         Exception ex) {
        ModelAndView mv = new ModelAndView();
        Map<String, Object> attributes = new HashMap<String, Object>();
        attributes.put("MESSAGE", ex.getMessage() == null ? ex.toString() : ex.getMessage());
        attributes.put("STATUS", "ERROR");
        attributes.put("ERROR_REASON", ex.toString());
        attributes.put("RequestURL", request.getRequestURL());
        log.error("------------统一异常处理器拦截异常----------:",ex);
        if (request.getHeader("x-requested-with") != null) {//ajax请求
            FastJsonJsonView view = new FastJsonJsonView();
            if (ex instanceof WfmsException) {//流程平台自定义异常
                attributes.put("CODE", ((WfmsException) ex).getCode());
                attributes.put("MESSAGE", ((WfmsException) ex).getMessage());
            }
            view.setAttributesMap(attributes);
            mv.setView(view);
            return mv;
        } else {
            mv.setViewName("common/error");
            return mv;
        }
    }


}
