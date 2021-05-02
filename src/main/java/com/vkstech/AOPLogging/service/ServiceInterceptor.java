package com.vkstech.AOPLogging.service;

import com.vkstech.AOPLogging.DemoRepository;
import com.vkstech.AOPLogging.annotation.NoLogging;
import com.vkstech.AOPLogging.dto.DemoContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;

@NoLogging
@Service
public class ServiceInterceptor implements HandlerInterceptor {

    @Autowired
    private DemoRepository demoRepository;

    @Resource(name = "demoContext")
    private DemoContext demoContext;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Pre Handle method is Calling");
        // TODO Fix Warning
        LinkedHashMap<String, String> pathVariables =
                (LinkedHashMap<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        String id = pathVariables.get("id");
        if (id != null)
            demoContext.setId(id);
        demoContext.setName(demoRepository.getName());
        return true;
    }


//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response,
//                           Object handler, ModelAndView modelAndView) throws Exception {
//        System.out.println("Post Handle method is Calling");
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
//                                Object handler, Exception exception) throws Exception {
//        System.out.println("Request and Response is completed");
//    }
}
