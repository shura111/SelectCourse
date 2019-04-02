package cn.ceh.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class loginFilter
 */
public class LoginFilter implements Filter {

	
	private FilterConfig config;
    /**
     * Default constructor. 
     */
    public LoginFilter() {

    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {

	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		HttpSession ses = request.getSession();
		String noLoginPaths = config.getInitParameter("noLoginPaths");
		if(noLoginPaths!=null){
			String[] strArray = noLoginPaths.split(";");
			for (int i = 0; i < strArray.length; i++) {
				
				if(strArray[i]==null || "".equals(strArray[i]))continue;
				
				if(request.getRequestURI().indexOf(strArray[i])!=-1 ){
					arg2.doFilter(arg0, arg1);
					return;
				}
			}
			
		}
		if(ses.getAttribute("SesUser")!=null){
			arg2.doFilter(arg0, arg1);
		}else{
			ses.setAttribute("Filter", "yes");
			response.sendRedirect("/Webtest/page/login/relay.jsp");
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		config = fConfig;
	}

}
