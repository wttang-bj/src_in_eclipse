package Serialization;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableUtils;

public class WritablePerson extends BasePerson implements Writable{
  
  public WritablePerson() {
    super();
  }
  
  public WritablePerson(String name, int age, String sex) {
    super(name, age, sex);
  }
  
  @Override
  public void write(DataOutput out) throws IOException {
    WritableUtils.writeString(out, name);
    WritableUtils.writeVInt(out, age);
    WritableUtils.writeString(out, sex);
    
  }

  @Override
  public void readFields(DataInput in) throws IOException {
    name = WritableUtils.readString(in);
    age = WritableUtils.readVInt(in);
    sex = WritableUtils.readString(in);    
  }

}
