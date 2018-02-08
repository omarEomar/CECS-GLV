package com.beshara.csc.hr.emp.integration.presentation.backingbean.emplist;


import com.beshara.jsfbase.csc.backingbean.BaseBean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.List;


public class EmpListBean extends BaseBean{

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private EmpListBeanHelper empListHelper;
    
    //private String aliasValue;
    
    public EmpListBean() {
        empListHelper = (EmpListBeanHelper)deserializeData().get(2);;
    }
    
    public EmpListBean(EmpListBeanHelper empListHelper) {
        this.empListHelper = empListHelper;
        //serializeData(empListHelper);
    }
    
    public static EmpListBean getInstance(EmpListBeanHelper empListHelper){
        
        EmpListBean empListBean = new EmpListBean(empListHelper);
        return empListBean;
    }
    
    
//    protected void serializeData(List data) {
//        try {
//            
//            File codeFile = new File("code.ser");
//            if(!codeFile.exists()) {
//                codeFile.createNewFile();
//            } 
//            FileOutputStream fileOut = new FileOutputStream(codeFile, false); 
//            //FileOutputStream fileOut = new FileOutputStream("/tmp/code.ser");
//            ObjectOutputStream out = new ObjectOutputStream(fileOut);
//            out.writeObject(data);
//            out.close();
//            fileOut.close();
//            System.out.printf("Serialized data is saved in code.ser");
//        } catch (IOException i) {
//            i.printStackTrace();
//        }
//    }
//
//    protected List deserializeData() {
//        try {
//            FileInputStream fileIn = new FileInputStream("code.ser");
//            ObjectInputStream in = new ObjectInputStream(fileIn);
//            List data = (List)in.readObject();
//            //setCode((IEntityKey)in.readObject());
//            in.close();
//            fileIn.close();
//            return data;
//        } catch (IOException i) {
//            i.printStackTrace();
//        } catch (ClassNotFoundException c) {
//            System.out.println("code class not found");
//            c.printStackTrace();
//        }
//        return null;
//    }

//    public void setAliasValue(String aliasValue) {
//        this.aliasValue = aliasValue;
//    }
//
//    public String getAliasValue() {
//        return aliasValue;
//    }



    public void setEmpListHelper(EmpListBeanHelper empListHelper) {
        this.empListHelper = empListHelper;
    }

    public EmpListBeanHelper getEmpListHelper() {
        //empListHelper = (EmpListBeanHelper)deserializeData();
        return empListHelper;
    }
    
    protected void serializeData(Object data) {
        try {
            File codeFile = new File("code.ser");
            if(!codeFile.exists()) {
                codeFile.createNewFile();
            } 
            FileOutputStream fileOut = new FileOutputStream(codeFile, false); 
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(data);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in code.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

//    protected Object deserializeData() {
//        try {
//            FileInputStream fileIn = new FileInputStream("code.ser");
//            ObjectInputStream in = new ObjectInputStream(fileIn);
//            Object data = (Object)in.readObject();
//            in.close();
//            fileIn.close();
//            return data;
//        } catch (IOException i) {
//            i.printStackTrace();
//        } catch (ClassNotFoundException c) {
//            System.out.println("code class not found");
//            c.printStackTrace();
//        }
//        return null;
//    }
    
        protected List deserializeData() {
        try {
            FileInputStream fileIn = new FileInputStream("code.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            List data = (List)in.readObject();
            in.close();
            fileIn.close();
            return data;
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("code class not found");
            c.printStackTrace();
        }
        return null;
    }
}
