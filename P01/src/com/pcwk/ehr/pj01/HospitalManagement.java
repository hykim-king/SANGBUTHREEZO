package com.pcwk.ehr.pj01;
import java.util.*;

import com.pcwk.ehr.cmn.PLog;


/*
 * 
 * 정상적으로 사용자에게 보여지는 메세지를 출력할땐 System.out 으로 출력한다.
 * 오류가 발생했을때는 LOG.debug 메세지를 남긴다.
 */

public class HospitalManagement<T> implements PLog{
	//환자 객체들을 저장하는 리스트
	List<T> patients = new ArrayList<T>();
	
	//병원 이름
	String hospitalName;
	
	
	//생성자
	public HospitalManagement(String hospitalName) {
		super();
		this.hospitalName = hospitalName;
		this.patients = new LinkedList<>();
	}
	
	
	/*
	 * 환자의 정보를 환자 "이름+등록일.txt" 파일로 기록.
	 * 정상적으로 저장 읽었을경우 0 리턴, 문제가생겼을경우 -1 리턴(Exception 이 발생했을경우)
	 * 
 try catch 문으로 처리.
	 * LOG.debug(e.getMessage)를 통해 문제를 기록하도록함.
	 */
	public int saveWholeFile() {
		
		Iterator<T> iterator =this.patients.iterator();
		while(iterator.hasNext()) {
			saveInfoFile(iterator.next());
			Iterator <VitalInfo> viterator=(Iterator<VitalInfo>)iterator;
		
			/*
			 * 순서대로 모든 환자를 순회하며 정보중 vital 정보 만을 입력 받아 환자의 vital에 정보를 입력
			 * 등록할때 생성된 "환자이름+등록날짜.txt"로 파일 이름을 정해서 저장한다.
			 * 저장은 saveInfofile 메소드를 통해 구현한다.
			 * 
			 */
		}
		return 0;
	
		
	}
	
	
	public int saveInfoFile(T patient) {
		return 0;
		
	}
	
	public int saveVitalInfoFile(T patient,VitalInfo vitalinfo) {
		return 0;
	}
	
	
	public int deleteInfo(T patient) {
		return 0;
	}
	
	public int deleteInfoFile(String path) {
		return 0;
	}
	
	
	public T readInfoFile(String path) {
		T patient=null;
		return patient;
	}
	
	public VitalInfo readVitalInfoFile(String path) {
		VitalInfo info = null;
		return info;
	}
	
	
	public Patient search(String name) {
		return new Patient();
	}
	
	
	
	/*
	 * 경로에 저장된 모든 파일들을 읽어서 환자 객체를 생성해서
	 * list에 추가한다.
	 */
	public int initHospital() {
		//경로에 있는 모든 파일에 접근해서 정보를 읽은후 리스트에 추가
		/*	
		 * 	patients.add(readInfoFile(""));
			
			for (int i =0;i<patients.size();i++) {
				Patient p =(Patient) patients.get(i);
				p.getVitalinfo().add(readVitalInfoFile(""));
			}
		 * 
		 */
		
		
		return 0;
		
	}
	
	
	
	
	//새로운 환자 정보를 입력,
	public int registerPatient() {
		T patient=null;
		
		patients.add(patient);
		//list에 환자객체를 추가한다.
		return 0;
	}
	
	
	
	//새로운 환자 정보를 입력,
	public void modifyPatient() {
			
		//환자 객체를 찾아 해당 객체의 정보를 수정합니다. "환자이름+등록날짜.txt" 또한 수정합니다.
	}
	
	
	
	//기존 환자 정보를 입력받아 리스트에서 삭제
	public void deletePatient() {
		T patient=null;
		
		patients.remove(patient);
		//환자의 이름을 입력받아 list 에서 equals 를 이용하여 이름이 같은 환자를 찾아,"환자이름+등록날짜.txt"파일을 삭제
		
	}
	
	
	//정상 종료시 0 리턴 문제가 발생시 -1 리턴
	public int vitalCheck() {
	
		Scanner scanner = new Scanner(System.in);
		Iterator<T> iterator =this.patients.iterator();
		while(iterator.hasNext()) {
			VitalInfo info = null;
			Patient p =	(Patient) iterator.next();
			int BPM;//심박수
			int SBP;// 수축기 혈압
			int DBP;// 이완기 혈압
			int bloodSugar;//혈당
			
			System.out.println(p.getName()+" 환자의 바이탈 체크를 시작합니다.");
			
			System.out.print("심박수:");
			BPM=Integer.parseInt(scanner.nextLine());
			System.out.print("수축기혈압:");
			SBP=Integer.parseInt(scanner.nextLine());
			System.out.print("이완기혈압:");
			DBP=Integer.parseInt(scanner.nextLine());
			System.out.print("혈당:");
			bloodSugar=Integer.parseInt(scanner.nextLine());
			info = new VitalInfo(BPM,SBP,DBP,bloodSugar);
			
			
			/* 
			 * 순서대로 모든 환자를 순회하며 정보중 vital 정보 만을 입력 받아 환자의 vital에 정보를 입력
			 * 
			 */
		}
		System.out.println("바이탈 체크를 완료하였습니다.\n\n");
		scanner.close();
		return 0;
	}
	
	
	//환자 목록을 출력
	public void patientList() {
		
		int num=0;
		Iterator<T> iterator =this.patients.iterator();
		if(iterator.hasNext()==false) {
			System.out.println("환자 목록이 비었습니다.");
		}
		while(iterator.hasNext()) {
			
			
			Patient p = (Patient)iterator.next();
			System.out.println((num+1)+"."+p.getName());
			num++;
			/*
			 * 번호를 매겨 환자의 이름을 출력해주고 총 인원수를 출력해준다.
			 */
		}
		System.out.println("총 환자의 수는 "+num+"명입니다.");
		System.out.println();
		System.out.println();
	}

