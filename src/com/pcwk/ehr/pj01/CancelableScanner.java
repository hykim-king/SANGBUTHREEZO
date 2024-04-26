package com.pcwk.ehr.pj01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CancelableScanner {

    Callable<String> subCallable = new Callable<String>(){
        @Override
        public String call() throws Exception {
            // InputStreamReader 는 데이터 흐름을 읽는데 도움을 줄만한 메소드가 들어있는 리더 클래스다.
            // InputStreamReader 는 byte 단위로 읽는다.
            // 여기서는 터미널로 입력받은 데이터 흐름(System.in)을 읽는다.
            InputStreamReader inputStreamReader = new InputStreamReader(System.in);

            // bufferedReader 는 버퍼 단위로 데이터를 읽어주는 클래스다.
            // 여기서는 한줄 읽기(readLine()) 을 사용하기 위해서 썼다.
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            // 버퍼 리더의 버퍼가 준비가 안되어 있으면(비어 있으면)
            while (!bufferedReader.ready()) {
                // 쓰레드를 0.2초 재운다.
                Thread.sleep(200);
            }

            // 여기로 왔다는건 버퍼 리더의 버퍼가 준비되었다는 뜻.

            // 리더에 들어있는 값을 한줄 읽어 inputString 에 넣는다.
            String inputString = bufferedReader.readLine();

            // futureTask 객체로 inputString 을 전달한다.
            return inputString;
        }
    };

    // 응답이 왔을때 값을 받을 FutureTask 타입 객체
    FutureTask<String> futureTask;

    public String readLine() throws Exception {

         futureTask = new FutureTask<>(subCallable);
        // 서브 스레드 선언
        Thread subThread = new Thread(futureTask);
        subThread.start();

        String inputString = futureTask.get();
        return inputString;
    }

    public void cancel(){
    	System.out.println("\n20초간 입력이 없어 재시작합니다.");
        futureTask.cancel(true);
    }
}