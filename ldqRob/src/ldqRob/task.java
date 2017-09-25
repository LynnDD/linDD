package ldqRob;

public class task {
	 int id;
	 String timeOut;
	 String massage;
	 task(int id, String timeOut, String massage){
		 this.id = id;
		 this.timeOut = timeOut;
		 this.massage = timeOut.substring(0, 2) + ":"+ timeOut.substring(2, 4) + "  " +  massage;
	 }
}
