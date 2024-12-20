package stubs;

/*
 * TO DO implement
 * Provide additional import statements here
 */
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class CreateSequenceFile extends Configured implements Tool {

  @Override
  public int run(String[] args) throws Exception {

    if (args.length != 2) {
      System.out.printf("Usage: CreateSequenceFile <input dir> <output dir>\n");
      return -1;
    }

    Job job = new Job(getConf());
    job.setJarByClass(CreateSequenceFile.class);
    job.setJobName("Create Sequence File");

    /*
     * Set the input and output paths.
     */
    FileInputFormat.setInputPaths(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));


    /*
     * There is no need to call setInputFormatClass, because the input
     * file is a text file. However, the output file is a SequenceFile.
     * Therefore, we must call setOutputFormatClass.
     */
    job.setOutputFormatClass(SequenceFileOutputFormat.class);


    /*
     * In the first part of the exercise, you write an uncompressed SequenceFile. 
     * Do not set the compression options.
     * When you get to the second part of the exercise, set options here to 
     * compress the output, useSnappy compression, and use block compression.
     */
    /*
     * TODO implement ONLY FOR THE SECOND PART OF THE EXERCISE WHEN YOU 
     * WRITE A COMPRESSED SEQUENCEFILE 
     */

    /*
     * This is a map-only job that uses the default (identity mapper), so we do not need to set
     * the mapper or reducer classes.  We just need to set the number of reducers to 0.
     */
    job.setNumReduceTasks(0);

    boolean success = job.waitForCompletion(true);
    return success ? 0 : 1;
  }

  public static void main(String[] args) throws Exception {
    int exitCode = ToolRunner.run(new Configuration(), new CreateSequenceFile(), args);
    System.exit(exitCode);
  }
}
