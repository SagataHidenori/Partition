import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Partition {
    public static void main(String args[]) {
        try {

            // 入力ファイルパス
            System.out.print("inputfile> ");
            Scanner scan = new Scanner(System.in);
            String inputfile = scan.nextLine();
            
            // 桁
            System.out.print("digit> ");
            scan = new Scanner(System.in);
            int digit = Integer.parseInt(scan.nextLine());
            scan.close();

            // 入力ファイル読み込み
            File f = new File(inputfile);
            BufferedReader br = new BufferedReader(new FileReader(f));

            // フォルダ取得
            String dirName = new File(inputfile).getParent();
            String line = null;
            
            Map<String, FileWriter> map = new HashMap<String, FileWriter>();
            
            while ((line = br.readLine()) != null) {
                
                FileWriter fileWriter = null;
                String key = line.substring(digit, digit+1);
                
                if (map.containsKey(key)) {
                    fileWriter = map.get(key);
                } else {
                    File file = new File(dirName + "\\" + key + ".txt");
                    fileWriter = new FileWriter(file, true);
                    map.put(key, fileWriter);
                }
                
                System.out.println(line);

                fileWriter.write(line + "\n");
            }
            
            for(Map.Entry<String, FileWriter> s : map.entrySet()) {
                s.getValue().close();
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}