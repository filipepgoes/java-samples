package br.gov.previc.simple.batch.batch;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Properties;

import javax.batch.api.chunk.AbstractItemReader;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.gov.previc.simple.batch.model.PayrollInputRecord;

@Named
public class SimpleItemReader extends AbstractItemReader{
	@Inject
	JobContext jobContext;
	InputStream inputStream;
	BufferedReader br;
	Integer recordNumber;
	String line;

	@Override
	public Object readItem() throws Exception {
		Object record = null;
	       if (line != null) {
	            String[] fields = line.split("[, \t\r\n]+");
	            PayrollInputRecord payrollInputRecord = new PayrollInputRecord();
	            payrollInputRecord.setId(Integer.parseInt(fields[0]));
	            payrollInputRecord.setBaseSalary(Integer.parseInt(fields[1]));
	            record = payrollInputRecord;
	            //Now that we could successfully read, Increment the record number
	           recordNumber++;
	        }
	        return record;
	}
	@Override
	public void open(Serializable prevCheckpointInfo) throws Exception {
        JobOperator jobOperator = BatchRuntime.getJobOperator();
        Properties jobParameters = jobOperator.getParameters(jobContext.getExecutionId());
        String resourceName = (String) jobParameters.get("payrollInputDataFileName");
        inputStream = new FileInputStream(resourceName);        
        br = new BufferedReader(new InputStreamReader(inputStream));

        if (prevCheckpointInfo != null)
            recordNumber = (Integer) prevCheckpointInfo;
        for (int i=1; i<recordNumber; i++) {   //Skip upto recordNumber
            br.readLine();
        } 
       System.out.println("[SimpleItemReader] Opened Payroll file for reading from record number: "              + recordNumber);
    }
	@Override
	public Serializable checkpointInfo() throws Exception {
	        return recordNumber;
	}
}
