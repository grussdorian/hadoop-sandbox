import java.io.*;
import java.util.*;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Mapper;

public class csvMapper extends
    Mapper<LongWritable, Text, Text, LongWritable> {

    public void map(LongWritable key, Text value, Context context)
        throws IOException, InterruptedException {

    //Framework does the reading for you...
    String line = value.toString();      //line contains one line of your csv file.
    //System.out.println("!! DEBUG !!" + line);
    String fields[] = line.split(",");
    System.out.println("!!!DEBUGG!!!   " + fields[7]);
    //do your processing here
    
    context.write(new Text(fields[7]), new LongWritable(1));
    }
}