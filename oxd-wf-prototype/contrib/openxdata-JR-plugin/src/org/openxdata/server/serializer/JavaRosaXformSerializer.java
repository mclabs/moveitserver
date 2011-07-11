package org.openxdata.server.serializer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openxdata.server.util.XformUtil;


/**
 * Provides the default xform serialization and deserialization from and to the sever.
 * An example of such clients could be mobile devices collecting data in for instance 
 * offline mode, and then send it to the server when connected.
 * 
 * For those who want a different serialization format for xforms,
 * just implement the SerializableData interface and specify the class
 * using the settings {xforms.xformSerializer}. 
 * The jar containing this class can then be
 * put under the webapps/openxdata/web-inf/lib folder.
 * One of the reasons one could want a different serialization format
 * is for performance by doing a more optimized and compact format. Such an example
 * exists in the EpiHandy compact implementation of xforms.
 * 
 * @author Mark
 *
 */
public class JavaRosaXformSerializer {

	public JavaRosaXformSerializer(){

	}

	/**
	 * 
	 * @param dos
	 * @param data
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void serializeForms(DataOutputStream dos,Object data, Integer studyId, String name) throws Exception{
		//This is always a list of strings.
		List<String> xforms = (List<String>)data; 
		//dos.writeByte(xforms.size());
		
		for(String xml : xforms){
			
			String xhtml = XformUtil.fromXform2Xhtml(xml, null);
			
			dos.writeUTF(xhtml.trim());
			System.out.println(xhtml);
			//break;
		}
	}

	/**
	 * 
	 * @param dos
	 * @param data
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void serializeStudies(DataOutputStream dos,Object data) throws Exception{
		List<Object[]> studies = (List<Object[]>)data;

		//dos.writeByte(studies.size());

		String xml = "<?xml version='1.0'?><forms> ";
		for(Object[] study : studies){
			
			int studyId = (Integer)study[0];
			String studyName = (String)study[1];
			
			xml += "<form url='http://localhost:8888/formdownloadservlet?action=downloadforms&amp;uname=Mark&amp;pw=daniel123&amp;formser=JRXformSerializer&amp;studyId=" + studyId + "'>" + studyName + "</form>";
		}			
		xml += "</forms>";
		dos.writeUTF(xml.trim());
		
		System.out.println(xml);
		
		dos.flush();
		
	}

	/**
	 * 
	 * @param dos
	 * @param data
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void serializeUsers(DataOutputStream dos,Object data) throws Exception{
		List<Object[]> users = (List<Object[]>)data; 

		dos.writeByte(users.size());
		for(Object[] user : users){
			dos.writeInt((Integer)user[0]);
			dos.writeUTF((String)user[1]);
			dos.writeUTF((String)user[2]);
			dos.writeUTF((String)user[3]);
		}
	}
	
	public void serializeSuccess(DataOutputStream os) throws IOException{
		
		String str = "The data Has been successfully submitted to the server. Cross check with the server admin to clarify";
		
		os.writeUTF(str);
		
		os.close();
	}
	
	public void serializeFailure(DataOutputStream os, Object ex) throws IOException{
		os.writeUTF(ex.toString());
		os.flush();
		os.close();
	}
	
	public void serializeAccessDenied(DataOutputStream os) throws IOException{
		
		String str = "<HTML><HEAD><TITLE>Data Submission Status</TITLE>"+
		"</HEAD><BODY>Form Submitted Successfully!</BODY></HTML>";
		
		os.writeUTF(str);
		os.flush();
		os.close();
	}
	
	/**
	 * Deserializes forms.
	 * 
	 * @param dis
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public Object deSerialize(DataInputStream dis,Object data) throws Exception{
		List<String> forms = new ArrayList<String>();

		int len = dis.readByte();
		for(int i=0; i<len; i++)
			forms.add(dis.readUTF());

		return forms;
	}
}
