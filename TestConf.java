import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.compress.DefaultCodec;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapreduce.MRJobConfig;

import com.platform.mapreduce.util.HadoopAPIVersion;
import com.platform.mapreduce.work.adaptor.JobConfWrapper;


public class TestConf {

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    JobConf conf = new JobConf();
    conf.setBoolean(MRJobConfig.FRAMEWORK_CPP_ENAHNCE, true);
    HadoopAPIVersion.setVersion(HadoopAPIVersion.HADOOP_1_1_1);
    //conf.setCombinerClass(Reducer.class);
    
    conf.setBoolean("mapred.mapper.new-api", true);
   //conf.setClass("mapreduce.combine.class", Reducer.class, Reducer.class);
    //conf.setCompressMapOutput(true);
    //conf.setMapOutputCompressorClass(org.apache.hadoop.io.compress.Lz4Codec.class);
    
    System.out.println(JobConfWrapper.checkCppEnhanceEnabled(conf));

  }
  
  public static boolean isCppEnhanceEnabled (Configuration conf) {
    if (!conf.getBoolean(MRJobConfig.FRAMEWORK_CPP_ENAHNCE, false)) {
      System.out.println("MRJobConfig.FRAMEWORK_CPP_ENAHNCE, false");
      return false;
    }
    
    // Check Hadoop version
    String hadoopVersion = HadoopAPIVersion.getVersion();
    if (!hadoopVersion.equals(HadoopAPIVersion.HADOOP_1_1_1)) {
      System.out.println(" NOT support Hadoop version");
      return false;
    }
    
    // Check combiner
    boolean hasCombiner = false;
    if (conf.getBoolean("mapred.mapper.new-api", false) == true) {
      hasCombiner = (conf.getClass("mapreduce.combine.class", null) != null);
    } else {
      hasCombiner = (conf.getClass("mapred.combiner.class", null) != null);
    }
    
    if (hasCombiner) {
      System.out.println("NOT support Combiner");
      return false;
    }
    
    // Check Map Output compress Codec
    if (conf.getBoolean("mapred.compress.map.output", false)) {
      String codecClassName = conf.get("mapred.map.output.compression.codec");
      if (codecClassName == null // if not configure DefaultCodec.class will be used
          || !codecClassName.equals("org.apache.hadoop.io.compress.Lz4Codec")) {
        System.out.println("Unsupported Compress Codec: " + (codecClassName == null ? DefaultCodec.class.toString() : codecClassName));
        return false;
      }
    }
    
    return true;
  }

}
