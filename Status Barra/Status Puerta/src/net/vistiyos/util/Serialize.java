package net.vistiyos.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Serialize {
	
	public static byte[] SerializeArrayList(ArrayList<String> list){
		try{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream out = new DataOutputStream(baos);
			for (Object element : list.toArray()) {
				out.writeUTF(String.valueOf(element));
			}
			return baos.toByteArray();
		}catch(IOException ex){
			Log.addLog(ex.getMessage());
			return null;
		}
	}
	
	public static ArrayList<String> DeserializeArrayList(byte[] bytes){
		try{
			ArrayList<String> array = new ArrayList<String>();
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			DataInputStream in = new DataInputStream(bais);
			while (in.available() > 0) {
				String element = in.readUTF();
				array.add(element);
			}
			return array;
		}catch(IOException ex){
			Log.addLog(ex.getMessage());
			return null;
		}
	}

}
