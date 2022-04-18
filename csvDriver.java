//hadoop jar Product.jar /input/input1/inputData1.csv /input/input2/inputData2
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class csvDriver {

	public static void main(String[] args) throws Exception
	{
		Configuration conf = new Configuration();
		String[] otherArgs = new GenericOptionsParser(conf,
								args).getRemainingArgs();

		// if less than two paths
		// provided will show error
		if (otherArgs.length < 4)
		{
			System.err.println("Error: please provide four paths");
			System.exit(2);
		}

		Job job = Job.getInstance(conf, "count countries from csv 1");
        Job job2 = Job.getInstance(conf,"count countries from csv 2");
		job.setJarByClass(csvDriver.class);
		job2.setJarByClass(csvDriver.class);

		job.setMapperClass(csvMapper.class);
		job2.setMapperClass(csvMapper.class);
		job.setReducerClass(csvReducer.class);
		job2.setReducerClass(csvReducer.class);

		job.setMapOutputKeyClass(Text.class);
		job2.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);
		job2.setMapOutputValueClass(LongWritable.class);

		job.setOutputKeyClass(LongWritable.class);
		job2.setOutputKeyClass(LongWritable.class);
		job.setOutputValueClass(Text.class);
		job2.setOutputValueClass(Text.class);

		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		FileInputFormat.addInputPath(job2, new Path(otherArgs[1]));

		FileOutputFormat.setOutputPath(job, new Path(otherArgs[2]));
		FileOutputFormat.setOutputPath(job2, new Path(otherArgs[3]));

        
        
        job.waitForCompletion(true);
        job2.waitForCompletion(true);
		//System.exit(job.waitForCompletion(true) ? 0 : 1);
  

	}
}

