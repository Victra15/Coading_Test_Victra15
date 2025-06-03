package 이용태;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileGenerator {
    private static final String JAVA_GEN_PATH = "src/이용태/"; // 자바 파일 만들 경로 입력
    private static final String TEXT_GEN_PATH = "src/이용태/input/"; // input 파일 만들 경로 입력


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        List<String> fileList = new ArrayList<String>();
        System.out.print("파일 이름 입력(엔터 입력시 종료): ");
        while (true) {
            String filename = br.readLine();
            if (filename.isEmpty()) break;
            fileList.add(filename);
        }
        System.out.print("파일을 만드시겠습니까?(취소하려면 N 입력): ");
        String cmd = br.readLine();
        if(cmd.equals("N") || cmd.equals("n")){
            System.exit(0);
        }

        for(String filename : fileList){
            String modFileName = filename.replaceAll(" ", "");
            sb.append(JAVA_GEN_PATH).append(modFileName).append(".java");
            if (!new File(sb.toString()).createNewFile()) {
                System.err.println(sb.toString() + "파일이 존재합니다.");
            }
            sb.setLength(0);

            sb.append(TEXT_GEN_PATH).append(modFileName).append(".txt");
            if (!new File(sb.toString()).createNewFile()) {
                System.err.println(sb.toString() + "파일이 존재합니다.");
            }
            sb.setLength(0);
        }
        br.close();
    }
}
