package org.gameprototype.tool.impl;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import org.apache.log4j.Logger;
import org.gameprototype.tool.IExecutorPool;

public class ExecutorPool implements IExecutorPool {

	private UncaughtExceptionHandler exceptionHandler = new UncaughtExceptionHandler() {
		private final Logger logger = Logger.getLogger(getClass());

		@Override
		public void uncaughtException(Thread t, Throwable e) {
			logger.error(t.toString(), e);
		}
	};
	private ExecutorService server;
	public ExecutorPool(final String name){
		server = Executors
		.newSingleThreadExecutor(new ThreadFactory() {

			@Override
			public Thread newThread(Runnable r) {
				Thread t = new Thread(r, name);
				if (t.isDaemon())
					t.setDaemon(false);
				if (t.getPriority() != Thread.NORM_PRIORITY)
					t.setPriority(Thread.NORM_PRIORITY);
				t.setUncaughtExceptionHandler(exceptionHandler);
				return t;
			}
		});
	}

	@Override
	public void execute(Runnable task) {
		server.execute(task);
	}

}
