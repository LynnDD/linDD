package ldqRob;

import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

public class alarm_clock extends Thread  {
    private static String ALARM_TIME = "2007";
    static boolean stop_flag = false;
    
    private int count_for_lazzyman = -1;
	private int count_for_issue = -1;
	private boolean lazzy_man = false;
	private final int COUNT_FOR_LAZZYMAN_MAX = 2900;
	private final int COUNT_FOR_ISSUE_MAX = 2900;

	private String strHH;
	private String strMI;
	private String timenow;
	public void run(){
        try
        {
            while(!stop_flag)
            {
            	clock_reflash();
                Thread.sleep(1000);
                //适时通知
                {
                    count_for_issue ++;
                    if(count_for_issue >= COUNT_FOR_ISSUE_MAX) {
                    	count_for_issue = 0;
                    	JOptionPane.showConfirmDialog(null,  "アクティブタスク:\n\"" + main.currunt_task.massage, "ピカチュウ", JOptionPane.PLAIN_MESSAGE);
                    }
                }
                //愈时通知
                System.out.println(timenow + "  " + ALARM_TIME);
                if(timenow.compareTo(ALARM_TIME)>=0)
                {
                	if(lazzy_man) {
                		count_for_lazzyman ++;
                		if(count_for_lazzyman >= COUNT_FOR_LAZZYMAN_MAX) 
                			lazzy_man = false;
                	} else {
                    	int n = JOptionPane.showConfirmDialog(null, "アクティブタスク:\n" + main.currunt_task.massage  + "\n放弃任务？", "ピカチュウ",JOptionPane.YES_NO_OPTION);//i=0/1 
                    	if(n == 1) {
                    		count_for_lazzyman = 0;
                    		lazzy_man = true;
                    	} else {
        					int index = main.currunt_task.id;
        					if(index == main.tasks.size()) {
        						clock_stop();
        						JOptionPane.showConfirmDialog(null,  "今天任务都完成了", "\"ピカチュウ", JOptionPane.PLAIN_MESSAGE);
        						main.currunt_task = null;
        						return;
        					}
        					main.currunt_task = main.tasks.get(index);
        					ini();
        					JOptionPane.showConfirmDialog(null,  "アクティブタスク:\n\"" + main.currunt_task.massage, "\"ピカチュウ", JOptionPane.PLAIN_MESSAGE);
        					lazzy_man = false;
                    	}
                	}
                }
            }
           
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }
	
	
	
	@SuppressWarnings("deprecation")
	private void clock_reflash() {
		Calendar cld = Calendar.getInstance();
		Date dt = cld.getTime();
        strHH = "" + dt.getHours();
        if(strHH.length()==1)
        {
          strHH = "0" + strHH;
        }
        strMI = "" + dt.getMinutes();
        if(strMI.length()==1)
        {
          strMI = "0" + strMI;
        }
        this.timenow = strHH + strMI;
	}



	public boolean check_time_over(String time) {
		clock_reflash();
		if(this.timenow.compareTo(time)>=0)
			return true;
		else
			return false;
		
	}
	public void ini() {
    	alarm_clock.ALARM_TIME = main.currunt_task.timeOut;
    	count_for_issue = 0;
    	count_for_lazzyman = 0;
    	lazzy_man =false;
    	clock_reflash();
    }
    String get_ALARM_TIME() {
    	return alarm_clock.ALARM_TIME;
    }
    void clock_stop() {
    	alarm_clock.stop_flag = true;
    }

}