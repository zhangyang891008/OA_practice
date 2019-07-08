package com.bigdata;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
 

public class WordCount {
	
	public static void main(String[] args) throws Exception {
		System.setProperty("HADOOP_USER_NAME","root");
		System.setProperty("HADOOP_USER_PASSWORD","891008");
		
		Configuration conf = new Configuration(true);
		conf.set("mapreduce.app-submission.cross-platform","true"); //解决windows跨平台时候不能执行命令bin/bash问题的
		
		Job job = Job.getInstance(conf);
		/**由于没有打包，程序不知道应该去哪里去反编译代码
		 * Error: java.lang.RuntimeException: java.lang.ClassNotFoundException: Class com.bigdata.MyMapper not found
				at org.apache.hadoop.conf.Configuration.getClass(Configuration.java:2154)
				at org.apache.hadoop.mapreduce.task.JobContextImpl.getMapperClass(JobContextImpl.java:186)
				at org.apache.hadoop.mapred.MapTask.runNewMapper(MapTask.java:742)
				at org.apache.hadoop.mapred.MapTask.run(MapTask.java:341)
				at org.apache.hadoop.mapred.YarnChild$2.run(YarnChild.java:163)
				at java.security.AccessController.doPrivileged(Native Method)
				at javax.security.auth.Subject.doAs(Subject.java:422)
				at org.apache.hadoop.security.UserGroupInformation.doAs(UserGroupInformation.java:1692)
				at org.apache.hadoop.mapred.YarnChild.main(YarnChild.java:158)
			Caused by: java.lang.ClassNotFoundException: Class com.bigdata.MyMapper not found
				at org.apache.hadoop.conf.Configuration.getClassByName(Configuration.java:2060)
				at org.apache.hadoop.conf.Configuration.getClass(Configuration.java:2152)
				... 8 more
		 */
		job.setJar("D:\\workspace\\spring-tool-suite\\HadoopProject\\target\\HadoopProject-0.0.1-SNAPSHOT.jar");
		
		job.setJarByClass(WordCount.class);
		job.setJobName("wordcount");
		
        Path infile = new Path("/data/wc/input");
        TextInputFormat.addInputPath(job,infile);

        Path outfile = new Path("/data/wc/output");
        if(outfile.getFileSystem(conf).exists(outfile))  
        	outfile.getFileSystem(conf).delete(outfile,true);
        
        TextOutputFormat.setOutputPath(job,outfile);
        
        job.setMapperClass(MyMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setReducerClass(MyReducer.class);
        
        job.waitForCompletion(true);

	}

}
