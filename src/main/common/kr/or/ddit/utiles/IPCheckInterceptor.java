package kr.or.ddit.utiles;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class IPCheckInterceptor extends HandlerInterceptorAdapter {

   private static Map<String, String> ipMap;
   
   static{
      ipMap = new HashMap<String, String>();
      ipMap.put("127.0.0.1", "A");
      ipMap.put("0:0:0:0:0:0:0:1", "A");
      ipMap.put("192.168.206.16", "A");
      ipMap.put("192.168.206.23","F");
   }
   
   @Override
   public void afterCompletion(HttpServletRequest request,
         HttpServletResponse response, Object handler, Exception ex)
         throws Exception {
   }

   @Override
   public void afterConcurrentHandlingStarted(HttpServletRequest request,
                                    HttpServletResponse response, 
                                    Object handler) throws Exception {
   }

   @Override
   public void postHandle(HttpServletRequest request,
         HttpServletResponse response, Object handler,
         ModelAndView modelAndView) throws Exception {
   }

   @Override
   public boolean preHandle(HttpServletRequest request,
         HttpServletResponse response, Object handler) throws Exception {
      
      String clientIP = request.getRemoteAddr();
      boolean flag = true;
      
      if(StringUtils.isNotEmpty(clientIP) && 
            ipMap.containsKey(clientIP)){
         
         if("A".intern() == ipMap.get(clientIP).intern()){
         }else{
            flag = false;
         }
         
      }else{
         flag = false;
      }
      if(!flag){
         response.setCharacterEncoding("UTF-8");
         response.setContentType("text/html; charset=UTF-8");
         
         PrintWriter out = response.getWriter();
         out.println("<html>");
         out.println("<body>");
         out.println("<font color = 'red'> 꺼져 </font>");
         out.println("</body>");
         out.println("</html>");
      }
      
      return flag;
      
   }

   

}