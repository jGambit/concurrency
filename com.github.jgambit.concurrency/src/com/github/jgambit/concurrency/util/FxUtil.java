package com.github.jgambit.concurrency.util;

import java.util.concurrent.CountDownLatch;

import javafx.application.Platform;

public class FxUtil {

	public static void runAndWait(Runnable toRun) {
		if (Platform.isFxApplicationThread()) {
			toRun.run();
		} else {
			final CountDownLatch doneLatch = new CountDownLatch(1);
			Platform.runLater(() -> {
				try {
					toRun.run();
				} finally {
					doneLatch.countDown();
				}
			});
			try {
				doneLatch.await();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}

}
