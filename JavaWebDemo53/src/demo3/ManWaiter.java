package demo3;

public class ManWaiter implements Waiter {
	@Override
	public void service() {
		System.out.println("服务中...");
	}

	@Override
	public void shouQian() {
		System.out.println("坟蛋，给我钱！");
	}

}
