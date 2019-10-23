package com.rodriguez.springboot.app.zuul.filters;

import javax.servlet.http.HttpServletRequest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PreTiempoTranscurridoFilter extends ZuulFilter {
	
	private static Logger log  =  LoggerFactory.getLogger(PreTiempoTranscurridoFilter.class);

	@Override
	public boolean shouldFilter() {
		
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		
		
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		log.info(String.format(" Entrando a post filter"));
		Long tiempoInicio = (Long)request.getAttribute("tiempoInicio");
		Long tiempoFinal = System.currentTimeMillis();
		Long tiempoTransacurrido = tiempoFinal - tiempoInicio;
		
		log.info(String.format("tiempo transcurrido en seguros %s seg .", tiempoTransacurrido.doubleValue()/1000.00));
		log.info(String.format("tiempo transcurrido en mileseg %s mills .", tiempoTransacurrido));
		request.setAttribute("tiempoInicio", tiempoInicio);
	
		return null;
	}

	@Override
	public String filterType() {
		
		return "post";
	}

	@Override
	public int filterOrder() {
		
		return 1;
	}

}
