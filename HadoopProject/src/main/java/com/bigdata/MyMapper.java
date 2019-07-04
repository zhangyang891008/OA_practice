package com.bigdata;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MyMapper extends Mapper<Object, Text, Text, IntWritable>{
	
	private static final IntWritable one = new IntWritable(1);
	private Text word = new Text(); 

	@Override
	protected void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {
		StringTokenizer tokens = new StringTokenizer(value.toString());
		while (tokens.hasMoreElements()) {
			word.set(tokens.nextToken());
			context.write(word, one);
		}		 
	}

}
