package sjjTEST;

import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class TestCabochaTree {
    public static void main(String[] args) {
    	TestCabochaTree test1 = new TestCabochaTree();
        String contentString = "いい天気ですね";
        test1.Run(contentString);
    }

    public void Run(String text) {

        try {
            //UTF-8のBOMを除去するための準備←textファイルから読み込む場合を考慮
            //            byte[] bytes = { -17, -69, -65 };
            byte[] bytes = {  };
            String btmp = new String(bytes, "UTF-8");
            //BOM除去
            text = text.replaceAll(btmp, "");

            //cabochaの実行開始　ラティス形式で出力(-f1の部分で決定、詳しくはcabochaのhelp参照)
            ProcessBuilder pb = new ProcessBuilder("cabocha", "");
            Process process = pb.start();

            //実行途中で文字列を入力(コマンドプロンプトで文字を入力する操作)
            OutputStreamWriter osw = new OutputStreamWriter(process.getOutputStream(),
                    "UTF-8");
            osw.write(text);
            osw.close();

            //出力結果を読み込む
            InputStream is = process.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is,
                        "UTF-8"));

            //一行ずつ読み込むための文字列の変数を用意
            String Line = "";
            File writeName = new File("D:/CabochaTree.txt"); // 相对路径，如果没有则要建立一个新的output.txt文件
            writeName.createNewFile(); // 创建新文件,有同名的文件的话直接覆盖
            FileWriter writer = new FileWriter(writeName);
            BufferedWriter out = new BufferedWriter(writer);
            while ((Line = br.readLine()) != null) {
                //行をコンソールへ表示
            	if(!Line.startsWith("EOS")){
            	System.out.println(Line);
            	out.write(Line+"\r\n"); // \r\n即为换行
                out.flush(); // 把缓存区内容压入文件
            	}
            }
            out.close();
            //プロセス終了
            process.destroy();
            process.waitFor();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

 
}
