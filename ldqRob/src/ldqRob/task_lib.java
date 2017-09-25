package ldqRob;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.List;

public class task_lib {
	static String pathname = "src\\other\\tasks.txt";
	void open(List<task> tasks){
		 try {
			  	task t;
	            File filename = new File(pathname);
	            
	            InputStreamReader reader = new InputStreamReader(  
	                    new FileInputStream(filename));
	            @SuppressWarnings("resource")
				BufferedReader br = new BufferedReader(reader);
	            String line = "";  
	            line = br.readLine();
	            int i = 0;
	            while (!line.equals("end")) {
	           	 	i++;
	           	 	String timeOut = line;
	           	 	String massage = br.readLine();
	           	 	t = new task(i, timeOut, massage);
	           	 	tasks.add(t);
	           	 	line = br.readLine();
	            }
	            reader.close();
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	}
	
	/*
	void save(List<task> tasks){
		 try {
	            String pathname = "C:\\Users\\rockchip\\Desktop\\tasks.txt";
	            File filename = new File(pathname);
	            filename.createNewFile();
	            BufferedWriter out = new BufferedWriter(new FileWriter(filename));  
	            for(task t:tasks) {
	            	out.write(t.timeOut + "\r\n");
	            	out.write(t.massage + "\r\n");
	            }
	            out.write("end" + "\r\n");
	            out.flush();
	            out.close();
	  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	}
	*/
}
