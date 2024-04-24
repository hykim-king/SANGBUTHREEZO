package com.pcwk.ehr.pj01;

import java.util.Scanner;

import com.pcwk.ehr.cmn.PLog;



public class Main implements PLog{
	
	
	public static void main(String[] args) {
		
		
		HospitalManagement<Patient> hospital = new HospitalManagement<Patient>("myHospital");
		hospital.initHospital();
		System.out.println(hospital.hospitalName+" 환자 관리를 시작합니다.");
		LoopThread loop = new LoopThread(hospital);
		//VitalThread th =new VitalThread(hospital);
		


		//테스트용코드
		Patient patient01=new Patient("이성연", "남성", "O", "19881003", 183, 100);
		Patient patient02=new Patient("김성연", "여성", "A", "19881003", 163, 60);
		VitalInfo vitalinfo01= new VitalInfo(80, 80, 120, 120);
		VitalInfo vitalinfo02= new VitalInfo(60, 100, 180, 220);
		hospital.patients.add(patient01);
		hospital.patients.add(patient02);
		hospital.patients.get(0).vitalinfo.add(vitalinfo01);
		hospital.patients.get(1).vitalinfo.add(vitalinfo02);
		
		
		//th.start();
		loop.start();
		try {
			loop.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		LOG.debug("프로그램 종료");
		hospital.saveWholeFile();

		
	}

}
