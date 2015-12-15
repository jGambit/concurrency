package com.github.jgambit.concurrency.util;

import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.scene.Cursor;
import javafx.scene.Scene;

public class SceneStack {

	class SceneBlocker {
		private final Scene scene;
		private final AtomicInteger blockCount;

		SceneBlocker(Scene toBlock) {
			scene = toBlock;
			blockCount = new AtomicInteger();
		}

		Scene getScene() {
			return scene;
		}

		int getBlockCount() {
			return blockCount.get();
		}

		void block() {
			blockCount.incrementAndGet();
			FxUtil.runAndWait(() -> scene.setCursor(Cursor.WAIT));
		}

		void unblock() {
			if (blockCount.decrementAndGet() == 0) {
				FxUtil.runAndWait(() -> scene.setCursor(Cursor.DEFAULT));
			}
		}
	}

	private final Stack<SceneBlocker> sceneStack;

	public SceneStack() {
		sceneStack = new Stack<>();
	}

	public void pushScene(Scene toBlock) {
		sceneStack.push(new SceneBlocker(toBlock));
	}

	public Scene popScene() {
		SceneBlocker popped = sceneStack.pop();
		if (popped.getBlockCount() > 0) {
			throw new IllegalStateException("BlockCount gt 0: " + popped.getBlockCount());
		}
		return popped.getScene();
	}

	public void blockScene() {
		SceneBlocker blocker = getCurrentBlocker();
		if (blocker != null) {
			blocker.block();
		}
	}

	public void unblockScene() {
		SceneBlocker blocker = getCurrentBlocker();
		if (blocker != null) {
			blocker.unblock();
		}
	}

	SceneBlocker getCurrentBlocker() {
		return sceneStack.peek();
	}

}
