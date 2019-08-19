package br.gov.previc.simple.batch.batch;

import javax.batch.api.chunk.ItemProcessor;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;

import br.gov.previc.simple.batch.model.PayrollInputRecord;
import br.gov.previc.simple.batch.model.PayrollRecord;

public class SimpleItemProcessor implements ItemProcessor{
	@Inject
    private JobContext jobContext;
	public Object processItem(Object item) throws Exception {
		PayrollInputRecord inputRecord =
                (PayrollInputRecord) item;
        PayrollRecord payrollRecord = 
                new PayrollRecord();

        int base = inputRecord.getBaseSalary();
        float tax = base * 27 / 100.0f;
        float bonus = base * 15 / 100.0f;

        payrollRecord.setEmpID(inputRecord.getId());
        payrollRecord.setBase(base);
        payrollRecord.setTax(tax);
        payrollRecord.setBase(bonus);
        payrollRecord.setNet(base + bonus - tax);   
        return payrollRecord;
	}

}
