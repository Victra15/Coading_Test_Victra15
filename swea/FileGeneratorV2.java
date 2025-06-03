package 이용태;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileGeneratorV2 {
	private static final String JAVA_GEN_PATH = "src/이용태/"; // 자바 파일 만들 경로 입력; 꼭 "/"로 끝내세요 제발...
	private static final String TEXT_GEN_PATH = "src/이용태/input/"; // input 파일 만들 경로 입력

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        List<String> fileList = new ArrayList<>();
        System.out.print("파일 이름 입력 (엔터 입력시 종료): ");
        while (true) {
            String filename = br.readLine();
            if (filename.isEmpty()) break;
            fileList.add(filename);
        }
        System.out.print("파일을 만드시겠습니까? (취소하려면 N 입력): ");
        String cmd = br.readLine();
        if(cmd.equals("N") || cmd.equals("n")){
            System.exit(0);
        }

        List<String> javaFilePaths = new ArrayList<>();
        List<String> textFilePaths = new ArrayList<>();
        
        for(String filename : fileList){
            String modFileName = filename.replaceAll(" ", "");
            
            // java file
            sb.append(JAVA_GEN_PATH).append(modFileName).append(".java");
            
            String javaFilePath = sb.toString();
            javaFilePaths.add(javaFilePath);
            
            if (!new File(javaFilePath).createNewFile()) {
                System.err.println(javaFilePath + "파일이 존재합니다.");
            }
            sb.setLength(0);

            // txt file
            sb.append(TEXT_GEN_PATH).append(modFileName).append(".txt");
            
            String textFilePath = sb.toString();
            textFilePaths.add(textFilePath);
            
            if (!new File(textFilePath).createNewFile()) {
                System.err.println(textFilePath + "파일이 존재합니다.");
            }
            sb.setLength(0);
        }
        
        for (int idx = 0; idx < javaFilePaths.size(); idx++) {
        	String fileName = fileList.get(idx);
			File javaFile = new File(javaFilePaths.get(idx));
			String packageName = "";
			if (JAVA_GEN_PATH.charAt(JAVA_GEN_PATH.length()-1) == '/') {
				packageName = JAVA_GEN_PATH.substring(4, JAVA_GEN_PATH.length()-1).replace('/', '.'); // 패키지가 소스 파일 내에 들어있음을 가정함!!!
			} else {
				packageName = JAVA_GEN_PATH.substring(4, JAVA_GEN_PATH.length()).replace('/', '.'); // 패키지가 소스 파일 내에 들어있음을 가정함!!!
			}
			
			String textFilePath = textFilePaths.get(idx);
        	FileWriter fw = new FileWriter(javaFile);
        	BufferedWriter bw = new BufferedWriter(fw);
        	bw.write("package "
        			+ packageName
        			+ ";\r\n"
        			+ "\r\n"
        			+ "import java.io.BufferedReader;\r\n"
        			+ "import java.io.BufferedWriter;\r\n"
        			+ "import java.io.FileInputStream;\r\n"
        			+ "import java.io.IOException;\r\n"
        			+ "import java.io.InputStreamReader;\r\n"
        			+ "import java.io.OutputStreamWriter;\r\n"
        			+ "\r\n"
        			+ "public class "
        			+ fileName
        			+ " {\r\n"
        			+ "\r\n"
        			+ "	public static void main(String[] args) throws IOException {\r\n"
        			+ "		System.setIn(new FileInputStream(\""
        			+ textFilePath
        			+ "\"));\r\n"
        			+ "		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));\r\n"
        			+ "		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));\r\n"
        			+ "		StringBuilder sb = new StringBuilder();\r\n"
        			+ "		\r\n"
        			+ "	}\r\n"
        			+ "}");
        	bw.close();
        	fw.close();
        }
        br.close();
    }
}
