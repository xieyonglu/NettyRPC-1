package com.newlandframework.test.common;

import org.apache.commons.lang3.time.StopWatch;

public class StopWatchTest {

	/**
	 * 主要方法: start(); //开始计时
	 * split(); //设置split点
	 * getSplitTime(); //获取从start 到 最后一次split的时间
	 * reset(); //重置计时
	 * suspend(); //暂停计时, 直到调用resume()后才恢复计时
	 * resume(); //恢复计时
	 * stop(); //停止计时
	 * getTime(); //统计从start到现在的计时
	 */
	public static void main(String[] args) throws InterruptedException {
		StopWatch watch = new StopWatch();
		watch.start();

		// 统计从start开始经历的时间
		Thread.sleep(1000);
		System.out.println(watch.getTime());

		// 统计计时点
		Thread.sleep(1000);
		watch.split();
		System.out.println(watch.getSplitTime());

		// 统计计时点
		Thread.sleep(1000);
		watch.split();
		System.out.println(watch.getSplitTime());

		// 复位后, 重新计时
		watch.reset();
		watch.start();
		Thread.sleep(1000);
		System.out.println(watch.getTime());

		// 暂停 与 恢复
		watch.suspend();
		System.out.println("暂停2秒钟");
		Thread.sleep(2000);

		watch.resume();
		Thread.sleep(1000);
		watch.stop();
		System.out.println(watch.getTime());
	}

}