	//환자 위험도 평가
	//정상 종료시 0 리턴 문제가 발생시 -1 리턴
	public int evaluatePatientStatus() {
	      
		int bpm;
               int sbp;
	       int bdp;
               int bloodSugar;

       Iterator<T> iterator =this.patients.iterator();
       while(iterator.hasNext()) {
       
	Patient patient = (Patient) iterator.next();
        List<VitalInfo> vitalInfos = Patient.getVitalInfo();

        //마지막으로 기록될 바이탈 정보를 가져옴(최근)
        VitalInfo latestVitalInfo = vitalInfos.get(vitalInfos.size()-1);

        //환자의 상태를 저장할 변수
        String status = "Safe";

       // 모든 수치가 위험한 경우
	if(latestVitalInfo.getBpm() > 100 && latestVitalInfo.getBloodSugar() > 150 &&
	latestVitalInfo.getSbp() > 140 && latestVitalInfo.getDbp() >90) {
	status = "All_Very_Dangreous";
	patient.setIsNotified(false);
	}else if(latestVitalInfo.getBpm() > 80 || latestVitalInfo.getBloodSugar() > 120 ||
	latestVitalInfo.getSbp() > 140 || latestVitalInfo.getDbp() > 90) {
	status = "One_or_More_Dangerous"; // VitalSign 중 하나라도 위험 범위에 속할시(조기에 발견)
	patient.setIsNotified(false);
	}

	//환자 바이탈 정보에 따라 상태 평가(수치에 따라 Very_dangrous, Dangerous, Safe 로 구분)
	if(latestVitalInfo.getBpm() > 100 || latestVitalInfo.getBloodSugar() > 150) {
	status = "Very_dangrous";
	patient.setIsNotified(false);
	}else if(latestVitalInfo.getBpm() > 80 || latestVitalInfo.getBloodSugar() > 120) {
	status = "Dangerous";
	patient.setIsNotified(false);
	}else if(latestVitalInfo.getSbp() > 140 || latestVitalInfo.getDbp() > 90) {
	status = "High_blood_Pressure";
	}else {
	status = "Safe";
	}

	/* Patient p =iterator.next();
	* p.Vitalinfo.get();
	* 환자의 바이탈이 정상 범위를 벗어나면 그 정도에 따라
	* status를 Safe,Dangerous,very_Dangerous로 설정해줌
	* status 가 Dangerous 와 very_Dangerous 일경우에는 isNotified 를 false 로 변경해준다.
	*/
	// 환자 상태 설정

	// 환자 상태 설정 후, 위험한 상태일 경우시 알림 여부 설정
	patient.setStatus(status);
	if(!status.equals("Safe")) {
	Patient.setIsNotified(false);
	}

	}
	// 정상적으로 종료 되었을시 0을 리턴
	return 0;

}

	
	
	
	public void notifyToDoctor() {
		//환자의 이름과 상태를 의사에게 전달한다는 메세지 출력 후 isNotified 를 true 로 변경해줌
	}
	
	
	public void patientReport() {
		
			/*
			 * 환자 한명의 이름을 입력받아 해당 환자를 검색, 해당 환자의 정보를 출력해준다.
			 *  name;// 이름
				gender;// 성별
				bloodType;// 혈액형
				birthDay;// 생년월일 YYYY.MM.DD
				height;// 키
				weight;// 몸무게
				registerdDate;// 등록일
				isNotified;
				PatientStatus status;
				
				//바이탈 이력을 출력할지 선택하게 한후 선택시 바이탈 정보도 시간을 포함해 같이 출력.
				Vital history; -리스트에 있는 바이탈 정보들을 전부 출력
			 * 
			 */
		
	}
	
	
	public void hospitalReport() {
		
		Iterator<T> iterator =this.patients.iterator();
		while(iterator.hasNext()) {
			/*
			 * 모든 환자에 대해서 환자의 이름과 현재 위험상태를 출력하고, 총 환자수, 각각 상태(Safe, Dangerous등)의 환자의 수,
			 * 의사에게 notify되지 않은 환자의 여부를 출력해주고, "현재시간+report.txt" 에 내용을 기록한다.
			 * 
			 */
		}
		
	}
	
	
	
	
	

}
