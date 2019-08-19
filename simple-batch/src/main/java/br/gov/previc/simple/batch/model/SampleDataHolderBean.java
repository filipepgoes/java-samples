package br.gov.previc.simple.batch.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import br.gov.previc.simple.batch.model.PayrollInputRecord;
import br.gov.previc.simple.batch.model.PayrollRecord;

@Singleton
@Startup
public class SampleDataHolderBean {

    private Map<String, Set<PayrollInputRecord>> payrollInputRecords
            = new HashMap<>();

    private Map<String, Set<PayrollRecord>> payrollRegistry
            = new HashMap<>();
    
    @PostConstruct
    public void onApplicationStartup() {
        String[] monthYear = new String[] {"JAN-2013", "FEB-2013", "MAR-2013"};
        for (int monthIndex = 0; monthIndex < monthYear.length; monthIndex++) {
            Set<PayrollInputRecord> inputRecords = new HashSet<PayrollInputRecord>(5);
            for (int empID=1; empID<6; empID++) {
                PayrollInputRecord e = new PayrollInputRecord();
                e.setId(empID);
                int baseSalary = 10000 + empID*200 + monthIndex*100;
                e.setBaseSalary(baseSalary);
                inputRecords.add(e);
            }

            payrollInputRecords.put(monthYear[monthIndex], inputRecords);
        }

    }

    public String[] getAllMonthYear() {
        return payrollInputRecords.keySet().toArray(new String[0]);
    }

    public Set<PayrollInputRecord> getPayrollInputRecords(String monthYear) {
        return payrollInputRecords.get(monthYear);
    }
    
    public void addPayrollRecord(PayrollRecord r) {
        String monthYear = r.getMonthYear();
        Set<PayrollRecord> monthlyPayroll = payrollRegistry.get(monthYear);
        if (monthlyPayroll == null) {
            monthlyPayroll = new HashSet<>();
            payrollRegistry.put(monthYear, monthlyPayroll);
        }
        monthlyPayroll.add(r);
    }
    
    public Set<PayrollRecord> getPayrollRecords(String monthYear) {
        Set<PayrollRecord> empty = new HashSet<>();
        Set<PayrollRecord> records = payrollRegistry.get(monthYear);
        return records == null ? empty : records;
    }
}