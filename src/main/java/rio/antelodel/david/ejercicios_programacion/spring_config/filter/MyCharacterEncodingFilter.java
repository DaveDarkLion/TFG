package rio.antelodel.david.ejercicios_programacion.spring_config.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyCharacterEncodingFilter implements Filter {
	
	@Override
    public void init(FilterConfig filterConfig) throws ServletException { }
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	
    	request.setCharacterEncoding(StandardCharsets.ISO_8859_1.toString());
        chain.doFilter(request, response);
        
    }
 
    @Override
    public void destroy() { }

}
