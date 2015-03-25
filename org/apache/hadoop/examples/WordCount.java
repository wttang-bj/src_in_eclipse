package org.apache.hadoop.examples;

import java.io.IOException;
import java.io.PrintStream;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class WordCount
{
  public static void main(String[] args)
    throws Exception
  {
    Configuration conf = new Configuration();
    String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs
();
    if (otherArgs.length != 2) {
      System.err.println("Usage: wordcount <in> <out>");
      System.exit(2);
    }
    Job job = new Job(conf, "word count");
    job.setJarByClass(WordCount.class);
    job.setMapperClass(TokenizerMapper.class);
    job.setCombinerClass(IntSumReducer.class);
    job.setReducerClass(IntSumReducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
    FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }

  public static class IntSumReducer extends Reducer<Text, IntWritable, Text, 
IntWritable>
  {
    private IntWritable result = new IntWritable();
    private static final Log logger = LogFactory.getLog(IntSumReducer.class
.getName());

    public void reduce(Text key, Iterable<IntWritable> values, Reducer<Text, 
IntWritable, Text, IntWritable>.Context context)
      throws IOException, InterruptedException
    {
      int sum = 0;
      StringBuilder value = new StringBuilder("<");
      boolean first = true;
      for (IntWritable val : values) {
        if (!first) {
          value.append(", ");
        } else {
          first = false;
        }
        sum += val.get();
        value.append(val.get());
      }
      value.append(">");
      this.result.set(sum);
      logger.info("Reduce: input K-V pair <" + key.toString() + ", " + value
      + " >, output K-V pair <" + key.toString() + ", " + this.result + " >");
      context.write(key, this.result);
    }
  }

  public static class TokenizerMapper extends Mapper<Object, Text, Text, 
IntWritable>
  {
    private static final Log logger = LogFactory.getLog(TokenizerMapper.
class.getName());

    private static final IntWritable one = new IntWritable(1);
    private Text word = new Text();
    private long count = 1L;
    private long line = 1L;

    public void map(Object key, Text value, Mapper<Object, Text, Text, 
IntWritable>.Context context)
      throws IOException, InterruptedException
    {
      StringTokenizer itr = new StringTokenizer(value.toString());
      logger.info("Map Input, Line#" + this.line + ": Key-value pair <" + 
        ((LongWritable)key).toString() + ", " + value.toString() + 
">.");
      this.line += 1L;
      while (itr.hasMoreTokens()) {
        this.word.set(itr.nextToken());
        logger.info("Map Output: " + this.word.toString());
        context.write(this.word, one);
      }
    }
  }
}