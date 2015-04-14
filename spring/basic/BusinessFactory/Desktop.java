package spring.basic.BusinessFactory;

public class Desktop implements IComputer{
  private IDeviceWriter writer;
  private String SN;

  public String getSN() {
    return SN;
  }

  public void setSN(String sN) {
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
    System.out.print("Desktop #" + SN + " ");
    writer.saveToDevice();
  }

}
