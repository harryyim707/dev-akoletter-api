package akoletter.devakoletterapi.util.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class corsConfig {

    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.addExposedHeader("Authorization");
        config.addExposedHeader("refreshToken");
        config.addAllowedOrigin("https://nojaewon.github.io/");
        source.registerCorsConfiguration("/**",config);
        return new CorsFilter(source);
    }

/*
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Method","POST,GET,OPTIONS,DELETE, PUT");
	    response.setHeader("Access-Control-Max-Age","3600");
	    response.setHeader("Access-Control-Allow-Headers","Content-Type,x-requested-with,Authorization,Axxess-Control-Allow-Origin");
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {}*/
}
