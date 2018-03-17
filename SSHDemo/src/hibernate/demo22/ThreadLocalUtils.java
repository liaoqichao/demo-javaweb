package hibernate.demo22;

import org.hibernate.Session;

public class ThreadLocalUtils {
	private static ThreadLocal<Session> threadlocale = new ThreadLocal<Session>();

	public static ThreadLocal<Session> getThreadlocale() {
		return threadlocale;
	}

	public static void setThreadlocale(ThreadLocal<Session> threadlocale) {
		ThreadLocalUtils.threadlocale = threadlocale;
	}
	
	
}
