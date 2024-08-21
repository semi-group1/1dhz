package semi.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import semi.model.AdminUser;
import semi.model.AdminUserRole;

public class AuthCheckInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		boolean authorized = false;
		if (session != null) {
			AdminUser loginUser = (AdminUser) session.getAttribute("loginUser");
			if (loginUser != null && loginUser.getUserRole().equals(AdminUserRole.admin)) {
				authorized = true;
			}
		}

		response.sendRedirect(request.getContextPath() + "/main");
		return authorized;
	}
}
