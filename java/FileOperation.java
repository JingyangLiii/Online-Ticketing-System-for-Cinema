

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FileOperation {
/**
* This is a method that read the content in file
* @param filename the fileName bank want to read
* @return String[] the file content
*/
public static String[] getfile(String filename){
	int l=0;
	try	{

		File file=new File(filename);

		BufferedReader reader=new BufferedReader(new FileReader(file));
		String line=null;
		while((line=reader.readLine())!=null){
			l++;

				}
		reader.close();

	}
	catch(Exception ex){
		ex.printStackTrace();
	}

	String []filecontent=new String[l];


try	{

	File file=new File(filename);

	BufferedReader reader=new BufferedReader(new FileReader(file));
	String line=null;int i=0;
	while((line=reader.readLine())!=null){
		filecontent[i]=line;
		i++;

			}
	reader.close();

}
catch(Exception ex){
	ex.printStackTrace();
}
return filecontent;
}
/**

* This is a method that separate the string by "/"
* @param lineToParse the fileName we want to read
* @param t the number of which token we want
* @return String the token we want
*/
public static String seperateit(String lineToParse,int t){
	String []token=lineToParse.split("/");
	return token[t];
}
}
