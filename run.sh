#/Library/Java/JavaVirtualMachines/jdk1.8.0_321.jdk/Contents/Home/bin/javac
javac -d . *.java  -cp $(hadoop classpath)
jar cfm Product.jar manifest.txt *.class
hadoop jar Product.jar /input/input1/inputData1.csv /input/input2/inputData2.csv /output/output1/ /output/output2
hadoop fs -cat /output/output1/part-r-00000
hadoop fs -get /output ./
hadoop fs -rm -r /output
# $HADOOP_HOME/bin/hdfs dfs -copyFromLocal input.txt /inputTopN.txt
# $HADOOP_HOME/bin/hadoop jar Product.jar /inputTopN.txt /mapreduce_output
# $HADOOP_HOME/bin/hdfs dfs -cat /mapreduce_output/part-r-00000