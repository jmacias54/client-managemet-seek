package com.pe.client_management.config;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

import static java.util.Objects.nonNull;


@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {
	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);


	/**
	 * Pointcut that matches all repositories, services and Web REST endpoints.
	 **/

	@Pointcut("within(@org.springframework.stereotype.Repository *)"
		+ " || within(@org.springframework.stereotype.Service *)"
		+ " || within(@org.springframework.web.bind.annotation.RestController *)")
	public void springBeanPointcut() {

	}


	/**
	 * Pointcut that matches all Spring beans in the application's main packages.
	 */
	@Pointcut("within(com.pe.client_management.service..*) "
		+ " || within(com.pe.client_management.controller..*)")
	public void applicationPackagePointcut() {

	}


	/**
	 * Advice that logs methods throwing exceptions.
	 *
	 * @param joinPoint join point for advice
	 * @param e         exception
	 */
	@AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
		logger.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
			joinPoint.getSignature().getName(), nonNull(e.getCause()) ? e.getCause() : "NULL"
		);
	}

	/**
	 * Advice that logs when a method is entered and exited.
	 *
	 * @param joinPoint join point for advice
	 * @return result
	 * @throws Throwable throws IllegalArgumentException
	 */
	@Around("applicationPackagePointcut() ")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

		this.printLogEnter(joinPoint);
		try {
			Object result = joinPoint.proceed();
			MethodSignature signature = (MethodSignature)joinPoint.getSignature();
			Method method = signature.getMethod();

			//	this.requestLoggerCreateUseCase.execute(method, joinPoint, result);
			this.printLogExit(joinPoint, result);

			return result;
		} catch(IllegalArgumentException e) {
			logger.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
				joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName()
			);
			throw e;
		}
	}

	/* valida que y cuando imprimir en el log , ya que evita que imprima info las operaciones de las clases
	 * mapper , validator y creator */
	private void printLogEnter(ProceedingJoinPoint joinPoint) {

		var args = joinPoint.getArgs();

		if(logger.isInfoEnabled() && this.isPrintable(joinPoint)) {

				logger.info("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
					joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs())
				);

		}
		else if(logger.isDebugEnabled() && this.isPrintable(joinPoint)) {
			logger.debug("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs())
			);
		}
	}

	/* valida que y cuando imprimir en el log , ya que evita que imprima info las operaciones de las clases
	 * mapper , validator y creator */
	private void printLogExit(ProceedingJoinPoint joinPoint, Object result) {
		if(logger.isInfoEnabled() && this.isPrintable(joinPoint)) {

				logger.info("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
					joinPoint.getSignature().getName(), result
				);

		}
		else if(logger.isDebugEnabled() && this.isPrintable(joinPoint)) {
			logger.debug("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().getName(), result
			);
		}
	}


	private String getClassName(ProceedingJoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature)joinPoint.getSignature();
		Method method = signature.getMethod();
		var clazz = method.getDeclaringClass();
		var className = clazz.getSimpleName();
		return className;
	}

	private boolean isPrintable(ProceedingJoinPoint joinPoint) {
		var className = this.getClassName(joinPoint);
		var pkge = joinPoint.getSignature().getDeclaringTypeName();
		return !className.contains("Mapper")
			&& !className.contains("Validator")
			&& !className.contains("Creator")
			&& !pkge.contains("validator")
			&& !pkge.contains("creator")
			&& !pkge.contains("mapper");
	}
}
