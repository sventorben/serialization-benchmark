package de.sven_torben.serialization_benchmark;

class TestResult<T> {
	
	private long time;

	public long getTime()

	{
		return time;
	}

	public void setTime(final long time) {
		this.time = time;
	}

	private T data;

	public T getData() {
		return data;
	}

	public void setData(final T data) {
		this.data = data;
	}
}
