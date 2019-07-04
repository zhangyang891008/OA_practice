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
		Configuration conf = new Configuration(true);
		Job job = Job.getInstance(conf);
		
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
