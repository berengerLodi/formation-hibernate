package fr.insee.tp.interceptor;

import org.apache.log4j.Logger;

import com.google.common.base.Stopwatch;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

@SuppressWarnings("serial")
public class StopwatchInterceptor implements Interceptor {

	private static final Logger logger = Logger.getLogger(StopwatchInterceptor.class);
	
	@Override
	public void destroy() {
		logger.info("destroy()");
	}

	@Override
	public void init() {
		logger.info("init()");
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		logger.info("intercept()");
		Stopwatch stopwatch = Stopwatch.createStarted();
		String result = invocation.invoke();
		stopwatch.stop();
		logger.info("Durée de l'action : " + stopwatch.toString());
		return result;
	}

}
