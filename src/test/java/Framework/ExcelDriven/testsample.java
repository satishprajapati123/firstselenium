package Framework.ExcelDriven;

import java.io.IOException;
import java.util.ArrayList;

public class testsample {

	public static void main(String[] args) throws IOException {
		
		Datadriven d = new Datadriven();
		ArrayList data = d.getdata("Login");
		System.out.println(data.get(0));
		System.out.println(data.get(1));
		System.out.println(data.get(2));

	}

}
