import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class csvReducer extends Reducer<Text,LongWritable, Text, LongWritable> {

	@Override
	public void reduce(Text key, Iterable<LongWritable> values,	Context context) throws IOException, InterruptedException {

		String country = key.toString();
		long count = 0;

		for (LongWritable val : values){
			count += val.get();
		}
        country = country + ": ";
        context.write(new Text(country), new LongWritable(count));
	}

}

