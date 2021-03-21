package Optional;

import java.io.*;
import java.util.List;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.io.File;
import java.io.FileWriter;

import com.northconcepts.datapipeline.core.Record;
import com.northconcepts.datapipeline.core.RecordList;
import com.northconcepts.datapipeline.job.Job;
import com.northconcepts.datapipeline.memory.MemoryReader;
//import com.northconcepts.datapipeline.template.TemplateWriter;


public class ReportCommand extends Command{
    static String inputTemplate = "java_example.vm";
    static String className = "ReportCommand";
    static String message = "Hello World!";
    static String outputFile = className + ".java";

    public ReportCommand(List<Item> items) {
        this.items = items;
    }

    @Override
    public void executeCommand() throws IOException {
        Record record1 = new Record();
        for(int i=0;i<getItems().size();i++) {
            record1.getField(getItems().get(i).getName(), true).setValue(getItems().get(i).getPath());
        }
        MemoryReader reader = new MemoryReader(new RecordList(record1));
        /*TemplateWriter writer = new TemplateWriter(new FileWriter("example/data/output/catalog-items"));
        writer.setFieldNamesInFirstRow(false);
        writer.getConfiguration().setDirectoryForTemplateLoading(new File("example/data/input"));
        writer.setFooterTemplate("ReportCommand-footer.html");
        writer.setDetailTemplate("ReportCommand-detail.html");*/
    }

}
