package jin.chen.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class LoginInterceptor  implements HandlerInterceptor{


    private List<String> unCheckUrls;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String requestUrl = request.getRequestURI();//获取根路径到地址结尾
        requestUrl = requestUrl.replaceAll(request.getContextPath(), "");//获取项目的根路径
        // 判断是否针对匿名路径需要拦截，如果包含，则表示匿名路径，需要拦截，否则通过拦截器
        if (unCheckUrls.contains(requestUrl)){
            // 包含公开url，直接跳过
            return true;
        }
        if(null == request.getSession().getAttribute("sessionUser")){
            response.sendRedirect(request.getContextPath()+"/users/login.action");

            return false;
        }
        // 放行
        return true;
    }

    public List<String> getUnCheckUrls() {
        return unCheckUrls;
    }

    public void setUnCheckUrls(List<String> unCheckUrls) {
        this.unCheckUrls = unCheckUrls;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub

    }


}
