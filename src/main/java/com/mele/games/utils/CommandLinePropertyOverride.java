package com.mele.games.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * For now this class supports only setting string properties on top-level bean objects. For example,
 * 		<code>Target.sourceFile=./some/source/file.txt</code> 
 * will result in the setSoruceFile() method of bean Target being called with the parameter "./some/source/file.txt".
 * 
 * With some more work this could handle nested objects (Target.myObject.myProperty), and property
 * types other than String.  But for now, it is minimalist!
 * 
 * @author Al
 *
 */
public class CommandLinePropertyOverride implements BeanFactoryPostProcessor {
	protected static Logger log = Logger
			.getLogger(CommandLinePropertyOverride.class);

	static public String REGEX_PERIOD = "\\.";
	static public String SETTER_PREFIX = "set";
	
	static private String[] argumentList = null;
	
	/**
	 * 
	 */
	public CommandLinePropertyOverride() {
		log.debug("Initializing CommandLinePropertyOverride...");
	}
	
	/**
	 * @param args
	 */
	public CommandLinePropertyOverride(String[] args) {
		log.debug("Initializing CommandLinePropertyOverride with arguments...");
		argumentList = args;
	}
	
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		log.info("Overriding beans with commandline specified properties...");

		if (argumentList != null) {
			for (int i = 0; i < argumentList.length; i++) {
				String argline = argumentList[i];
				String bean = getBeanFromArgline(argline);
				String method = getMethodFromArgline(argline);
				method = makeSetterString(method);
				String value = getValueFromArgline(argline);
				
				log.debug(argline + ": Bean='" + bean + "', Method='" + method + "', Value='" + value + "'");
				
				try {
					Object editBean = beanFactory.getBean(bean);
					
					Method mList[] = editBean.getClass().getMethods();
					for (int j = 0; j < mList.length; j++) {
						Method editMethod = mList[j];
						if (editMethod.getName().startsWith(SETTER_PREFIX) 
								&& editMethod.getName().equals(method)
								&& editMethod.getParameterTypes().length == 1
						) {
							log.info("Setting property using " + editMethod.getName() + " to value: " + value);
							Class<?> parmType = editMethod.getParameterTypes()[0];
							
							// Translate string-based arguments if needed.
							if (parmType.equals(boolean.class)) {
								boolean val = ("true".equals(value) ? true : false);
								editMethod.invoke(editBean, val);
							} else {
								editMethod.invoke(editBean, value);
							}
							
							break;
						}
					}
				// Intentionally eat all of these exceptions... inappropriately specified 
				// command line parameters will be ignored rather than cause errors.
				} catch (NoSuchBeanDefinitionException e) {
					log.error("No bean found named '" + bean + "'");				
				} catch (SecurityException e) {
					log.error(GameException.fullExceptionInfo(e));
				} catch (IllegalArgumentException e) {
					log.error(GameException.fullExceptionInfo(e));
				} catch (IllegalAccessException e) {
					log.error(GameException.fullExceptionInfo(e));
				} catch (InvocationTargetException e) {
					log.error(GameException.fullExceptionInfo(e));	
				}
			
			}
		}
	}
	
	/**
	 * @param argline
	 * @return
	 */
	protected String getBeanFromArgline(String argline) {
		String bean = null;
		String[] propvalPair = argline.split("=");
		String[] beanMethodPair = propvalPair[0].split(REGEX_PERIOD);
		bean = beanMethodPair[0];
		
		return bean;
	}
	
	/**
	 * @param argline
	 * @return
	 */
	protected String getMethodFromArgline(String argline) {
		String method = null;
		String[] propvalPair = argline.split("=");
		String[] beanMethodPair = propvalPair[0].split(REGEX_PERIOD);
		method = beanMethodPair[1];
		
		return method;
	}	
	
	/**
	 * @param argline
	 * @return
	 */
	protected String getValueFromArgline(String argline) {
		String value = null;
		String[] propvalPair = argline.split("=");
		value = propvalPair[1];
		
		return value;
	}		
	
	/**
	 *  Transform unqualified property name to setter method name.
	 *  
	 * @param methodName
	 * @return
	 */
	protected String makeSetterString(String methodName) {
		String setterString = SETTER_PREFIX;
		setterString = setterString + methodName.substring(0, 1).toUpperCase();
		setterString = setterString + methodName.substring(1, methodName.length());
		return setterString;
	}

}
