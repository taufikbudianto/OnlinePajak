package com.taufik.service;


import com.taufik.model.TaxSchema;
import com.taufik.util.AbstractUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author taufik.budiyanto
 * @date 05/01/2021
 * com.taufik.service
 */
@Service
public class Pph21ServiceImpl extends AbstractUtil implements Pph21Service {

    @Override
    public Double taxation(Double salary,Boolean relief,String reliefType) {
        Double taxation =0.0;
        Double annualSalary = salary*12;
        List<TaxSchema> listSchema =  getListTaxSchema();
        Double totalSalary =0.0;
        Double totalSalaryTemp =0.0;
        if(relief){
            annualSalary=annualSalary-Double.valueOf(getRelief().get(reliefType));
        }
        for(int i=0;i<listSchema.size();i++){
            TaxSchema taxSchema = listSchema.get(i);
            if(annualSalary<=taxSchema.getMax() && i==0){
                taxation+=taxSchema.getTaxRate()*annualSalary/100;
                break;
            }else{
                if(annualSalary>totalSalary && annualSalary>taxSchema.getMax() && i<listSchema.size()){
                    totalSalaryTemp+=(taxSchema.getMax()-totalSalary);
                    Double dataReal =taxSchema.getMax()-totalSalary;
                    taxation+=taxSchema.getTaxRate()*dataReal/100;
                    totalSalary=taxSchema.getMax();
                }else{
                    taxation+=taxSchema.getTaxRate()*(annualSalary-totalSalaryTemp)/100;
                    break;
                }
            }

        }
        return taxation;
    }

}
