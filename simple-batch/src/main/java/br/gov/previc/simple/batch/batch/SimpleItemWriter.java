package br.gov.previc.simple.batch.batch;

import java.io.Serializable;
import java.util.List;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.naming.InitialContext;

import br.gov.previc.simple.batch.model.PayrollRecord;
import br.gov.previc.simple.batch.model.SampleDataHolderBean;

@Named
public class SimpleItemWriter extends AbstractItemWriter {

@EJB
private SampleDataHolderBean bean;

@Override
public void open(Serializable checkpoint) throws Exception {
    super.open(checkpoint);
    try {
        InitialContext ctx = new InitialContext();
        bean = (SampleDataHolderBean) ctx.lookup("java:module/SampleDataHolderBean");
    } catch (Exception ex) {
        //
    }
}

public void writeItems(List list) throws Exception {
    for (Object obj : list) {
        bean.addPayrollRecord((PayrollRecord) obj);
    }
}

}
