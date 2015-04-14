package spring.basic.BusinessFactory;

public class Laptop implements IComputer {
	private IDeviceWriter writer;
	private int SN;

	public int getSN() {
    return SN;
  }

  public void setSN(int sN) {
    SN = sN;
  }

  public void setDeviceWriter(IDeviceWriter writer) {
		this.writer = writer;
	}

	public IDeviceWriter getDeviceWriter() {
		return writer;
	}

	@Override
	public void save() {
		if (writer == null) {
			throw new RuntimeException("DeviceWriter needed...");
		}
		System.out.print("Laptop #" + SN + " ");
		writer.saveToDevice();
	}
}