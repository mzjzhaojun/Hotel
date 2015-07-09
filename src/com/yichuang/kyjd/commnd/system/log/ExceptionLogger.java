package com.yichuang.kyjd.commnd.system.log;

import org.apache.log4j.Logger;
/**
 * 
 *
 * 
 * @author zjma
 *
 * 
 */
public class ExceptionLogger {

	private static Logger logger = Logger.getLogger(ExceptionLogger.class);
	/**
	 * 
	 * @Title: LoggerError
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param proxyName 代理对象名称
	 * @param @param className 对象名称
	 * @param @param methodName 方法名称
	 * @param @param arg 方法的参数
	 * @param @param errMsg 错误信息
	 * @param @param cause 错误原因
	 * @param @param statckTrace 堆栈信息
	 * @return void 返回类型
	 * @throws
	 */
	public static void LoggerError(String proxyName, String className,
			String methodName, String arg, String errMsg, String cause,
			String statckTrace) {
		StringBuilder info = new StringBuilder();
		info.append(proxyName);
		info.append("|");
		info.append(className);
		info.append("|");
		info.append(methodName);
		info.append("|");
		info.append(arg);
		info.append("|");
		info.append(errMsg);
		info.append("\n\t");
		info.append("cause===>>");
		info.append(cause);
		info.append("\n\t");
		info.append("statckTrace===>>");
		info.append(statckTrace);
		logger.error(info.toString());
	}

	/**
	 * 
	 * @Title: LoggerError
	 * @Description: TODO(记录其他异常信息)
	 * @param @param logger
	 * @param @param t 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public static void LoggerError(Throwable t) {
		logger.error(t);
	}

	/**
	 * 
	 * @Title: LoggerError
	 * @Description: TODO(记录其他异常信息)
	 * @param @param logger
	 * @param @param t 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public static void LoggerError(String message, Throwable t) {
		logger.error("====" + message
				+ "====" + t.getMessage());
	}

	/**
	 * 
	 * @Title: LoggerError
	 * @Description: TODO(记录异常堆栈)
	 * @param @param stackTrace 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public static void LoggerError(String stackTrace) {
		logger.error(stackTrace);
	}
}
