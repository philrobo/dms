package com.dms.test;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.dms.jsondatatype.*;
import com.dms.jsondatatype.SectionData;


public class JsonTest {

    public static void writeJson() {

        JSONObject obj = new JSONObject();
        obj.put("name", "mkyong.com");
        obj.put("age", new Integer(100));

        JSONArray list = new JSONArray();
        list.add("msg 1");
        list.add("msg 2");
        list.add("msg 3");

        obj.put("messages", list);

        try (FileWriter file = new FileWriter("f:\\test.json")) {

            file.write(obj.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print(obj);

    }
    
    public static Document readJson(String str) {

            JSONParser parser = new JSONParser();
            
            List<SectionData> lst = new ArrayList<SectionData>();
            
            Document docData = new Document();
            

            try {

                //Object obj = parser.parse(new FileReader("one.json"));
            	
            	Object obj  = parser.parse(str);

                JSONObject jsonObject = (JSONObject) obj;
                //System.out.println(jsonObject);
                
                JSONObject jobjHeader = (JSONObject) jsonObject.get("header");
                docData.PatientUniqueID = (String) jobjHeader.get("businessKey");
                docData.TokenNo = (String) jobjHeader.get("businessSubKey");
                docData.DocumentID = (String) jobjHeader.get("documentID");
                docData.CreatedorUpdateTime = (String) jobjHeader.get("createdOrUpdateTime");
                docData.sectionDataList = new ArrayList<SectionData>();
               
                JSONArray jsonChildObject = (JSONArray)jsonObject.get("sectionData");
                Iterator<JSONObject> iterator = jsonChildObject.iterator();
                while (iterator.hasNext()) {
                	JSONObject joob = iterator.next();
                    System.out.println(joob);
                    SectionData sectionData = new SectionData();
                    
                    sectionData.sectionName = (String) joob.get("sectionName");
                    sectionData.sectionOwnerId = (String) joob.get("sectionOwnerId");
                    sectionData.sectionSequence = (String) joob.get("sectionSequence");
                    sectionData.sectionVersionNo = (String) joob.get("sectionVersionNo");
                    sectionData.sectionDataasText = (String) joob.get("sectionDataasText");
                    sectionData.generateQRCode = (String) joob.get("generateQRCode");
                    sectionData.QRCodeURI = (String) joob.get("QRCodeURI");
                    
                    if (joob.containsKey("sectionDataAsTable")) {
                    	//initialize the sectionDataasList 
                    	sectionData.sectionDataasList = new ArrayList<List<String>>();
                    	JSONObject jSecData = (JSONObject) joob.get("sectionDataAsTable");
                    	System.out.println(jSecData);
                    	List<String> lstTableHeader = new ArrayList<String>();
                    	JSONArray jarr = (JSONArray)jSecData.get("tableHeader");
                    	Iterator<String> itr = jarr.iterator();
                    	while (itr.hasNext()) {
                    		//str  = itr.next();
                    		lstTableHeader.add(itr.next());
                    	}
                    	sectionData.sectionDataasList.add(lstTableHeader);
                    	JSONArray jarr1 = (JSONArray)jSecData.get("rows");
                    	Iterator<JSONObject> itr1 = jarr1.iterator();
                    	while (itr1.hasNext()) {
                    		List<String> lstRow = new ArrayList<String>();
                    		//JSONArray jarr2 = (JSONArray) itr1.next();
                    		Object jb = (Object) itr1.next();
                    		JSONArray jarr3 = (JSONArray) jb;
                    		Iterator<String> itr2 = jarr3.iterator();
                    		while (itr2.hasNext())
                    		{
                    			lstRow.add(itr2.next());
                    		}
                    		sectionData.sectionDataasList.add(lstRow);
                    	}
                    	System.out.println(jSecData.size());
                    }
                    
                    docData.sectionDataList.add(sectionData);
                }
                
                System.out.println(lst.size());

                /*long age = (Long) jsonObject.get("age");
                System.out.println(age); */

                // loop array
                //JSONArray msg = (JSONArray) jsonObject.get("sectionData");
                //Iterator<String> iterator = msg.iterator();
                //while (iterator.hasNext()) {
                //    System.out.println(iterator.next());
               // }

            } 
            catch (ParseException e) {
                e.printStackTrace();
            }
			return docData;

        

    }

}
